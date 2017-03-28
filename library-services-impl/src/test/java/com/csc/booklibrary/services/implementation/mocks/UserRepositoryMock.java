package com.csc.booklibrary.services.implementation.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.persistence.interfaces.UserRole;

/**
 * A mock class of UserRepository used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class UserRepositoryMock implements UserRepository {

    private final Map<Long, User> users;
    private final Map<String, User> usersByUsername;
    private final Map<Long, List<Message>> messagesByUserId;
    private final Map<Long, List<Comment>> commentsByBookId;

    /**
     * Constructor of the class. Creates the mock objects.
     */
    public UserRepositoryMock() {
        users = new HashMap<>();
        usersByUsername = new HashMap<>();
        messagesByUserId = new HashMap<>();
        commentsByBookId = new HashMap<>();

        final UserMock user1 = new UserMock(12, "user1");
        user1.setPassword("123asd");
        users.put(Long.valueOf(12), user1);
        usersByUsername.put(user1.getUsername(), user1);

        final UserMock user2 = new UserMock(23, "user2");
        users.put(Long.valueOf(23), user2);

        final UserMock user3 = new UserMock(7, "user3");
        user3.setUserRoleId(1);
        users.put(Long.valueOf(7), user3);

        final User user4 = new UserMock(1, "Joshua132");
        users.put(Long.valueOf(1), user4);
        usersByUsername.put("Joshua132", user4);

        final User user5 = new UserMock(22, "Jo75");
        users.put(Long.valueOf(22), user5);
        usersByUsername.put("Jo75", user5);
        messagesByUserId.put(Long.valueOf(22), new ArrayList<>());

        commentsByBookId.put(Long.valueOf(1), new ArrayList<>());

        final User user = new UserMock(100);
        final Book book = new BookMock(2);
        final List<Comment> comments = new ArrayList<>();
        comments.add(new CommentMock(user, book, "a comment")); //$NON-NLS-1$
        comments.add(new CommentMock(user, book, "another comment")); //$NON-NLS-1$
        commentsByBookId.put(Long.valueOf(2), comments);

    }

    @Override
    public User createUser(final String username, final String password, final String firstName, final String lastName,
            final UserRole userRole, final String email) {
        final UserMock user123 = new UserMock(123, username);
        users.put(Long.valueOf(123), user123);
        return user123;
    }

    @Override
    public List<User> findUsers() {
        final List<User> usersList = new ArrayList<>();
        for (final User user : users.values()) {
            usersList.add(user);
        }
        return usersList;
    }

    @Override
    public User findUser(final long userId) {
        return users.get(userId);
    }

    @Override
    public List<Message> findMessagesReceivedByUserId(final long userId) {
        return messagesByUserId.get(userId);
    }

    @Override
    public Message addMessage(final User receiver, final String messageText, final User sender) {
        final Message message = new MessageMock(receiver, messageText, sender);
        messagesByUserId.get(Long.valueOf(22)).add(message);
        return null;
    }

    @Override
    public User addCommentFrom(final User commentWriter, final Book book, final String content) {
        final Comment comment = new CommentMock(commentWriter, book, content);
        commentWriter.addComment(comment);
        return commentWriter;
    }

    @Override
    public List<Comment> findCommentsByBookId(final long id) {
        return commentsByBookId.get(id);
    }

    @Override
    public User findUserByUsername(final String username) {
        return usersByUsername.get(username);
    }

}
