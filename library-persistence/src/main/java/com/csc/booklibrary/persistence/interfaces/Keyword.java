package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

/**
 * This class has information about the keywords with which a book can be
 * sought. The fields describe what the exact keyword is.
 *
 * @author mduhovnikov
 *
 */
public interface Keyword {

    /**
     * @return The keyword that we search for.
     */
    String getKeyword();

    /**
     * Adds a new keyword to search with.
     *
     * @param keyword
     *            The new keyword.
     */
    void setKeyword(String keyword);

    /**
     * @return The id of the keyword.
     */
    long getKeywordsId();

    /**
     * @return All books containing this keyword.
     */
    List<Book> getBooksContaining();
}