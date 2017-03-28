package com.csc.booklibrary.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see User
 *
 * @author mvitanov
 *
 */
@Entity
@SequenceGenerator(name = "User", sequenceName = "USERS_SEQ")
@Table(name = "USERS")
@NamedQueries({ @NamedQuery(name = "ql.user.all", query = "select u from UserJPA u order by u.dateRegistered desc"),
        @NamedQuery(name = "ql.user.byuserid", query = "select u from UserJPA u where u.userId = :userId"),
        @NamedQuery(name = "getUserByUsername", query = "select u from UserJPA u where u.username = :username") })
public class UserJPA implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @Column(name = "USER_ID")
    private long userId;

    private String username;

    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_ROLE_ID")
    private int userRoleId;

    @Column(name = "DATE_REGISTERED")
    private LocalDate dateRegistered;

    @Column(name = "DATE_INACTIVE")
    private LocalDate dateInactive;

    private String email;

    private String phone;

    private byte[] picture;

    @OneToMany(mappedBy = "fromUser", targetEntity = MessageJPA.class)
    @OrderBy(value = "messageDate DESC")
    private List<Message> messagesSent = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", targetEntity = MessageJPA.class)
    @OrderBy(value = "messageDate DESC")
    private List<Message> messagesReceived = new ArrayList<>();

    @OneToMany(mappedBy = "user", targetEntity = CommentJPA.class)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", targetEntity = RequestJPA.class)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "user", targetEntity = BorrowJPA.class)
    private List<Borrow> borrowsReturned = new ArrayList<>();

    UserJPA() {
        // Used by JPA framework
    }

    /**
     * Constructor.
     *
     * @param username
     *            User's username.
     * @param password
     *            User's password.
     * @param firstName
     *            User's first name.
     * @param lastName
     *            User's last name.
     * @param userRoleId
     *            Enumeration for the user's role.
     * @param email
     *            User's email.
     */
    public UserJPA(final String username, final String password, final String firstName, final String lastName,
            final int userRoleId, final String email) {
        assert username != null;
        assert password != null;
        assert firstName != null;
        assert lastName != null;
        assert userRoleId == 1 || userRoleId == 2;
        assert email != null;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRoleId = userRoleId;
        this.email = email;
        dateRegistered = LocalDate.now();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getUserRoleId() {
        return userRoleId;
    }

    @Override
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public LocalDate getDateInactive() {
        return dateInactive;
    }

    @Override
    public void setDateInactive(LocalDate dateInactive) {
        this.dateInactive = dateInactive;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public byte[] getPicture() {
        return picture;
    }

    @Override
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    @Override
    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    @Override
    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    @Override
    public void addMessageSent(final Message m) {
        messagesSent.add(m);
    }

    @Override
    public void addMessageReceived(final Message m) {
        messagesReceived.add(m);
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void addComment(final Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void addRequest(final Request req) {
        requests.add(req);
    }

    @Override
    public List<Borrow> getBorrowsReturned() {
        return borrowsReturned;
    }
}
