package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

/**
 * This class has information about the content of a book. The fields describe
 * its title, name and isbn as well as its publishers and author/s, year of
 * publishment and edition. It can also have a selected category, language and a
 * cover photo. Requests and comments can be made for the book content and it
 * can be searched by using selected keywords.
 *
 * @author mduhovnikov
 *
 */
public interface Book {

    /**
     * @return A list of all requests made for the book.
     */
    List<Request> getRequests();

    /**
     * @return A list of all comments made for the book.
     */
    List<Comment> getComments();

    /**
     * Adds a new request for the book.
     *
     * @param req
     *            The new request.
     */
    void addRequest(Request req);

    /**
     * @return The edition of the book.
     */
    int getEdition();

    /**
     * Changes the edition of the book.
     *
     * @param edition
     *            The new edition.
     */
    void setEdition(int edition);

    /**
     * @return The publisher of the book.
     */
    Publisher getPublisher();

    /**
     * @return The year in which the book was published.
     */
    int getYearPublished();

    /**
     * @return The cover photo of the book.
     */
    byte[] getCoverPhoto();

    /**
     * Adds a new cover photo for the book.
     *
     * @param coverPhoto
     *            The new photo.
     */
    void setCoverPhoto(byte[] coverPhoto);

    /**
     * @return The name of the book.
     */
    String getName();

    /**
     * @return The ISBN of the book.
     */
    String getIsbn();

    /**
     * Changes the ISBN of the book.
     *
     * @param isbn
     *            The new ISBN.
     */
    void setIsbn(String isbn);

    /**
     * @return The category of the book.
     */
    long getCategoryId();

    /**
     * @return The language of the book.
     */
    long getLanguageId();

    /**
     * @return The number of copies of the book.
     */
    int getNumberOfCopies();

    /**
     * Changes the existing number of copies of the book.
     *
     * @param numberOfCopies
     *            The new number.
     */
    void setNumberOfCopies(int numberOfCopies);

    /**
     * @return A list of all requests made for the book.
     */
    User getRegisteredBy();

    /**
     * @return The id of the book.
     */
    long getBookId();

    /**
     * @return A list of all authors of the book.
     */
    List<Author> getAuthors();

    /**
     * Changes the authors of the book.
     *
     * @param authors
     *            The new authors.
     */
    void setAuthors(List<Author> authors);

    /**
     * @return A list of all keywords used for searching the book.
     */
    List<Keyword> getKeywords();
}
