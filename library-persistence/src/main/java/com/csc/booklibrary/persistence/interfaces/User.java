package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has information about the users in the system. The fields describe
 * the user's username, password, names, role, email, phone number, profile
 * picture, the date they registered and the date they were deactivated. Also
 * stores collections containing all messages sent by the user and all messages
 * received by the user.
 *
 * @author mvitanov
 *
 */
public interface User {

    /**
     * @return DB id of the user.
     */
    long getUserId();

    /**
     * @return The user's password.
     */
    String getPassword();

    /**
     * Changes the user's password.
     *
     * @param password
     *            New password.
     */
    void setPassword(String password);

    /**
     * @return The user's first name.
     */
    String getFirstName();

    /**
     * Changes the user's first name.
     *
     * @param firstName
     *            New first name.
     */
    void setFirstName(String firstName);

    /**
     * @return The user's last name.
     */
    String getLastName();

    /**
     * Changes the user's last name.
     *
     * @param lastName
     *            New last name.
     */
    void setLastName(String lastName);

    /**
     * @return The code of the user's role. 1 means admin, 2 means regular user.
     */
    int getUserRoleId();

    /**
     * Change the user's role.
     *
     * @param userRoleId
     *            1 sets role to admin, 2 sets role to regular user.
     */
    void setUserRoleId(int userRoleId);

    /**
     * @return LocalDate when the user was deactivated. Null means the user is still
     *         active.
     */
    LocalDate getDateInactive();

    /**
     * Deactivate the user.
     *
     * @param dateInactive
     *            Sets the date on which the user was deactivated.
     */
    void setDateInactive(LocalDate dateInactive);

    /**
     * @return The user's email.
     */
    String getEmail();

    /**
     * Change the user's email.
     *
     * @param email
     *            New email.
     */
    void setEmail(String email);

    /**
     * @return The user's phone number. Can be null.
     */
    String getPhone();

    /**
     * Change the user's phone number.
     *
     * @param phone
     *            New phone number.
     */
    void setPhone(String phone);

    /**
     * @return Byte array representing the user's profile picture. Can be null.
     */
    byte[] getPicture();

    /**
     * Change the user's profile picture.
     *
     * @param picture
     *            New picture represented by Byte array.
     */
    void setPicture(byte[] picture);

    /**
     * @return the user's username.
     */
    String getUsername();

    /**
     * @return The date on which the user was registered.
     */
    LocalDate getDateRegistered();

    /**
     * @return A list of the messages sent by the user.
     */
    List<Message> getMessagesSent();

    /**
     * @return A list of the messages received by the user.
     */
    List<Message> getMessagesReceived();

    /**
     * @return A list of the comments made by the user.
     */
    List<Comment> getComments();

    /**
     * @return A list of the requests made by the user.
     */
    List<Request> getRequests();

    /**
     * Add a request made by the user.
     *
     * @param req
     *            Request entity.
     */
    void addRequest(Request req);

    /**
     * Add another message sent by the user.
     *
     * @param m
     *            Sent message entity.
     */
    void addMessageSent(Message m);

    /**
     * Add another message received by the user.
     *
     * @param m
     *            Received message entity.
     */
    void addMessageReceived(Message m);

    /**
     * Add a comment made by the user.
     *
     * @param comment
     *            Comment entity.
     */
    void addComment(Comment comment);

    /**
     * @return If the user is an admin returns all borrows returned to that
     *         admin. For a regular user returns null.
     */
    List<Borrow> getBorrowsReturned();
}
