package com.csc.booklibrary.services.implementation.mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * A mock class of BookRepository used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class BookRepositoryMock implements BookRepository {

    private final Map<Long, Book> book;
    private final Map<Long, Request> requests;
    private final Map<String, Publisher> publishers;
    private final Map<String, Author> authors;
    private final List<Request> allRequests;
    private final List<Request> allPendingRequests;

    /**
     * Constructor of the class. Creates the mock objects.
     */
    public BookRepositoryMock() {
        book = new HashMap<>();
        requests = new HashMap<>();
        publishers = new HashMap<>();
        authors = new HashMap<>();
        allRequests = new ArrayList<>();
        allPendingRequests = new ArrayList<>();

        final BookMock book1 = new BookMock(1);
        book1.setNumberOfCopies(23);
        book1.setAuthors(new ArrayList<Author>());
        book.put(Long.valueOf(1), book1);

        final BookMock book2 = new BookMock(2);
        book2.setNumberOfCopies(0);
        book.put(Long.valueOf(2), book2);

        final BookMock book3 = new BookMock(3);
        book3.setNumberOfCopies(2);
        book.put(Long.valueOf(3), book3);

        final RequestMock request1 = new RequestMock(new UserMock(1), book1, 1);
        request1.setRequestId(123);
        allRequests.add(request1);
        allPendingRequests.add(request1);
        requests.put(Long.valueOf(123), request1);

        final RequestMock request2 = new RequestMock(new UserMock(2), book2, 2);
        request2.setRequestId(567);
        allRequests.add(request2);
        requests.put(Long.valueOf(567), request2);

        final RequestMock request3 = new RequestMock(new UserMock(2), book3, 2);
        request3.setRequestId(89);
        request3.setDateAnswered(LocalDateTime.now());
        allRequests.add(request3);
        requests.put(Long.valueOf(89), request3);

        final PublisherMock publisher1 = new PublisherMock(1, "SamplePublisher1");
        publishers.put("SamplePublisher1", publisher1);

        final AuthorMock author1 = new AuthorMock(1, "SampleAuthor1");
        authors.put("SampleAuthor1", author1);
    }

    @Override
    public Book createBook(final String name, final String isbn, final Publisher publisher, final List<Author> authors,
            final int yearPublished, final Category category, final Language language, final User registeredBy) {
        final BookMock b = new BookMock(4);
        book.put(Long.valueOf(4), b);
        return b;
    }

    @Override
    public Author createAuthor(final String name) {
        final AuthorMock author2 = new AuthorMock(2, name);
        authors.put(name, author2);
        return author2;
    }

    @Override
    public Publisher createPublisher(final String name) {
        final PublisherMock publisher2 = new PublisherMock(2, name);
        publishers.put(name, publisher2);
        return publisher2;
    }

    @Override
    public Request addRequest(final User user, final Book book, final Type type) {
        final RequestMock request = new RequestMock(user, book, type.getTypeId());
        request.setRequestId(555);
        requests.put(Long.valueOf(555), request);
        return request;
    }

    @Override
    public Book findBook(final long id) {
        return book.get(id);
    }

    @Override
    public Author findAuthorByName(final String name) {
        return authors.get(name);
    }

    @Override
    public Publisher findPublisherByName(final String name) {
        return publishers.get(name);
    }

    @Override
    public Request findRequest(final long id) {
        return requests.get(id);
    }

    @Override
    public List<Book> findBooks() {
        final List<Book> bookList = new ArrayList<Book>(book.values());
        return bookList;
    }

    @Override
    public List<Request> findRequests() {
        return allRequests;
    }

    @Override
    public List<Request> findRequests(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> findPendingRequests() {
        return allPendingRequests;
    }

    @Override
    public List<Request> findPendingRequests(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

}
