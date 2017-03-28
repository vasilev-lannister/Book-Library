package com.csc.booklibrary.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Borrow
 *
 * @author mduhovnikov
 *
 */
@Entity
@Table(name = "BORROW")
@NamedQueries({ @NamedQuery(name = "ql.borrow.all", query = "select b from BorrowJPA b"),
        @NamedQuery(name = "ql.borrow.byborrowid", query = "select b from BorrowJPA b where b.request = :request") })
public class BorrowJPA implements Borrow {
    @Id
    private long borrowId;

    @MapsId
    @OneToOne(targetEntity = RequestJPA.class)
    @JoinColumn(name = "BORROW_ID")
    private Request request;

    @ManyToOne(targetEntity = BookCopyJPA.class)
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @Column(name = "DATE_TAKEN")
    private LocalDate dateTaken;

    @Column(name = "DATE_RETURNED")
    private LocalDate dateReturned;

    @Column(name = "FINAL_DATE")
    private LocalDate finalDate;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "ADMIN_RETURN_ID")
    private User user;

    BorrowJPA() {
        // Used by JPA framework
    }

    /**
     * Constructor of the class.
     * 
     * @param request
     *            The request for which the borrowing is made.
     * @param bookCopy
     *            The copy of the book that is requested.
     */
    public BorrowJPA(final Request request, final BookCopy bookCopy) {
        assert request != null;
        assert bookCopy != null;
        this.request = request;
        this.bookCopy = bookCopy;
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @Override
    public LocalDate getDateTaken() {
        return dateTaken;
    }

    @Override
    public void setDateTaken(final LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    @Override
    public LocalDate getDateReturned() {
        return dateReturned;
    }

    @Override
    public LocalDate getFinalDate() {
        return finalDate;
    }

    @Override
    public void setFinalDate(final LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(final User user) {
        this.user = user;
    }
}