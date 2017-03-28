package com.csc.booklibrary.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookCopy;
import com.csc.booklibrary.persistence.interfaces.Borrow;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Book
 *
 * @author mduhovnikov
 *
 */
@Entity
@SequenceGenerator(name = "BookCopy", sequenceName = "BOOK_COPY_SEQ")
@Table(name = "BOOK_COPY")
@NamedQuery(name = "ql.bookCopy.AvailableBookCopyByBook", query = "select b from BookCopyJPA b where b.book = :book and b.taken = 0")
public class BookCopyJPA implements BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookCopy")
    @Column(name = "BOOK_COPY_ID")
    private long bookCopyId;

    @Column(name = "DATE_ADDED")
    private LocalDate dateAdded;

    private int taken;

    @ManyToOne(targetEntity = BookJPA.class)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "DATE_REMOVED")
    private LocalDate dateRemoved;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "USER_ID")
    private User registeredBy;

    @OneToMany(mappedBy = "bookCopy", targetEntity = BorrowJPA.class)
    private List<Borrow> bookCopyBorrowed = new ArrayList<>();

    BookCopyJPA() {
        // Used by JPA framework
    }

    /**
     * Constructor of the class.
     *
     * @param taken
     *            A flag indicating whether the book is taken or not.
     * @param book
     *            The book which has a copy.
     * @param registeredBy
     *            The person who has registered the copy.
     */
    public BookCopyJPA(final int taken, final Book book, final User registeredBy) {

        assert taken == 1 || taken == 0;
        assert book != null;
        assert registeredBy != null;
        this.dateAdded = LocalDate.now();
        this.taken = taken;
        this.book = book;
        this.registeredBy = registeredBy;

    }

    @Override
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    @Override
    public int getTaken() {
        return taken;
    }

    @Override
    public void setTaken(final int taken) {
        this.taken = taken;
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public void setBook(final Book book) {
        this.book = book;
    }

    @Override
    public LocalDate getDateRemoved() {
        return dateRemoved;
    }

    @Override
    public void setDateRemoved(final LocalDate dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    @Override
    public User getRegisteredBy() {
        return registeredBy;
    }

    @Override
    public void setRegisteredBy(final User registeredBy) {
        this.registeredBy = registeredBy;
    }

    @Override
    public List<Borrow> getBookCopyBorrows() {
        return bookCopyBorrowed;
    }

    @Override
    public long getBookCopyId() {
        return bookCopyId;
    }

}
