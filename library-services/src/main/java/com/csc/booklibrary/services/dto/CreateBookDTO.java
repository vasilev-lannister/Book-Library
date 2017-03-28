package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has the information needed for creating the book without its
 * authors, publisher and user who has registered it.
 * 
 * @author mduhovnikov
 *
 */
public class CreateBookDTO implements Serializable {

    private static final long serialVersionUID = -4146868678158957046L;
    private final String name;
    private final int edition;
    private final int yearPublished;
    private final String isbn;
    private final long category;
    private final long language;
    private final int quantity;

    /**
     * @param name
     *            The name of the book.
     * @param isbn
     *            The ISBN of the book.
     * @param yearPublished
     *            The year in which the book was published.
     * @param edition
     *            The edition of the book.
     * @param category
     *            The category of the book.
     * @param language
     *            The language of the book.
     * @param quantity
     *            The quantity for the book.
     */
    public CreateBookDTO(final String name, final String isbn, final int yearPublished, final int edition,
            final long category, final long language, final int quantity) {
        this.name = name;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.quantity = quantity;
        this.edition = edition;
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
     * @return The year in which the book was published.
     */
    public int getYearPublished() {
        return yearPublished;
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
     * @return The edition of the book.
     */
    public int getEdition() {
        return edition;
    }
}
