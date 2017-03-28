package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

/**
 * This class has information about the authors of a book. The fields consist of
 * their name, nationality and nickname.
 *
 * @author mvasilev
 *
 */
public interface Author {

    /**
     * @return The name of the author.
     */
    String getName();

    /**
     * @return The id of the author.
     */
    long getAuthorId();

    /**
     * @return The nationality of the author.
     */
    String getNationality();

    /**
     * @return The nickname of the author.
     */
    String getNickname();

    /**
     * Changes the nationality of the author.
     *
     * @param nationality
     *            the new nationality
     */
    void setNationality(String nationality);

    /**
     * Changes the nickname of the author.
     *
     * @param nickname
     *            the new nickname
     */
    void setNickname(String nickname);

    /**
     * @return All books written by this author.
     */
    List<Book> getBooksWritten();

    /**
     * Changes the written books by the author.
     *
     * @param booksWritten
     *            list of the books written
     */
    void setBooksWritten(List<Book> booksWritten);
}
