package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

public interface UserRepository {
    /**
     * Creates new user.
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param userRole
     * @param email
     * @return Returns the created user.
     */
    User createUser(String username, String password, String firstName, String lastName, UserRole userRole,
            String email);

    /**
     * Finds user by username.
     *
     * @param username
     * @return Returns founded user.
     */
    User findUserByUsername(String username);

    /**
     *
     * @return Returns all users.
     */
    List<User> findUsers();

    /**
     * Finds user by user id.
     *
     * @param userId
     * @return Returns founded user.
     */
    User findUser(long userId);

    List<Message> findMessagesReceivedByUserId(long userId);

    Message addMessage(User receiver, String messageText, User sender);

    User addCommentFrom(User commentWriter, Book bookContent, String content);

    /**
     * Find all comments for the book content with the given id.
     *
     * @param id
     *            Id of the book content to search for.
     * @return All comments for the book content with the given id.
     */
    List<Comment> findCommentsByBookId(long id);
}
