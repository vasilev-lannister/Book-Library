package com.csc.booklibrary.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.csc.booklibrary.domain.BorrowJPA;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.BookCopyRepository;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;

/**
 * @see BookCopyRepository
 *
 * @author mduhovnikov
 *
 */
public final class BookCopyRepositoryJPA implements BookCopyRepository {

    private final EntityManager em;

    /**
     * Constructor of the class.
     *
     * @param em
     *            The entity manager that is used.
     */
    public BookCopyRepositoryJPA(final EntityManager em) {
        this.em = em;
    }

    @Override
    public List<BookCopy> findAvailableBookCopies(Book book) {
        assert book != null;
        return em.createNamedQuery("ql.bookCopy.AvailableBookCopyByBook", BookCopy.class).setParameter("book", book)
                .getResultList();
    }

    @Override
    public Borrow addBorrow(Request request, BookCopy bookCopy) {
        final Borrow borrow = new BorrowJPA(request, bookCopy);
        em.persist(borrow);
        return borrow;
    }
}
