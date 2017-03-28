package com.csc.booklibrary.initialization;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.services.impl.BookRequestServiceImpl;
import com.csc.booklibrary.services.impl.BookServiceImpl;
import com.csc.booklibrary.services.impl.BorrowBookServiceImpl;
import com.csc.booklibrary.services.impl.LoginServiceImpl;
import com.csc.booklibrary.services.impl.MessagesServiceImpl;
import com.csc.booklibrary.services.impl.ReviewServiceImpl;
import com.csc.booklibrary.services.impl.UserServiceImpl;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.services.interfaces.BorrowBookService;
import com.csc.booklibrary.services.interfaces.LoginService;
import com.csc.booklibrary.services.interfaces.MessagesService;
import com.csc.booklibrary.services.interfaces.ReviewService;
import com.csc.booklibrary.services.interfaces.UserService;

/**
 * This class is used for initializing the services.
 * 
 * @author mduhovnikov
 *
 */
public final class ServiceFactoryCreator {

    private final TransactionHandler transactionHandler;

    /**
     * Constructor of the class.
     * 
     * @param transactionHandler
     *            The transaction handler that is used as a constructor
     *            argument.
     */
    public ServiceFactoryCreator(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    /**
     * This method registers all services within a service factory.
     * 
     * @return The created service factory.
     */
    public ServiceFactory<TransactionHandler> createServiceFactory() {

        final ServiceRegistry<TransactionHandler> registry = new ServiceRegistry<>();

        registry.registerService(BookRequestService.class, BookRequestServiceImpl::new);
        registry.registerService(BookService.class, BookServiceImpl::new);
        registry.registerService(BorrowBookService.class, BorrowBookServiceImpl::new);
        registry.registerService(MessagesService.class, MessagesServiceImpl::new);
        registry.registerService(ReviewService.class, ReviewServiceImpl::new);
        registry.registerService(UserService.class, UserServiceImpl::new);
        registry.registerService(LoginService.class, LoginServiceImpl::new);

        return new ServiceFactory<TransactionHandler>(registry, transactionHandler);
    }
}
