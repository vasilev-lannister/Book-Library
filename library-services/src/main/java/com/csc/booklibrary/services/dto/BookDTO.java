package com.csc.booklibrary.services.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This class has information about a particular book including all fields that
 * contain information needed for registering it properly.
 * 
 * @author mduhovnikov
 *
 */
public class BookDTO implements Serializable {

    private static final long serialVersionUID = -5716743408697530864L;
    private final long bookId;
    private final String name;
    private final List<AuthorDTO> authors;
    private final int edition;
    private final int yearPublished;
    private final PublisherDTO publisher;
    private final String isbn;
    private final long category;
    private final long language;
    private final int quantity;
    private final UserDTO registeredBy;

    /**
     * @param bookId
     *            The id of the book.
     * @param edition
     *            The edition of the book.
     * @param publisher
     *            The publisher of the book.
     * @param name
     *            The name of the book.
     * @param isbn
     *            The ISBN of the book.
     * @param authors
     *            The authors of the book.
     * @param yearPublished
     *            The year in which the book was published.
     * @param category
     *            The category of the book.
     * @param language
     *            The language of the book.
     * @param quantity
     *            The quantity for the book.
     * @param registeredBy
     *            The person who registered the book.
     */
    public BookDTO(final long bookId, final int edition, final PublisherDTO publisher, final String name,
            final String isbn, final List<AuthorDTO> authors, final int yearPublished, final long category,
            final long language, final int quantity, final UserDTO registeredBy) {
        this.bookId = bookId;
        this.name = name;
        this.authors = authors;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.quantity = quantity;
        this.registeredBy = registeredBy;
        this.edition = edition;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
    }

    /**
     * @return The name of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The authors of the book.
     */
    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    /**
     * @return The id of the book.
     */
    public long getBookId() {
        return bookId;
    }

    /**
     * @return The year in which the book was published.
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * @return The publisher of the book.
     */
    public PublisherDTO getPublisher() {
        return publisher;
    }

    /**
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return The category of the book.
     */
    public long getCategory() {
        return category;
    }

    /**
     * @return The language of the book.
     */
    public long getLanguage() {
        return language;
    }

    /**
     * @return The quantity for the book.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return The person who registered the book.
     */
    public UserDTO getRegisteredBy() {
        return registeredBy;
    }

    /**
     * @return The edition of the book.
     */
    public int getEdition() {
        return edition;
    }
}
