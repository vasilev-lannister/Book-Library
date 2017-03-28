package com.csc.booklibrary.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.RejectReason;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Request
 *
 * @author mduhovnikov
 *
 */
@Entity
@Table(name = "REQUEST")
@SequenceGenerator(name = "Request", sequenceName = "REQUEST_SEQ")
@NamedQueries({ @NamedQuery(name = "ql.request.all", query = "select r from RequestJPA r order by r.dateMade desc"),
        @NamedQuery(name = "ql.request.user", query = "select r from RequestJPA r where r.user =:user"),
        @NamedQuery(name = "ql.request.pending", query = "select r from RequestJPA r where r.dateAnswered = null order by r.dateMade desc"),
        @NamedQuery(name = "ql.request.pending.user", query = "select r from RequestJPA r where r.user =:user and r.dateAnswered = null order by r.dateMade desc") })
public class RequestJPA implements Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Request")
    @Column(name = "REQUEST_ID")
    private long requestId;

    @OneToOne(mappedBy = "request", targetEntity = BorrowJPA.class)
    private Borrow borrow;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(targetEntity = BookJPA.class)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "TYPE_ID")
    private int typeId;

    @Column(name = "DATE_MADE")
    private LocalDateTime dateMade;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "ADMIN_ID")
    private User admin;

    @Column(name = "REQUEST_COMMENT")
    private String requestComment;

    @ManyToOne(targetEntity = RejectReasonJPA.class)
    @JoinColumn(name = "REJECT_REASON_ID")
    private RejectReason rejectReason;

    @Column(name = "DATE_ANSWERED")
    private LocalDateTime dateAnswered;

    /**
     * Empty constructor.
     */
    public RequestJPA() {
        /* JPA */}

    /**
     * Constructor of the class.
     * 
     * @param user
     *            The user who has made the request.
     * @param book
     *            The book for which the request is.
     * @param typeId
     *            The id of the type of the request.
     */
    public RequestJPA(final User user, final Book book, final int typeId) {

        assert user != null;
        assert book != null;
        assert typeId == 1 || typeId == 2 || typeId == 3 || typeId == 4;

        this.user = user;
        this.book = book;
        this.typeId = typeId;
        this.dateMade = LocalDateTime.now();
    }

    @Override
    public long getRequestId() {
        return requestId;
    }

    @Override
    public void setRequestId(final long requestId) {
        this.requestId = requestId;
    }

    @Override
    public Book getBook() {
        return this.book;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public void setTypeId(final int typeId) {
        this.typeId = typeId;
    }

    @Override
    public LocalDateTime getDateMade() {
        return dateMade;
    }

    @Override
    public User getAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(final User admin) {
        this.admin = admin;
    }

    @Override
    public String getRequestComment() {
        return requestComment;
    }

    @Override
    public void setRequestComment(final String requestComment) {
        this.requestComment = requestComment;
    }

    @Override
    public RejectReason getRejectReason() {
        return this.rejectReason;
    }

    @Override
    public void setRejectReasonId(final RejectReason rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public LocalDateTime getDateAnswered() {
        return dateAnswered;
    }

    @Override
    public void setDateAnswered(final LocalDateTime dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Borrow getBorrow() {
        return borrow;
    }
}
