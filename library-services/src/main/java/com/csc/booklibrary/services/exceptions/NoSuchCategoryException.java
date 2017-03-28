package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when a category cannot be found by id in the persistence
 * layer. Stores the id of that category.
 * 
 * @author mduhovnikov
 *
 */
public class NoSuchCategoryException extends RuntimeException {

    private static final long serialVersionUID = 2334216889309438452L;
    private final long categoryId;

    /**
     * Constructor.
     * 
     * @param categoryId
     *            id of the category which wasn't found.
     */
    public NoSuchCategoryException(final long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return id of the category which wasn't found.
     */
    public long getCategoryId() {
        return categoryId;
    }
}