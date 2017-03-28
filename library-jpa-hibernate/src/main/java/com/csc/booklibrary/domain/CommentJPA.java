package com.csc.booklibrary.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Comment
 *
 * @author lbosilkov
 */
@Entity
@SequenceGenerator(name = "Comment", sequenceName = "COMMENT_SEQ")
@NamedQueries({
        @NamedQuery(name = "ql.comments.book", query = "select c from CommentJPA c where c.book.bookId = :bookId order by c.commentDate asc"),
        @NamedQuery(name = "ql.comments.user", query = "select c from CommentJPA c where c.user = :user") })
@Table(name = "COMMENTS")
public class CommentJPA implements Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Comment")
    @Column(name = "COMMENT_ID")
    private long id;

    @ManyToOne(targetEntity = CommentJPA.class)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "COMMENT_DATE")
    private LocalDateTime commentDate;

    @ManyToOne(targetEntity = BookJPA.class)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private String content;

    @OneToMany(mappedBy = "parent", targetEntity = CommentJPA.class)
    private List<Comment> comments = new ArrayList<>();

    CommentJPA() {
        // For JPA
    }

    /**
     * Constructor without parent comment.
     * 
     * @param user
     *            The user who has made the comment.
     * @param book
     *            The book for which the comment is.
     * @param content
     *            The content of the comment.
     */
    public CommentJPA(final User user, final Book book, final String content) {
        assert user != null;
        assert book != null;
        assert content != null;

        this.user = user;
        this.book = book;
        this.content = content;
        this.commentDate = LocalDateTime.now();
    }

    /**
     * Constructor with parent comment.
     * 
     * @param user
     *            The user who has made the comment.
     * @param book
     *            The book for which the comment is.
     * @param content
     *            The content of the comment.
     * @param parent
     *            The parent comment of the current one.
     */
    public CommentJPA(final User user, final Book book, final String content, final Comment parent) {
        assert user != null;
        assert book != null;
        assert content != null;
        assert parent != null;

        this.user = user;
        this.book = book;
        this.content = content;
        this.parent = parent;
        this.commentDate = LocalDateTime.now();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Comment getParent() {
        return parent;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void addComments(final Comment c) {
        comments.add(c);
    }
}
