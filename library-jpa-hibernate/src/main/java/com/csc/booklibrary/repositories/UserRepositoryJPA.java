package com.csc.booklibrary.repositories;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import com.csc.booklibrary.domain.CommentJPA;
import com.csc.booklibrary.domain.MessageJPA;
import com.csc.booklibrary.domain.UserJPA;
import com.csc.booklibrary.exceptions.UserAlreadyExistsException;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.persistence.interfaces.UserRole;

/**
 * @see UserRepository
 *
 * @author mduhovnikov
 *
 */
public final class UserRepositoryJPA implements UserRepository {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryJPA.class.getName());
    private final EntityManager em;

    /**
     * Constructor of the class.
     *
     * @param em
     *            The used entity manager.
     */
    public UserRepositoryJPA(final EntityManager em) {
        this.em = em;
    }

    @Override
    public User createUser(final String username, final String password, final String firstName, final String lastName,
            final UserRole userRole, final String email) {

        final User userCreated = new UserJPA(username, password, firstName, lastName, userRole.getRoleId(), email);
        try {
            em.persist(userCreated);
            return userCreated;
        } catch (final ConstraintViolationException e) {
            LOGGER.log(Level.INFO, "Unique Key Constraint Violation", e);
            throw new UserAlreadyExistsException(username);
        }
    }

    @Override
    public List<User> findUsers() {
        return em.createNamedQuery("ql.user.all", User.class).getResultList();
    }

    @Override
    public User findUser(final long userId) {
        return em.find(UserJPA.class, userId);
    }

    @Override
    public User findUserByUsername(final String username) {

        try {
            return em.createNamedQuery("getUserByUsername", User.class).setParameter("username", username)
                    .getSingleResult();
        } catch (final NoResultException e) {
            LOGGER.log(Level.INFO, "User doesn't exist: " + "\"" + username + "\"", e);
            return null;
        }
    }

    @Override
    public List<Message> findMessagesReceivedByUserId(final long userId) {
        return em.createNamedQuery("ql.message.byreceivinguser", Message.class).setParameter("toUser", userId)
                .getResultList();
    }

    @Override
    public Message addMessage(final User receiver, final String messageText, final User sender) {
        final Message message = new MessageJPA(receiver, messageText, sender);
        em.persist(message);
        receiver.addMessageReceived(message);
        sender.addMessageSent(message);
        return message;
    }

    @Override
    public User addCommentFrom(final User commentWriter, final Book book, final String content) {
        final Comment c = new CommentJPA(commentWriter, book, content);
        em.persist(c);
        commentWriter.addComment(c);
        return commentWriter;
    }

    @Override
    public List<Comment> findCommentsByBookId(final long bookId) {
        return em.createNamedQuery("ql.comments.book", Comment.class).setParameter("bookId", bookId).getResultList();
    }

}
