package com.csc.booklibrary.repositories;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.csc.booklibrary.domain.AuthorJPA;
import com.csc.booklibrary.domain.BookJPA;
import com.csc.booklibrary.domain.PublisherJPA;
import com.csc.booklibrary.domain.RequestJPA;
import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Category;
import com.csc.booklibrary.persistence.interfaces.Language;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.Type;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see BookRepository
 *
 * @author mduhovnikov
 *
 */
public final class BookRepositoryJPA implements BookRepository {

    private final EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(BookRepositoryJPA.class.getName());

    /**
     * Constructor of the class.
     *
     * @param em
     *            the entity manager that is used.
     */
    public BookRepositoryJPA(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Book createBook(final String name, final String isbn, final Publisher publisher, final List<Author> authors,
            final int yearPublished, final Category category, final Language language, final User registeredBy) {
        final BookJPA bookCreated = new BookJPA(name, isbn, publisher, authors, yearPublished, category.getCategoryId(),
                language.getLanguageId(), registeredBy);
        em.persist(bookCreated);
        return bookCreated;
    }

    @Override
    public Author createAuthor(final String name) {
        final Author author = new AuthorJPA(name);
        em.persist(author);
        return author;
    }

    @Override
    public Publisher createPublisher(final String name) {
        final Publisher publisher = new PublisherJPA(name);
        em.persist(publisher);
        return publisher;
    }

    @Override
    public Request addRequest(final User user, final Book book, final Type type) {
        final Request req = new RequestJPA(user, book, type.getTypeId());
        em.persist(req);
        book.addRequest(req);
        user.addRequest(req);
        return req;
    }

    @Override
    public Book findBook(final long id) {
        return em.find(BookJPA.class, id);
    }

    @Override
    public Author findAuthorByName(final String name) {

        try {
            return em.createNamedQuery("ql.author.authorbyname", Author.class).setParameter("name", name)
                    .getSingleResult();
        } catch (final NoResultException e) {
            LOGGER.log(Level.INFO, "No such author: " + name, e);
            return null;
        }
    }

    @Override
    public Publisher findPublisherByName(final String name) {

        try {
            return em.createNamedQuery("ql.publisher.publisherbyname", Publisher.class).setParameter("name", name)
                    .getSingleResult();
        } catch (final NoResultException e) {
            LOGGER.log(Level.INFO, "No such publisher: " + name, e);
            return null;
        }
    }

    @Override
    public Request findRequest(final long id) {
        return em.find(RequestJPA.class, id);
    }

    @Override
    public List<Book> findBooks() {
        return em.createNamedQuery("ql.book.all", Book.class).getResultList();
    }

    @Override
    public List<Request> findRequests() {
        return em.createNamedQuery("ql.request.all", Request.class).getResultList();
    }

    @Override
    public List<Request> findRequests(final User user) {
        return em.createNamedQuery("ql.request.user", Request.class).setParameter("user", user).getResultList();
    }

    @Override
    public List<Request> findPendingRequests() {
        return em.createNamedQuery("ql.request.pending", Request.class).getResultList();
    }

    @Override
    public List<Request> findPendingRequests(final User user) {
        return em.createNamedQuery("ql.request.pending.user", Request.class).setParameter("user", user).getResultList();
    }

}
