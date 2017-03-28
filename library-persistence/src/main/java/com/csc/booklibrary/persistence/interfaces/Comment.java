package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class contains information about comments: - commented book; - user who
 * wrote the comment; - content of the comment; - date of comment; - might have
 * or not parent comment.
 *
 * @author lbosilkov
 *
 */
public interface Comment {

    /**
     * @return The comment's id.
     */
    long getId();

    /**
     * @return The comment's parent comment.
     */
    Comment getParent();

    /**
     * @return The user who wrote the comment.
     */
    User getUser();

    /**
     * @return The date on which the comment is written.
     */
    LocalDateTime getCommentDate();

    /**
     * @return The bookContent which is commented
     */
    Book getBook();

    /**
     * @return A list representing all sub-comments.
     */
    List<Comment> getComments();

    /**
     * @return The actual text of the comment.
     */
    String getContent();

    /**
     * Add another comment.
     *
     * @param c
     *            Comment entity.
     */
    void addComments(Comment c);
}