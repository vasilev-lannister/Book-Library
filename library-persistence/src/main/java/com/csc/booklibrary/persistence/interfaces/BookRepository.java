package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

public interface BookRepository {

    /**
     * @param publisher
     * @param yearPublished
     * @param name
     * @param category
     * @param language
     * @param registeredBy
     * @return A book created from the parameters.
     */
    Book createBook(String name, String isbn, Publisher publisher, List<Author> authors, int yearPublished,
            Category category, Language language, User registeredBy);

    /**
     * @param name
     * @return An author created from the parameter.
     */
    Author createAuthor(String name);

    /**
     * @param publisherId
     * @param name
     * @return A publisher created from the parameters.
     */
    Publisher createPublisher(String name);

    /**
     * Create a new request.
     *
     * @param user
     *            User making the request.
     * @param book
     *            Book requested.
     * @param type
     * @return A request created from the parameters.
     */
    Request addRequest(User user, Book book, Type type);

    /**
     * Find a book by id.
     *
     * @param id
     *            Database id to search for.
     * @return A book with the required id.
     */
    Book findBook(long id);

    /**
     * Find an author by name.
     *
     * @param name
     *            Database name to search for.
     * @return An author with the required name.
     */
    Author findAuthorByName(String name);

    /**
     * Find a publisher by name.
     *
     * @param name
     *            Database name to search for.
     * @return A publisher with the required name.
     */
    Publisher findPublisherByName(String name);

    /**
     * Find request by id.
     *
     * @param id
     * @return A request with the required id.
     */
    Request findRequest(long id);

    /**
     * @return All books in the database.
     */
    List<Book> findBooks();

    /**
     * @return All requests in the database.
     */
    List<Request> findRequests();

    /**
     * Find requests by user.
     *
     * @param user
     * @return All requests made by a particular user.
     */
    List<Request> findRequests(User user);

    /**
     * @return All pending requests. Pending requests are requests which have
     *         not been processed by an admin.
     */
    List<Request> findPendingRequests();

    List<Request> findPendingRequests(User user);
}
