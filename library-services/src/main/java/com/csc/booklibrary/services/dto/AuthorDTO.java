package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the authors of a book.
 * 
 * @author mduhovnikov
 *
 */
public class AuthorDTO implements Serializable {

    private static final long serialVersionUID = -3157604376028609407L;
    private final long authorId;
    private final String name;
    private final String nationality;
    private final String nickname;

    /**
     * @param authorId
     *            The id of the author.
     * @param name
     *            The name of the author.
     * @param nationality
     *            The nationality of the author.
     * @param nickname
     *            The nickname of the author.
     */
    public AuthorDTO(final long authorId, final String name, final String nationality, final String nickname) {
        this.authorId = authorId;
        this.name = name;
        this.nationality = nationality;
        this.nickname = nickname;
    }

    /**
     * @return The name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The nationality of the author.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @return The nickname of the author.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return The id of the author.
     */
    public long getAuthorId() {
        return authorId;
    }
}
