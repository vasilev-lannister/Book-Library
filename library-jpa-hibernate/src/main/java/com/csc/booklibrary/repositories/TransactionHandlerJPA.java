package com.csc.booklibrary.repositories;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;

/**
 * @see TransactionHandler
 *
 * @author mduhovnikov
 *
 */
public class TransactionHandlerJPA implements TransactionHandler {

    private final EntityManagerFactory entityManagerFactory;
    private final ServiceRegistry<EntityManager> registry = new ServiceRegistry<>();

    /**
     * Constructor with an entity manager factory.
     *
     * @param emf
     *            the entity manager factory
     */
    public TransactionHandlerJPA(final EntityManagerFactory emf) {
        entityManagerFactory = emf;
        registry.registerService(BookRepository.class, BookRepositoryJPA::new);
        registry.registerService(BookCopyRepository.class, BookCopyRepositoryJPA::new);
        registry.registerService(UserRepository.class, UserRepositoryJPA::new);
    }

    @Override
    public <TransactionResult> TransactionResult execute(
            final Function<ServiceFactory<?>, TransactionResult> transactionalCode) {

        final EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return executeTransaction(transactionalCode, em);
        } finally {
            em.close();
        }
    }

    private <TransactionResult> TransactionResult executeTransaction(
            final Function<ServiceFactory<?>, TransactionResult> transactionalCode, final EntityManager em) {
        final EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {
            final TransactionResult transactionResult = transactionalCode
                    .apply(new ServiceFactory<>(registry, em));

            transaction.commit();
            return transactionResult;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

}
