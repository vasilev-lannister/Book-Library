package com.csc.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Keyword;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Book
 *
 * @author mduhovnikov
 *
 */
@Entity
@SequenceGenerator(name = "Book", sequenceName = "BOOK_SEQ")
@Table(name = "BOOK")
@NamedQueries({ @NamedQuery(name = "ql.book.all", query = "select bk from BookJPA bk order by bk.bookId desc"),
        @NamedQuery(name = "ql.book.bybookid", query = "select bk from BookJPA bk where bk.bookId = :bookId") })

public class BookJPA implements Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book")
    @Column(name = "BOOK_ID")
    private long bookId;

    @ManyToOne(targetEntity = PublisherJPA.class)
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "REGISTERED_BY")
    private User registeredBy;

    @Column(name = "CATEGORY_ID")
    private long categoryId;

    @Column(name = "LANGUAGE_ID")
    private long languageId;

    private int edition;

    @Column(name = "YEAR_PUBLISHED")
    private int yearPublished;

    @Column(name = "COVER_PHOTO")
    private byte[] coverPhoto;

    private String name;

    private String isbn;

    @Column(name = "NUMBER_OF_COPIES")
    private int numberOfCopies;

    @ManyToMany(targetEntity = AuthorJPA.class)
    @JoinTable(name = "AUTHOR_BOOK", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(targetEntity = KeywordJPA.class)
    @JoinTable(name = "KEYWORDS_BOOK", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "KEYWORDS_ID"))
    private List<Keyword> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "book", targetEntity = RequestJPA.class)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "book", targetEntity = CommentJPA.class)
    private List<Comment> comments = new ArrayList<>();

    BookJPA() {
        // USED By JPA
    }

    /**
     * Constructor of the class.
     * 
     * @param name
     *            The name of the book.
     * @param isbn
     *            The ISBN of the book.
     * @param publisher
     *            The publisher of the book.
     * @param authors
     *            The authors of the book.
     * @param yearPublished
     *            The year in which the book has been published.
     * @param categoryId
     *            The category of the book.
     * @param languageId
     *            The language of the book.
     * @param registeredBy
     *            The person who has registered the book.
     */
    public BookJPA(final String name, final String isbn, final Publisher publisher, final List<Author> authors,
            final int yearPublished, final long categoryId, final long languageId, final User registeredBy) {
        assert publisher != null;
        assert authors != null;
        assert yearPublished > 0;
        assert name != null;
        assert isbn != null;
        assert categoryId > 0;
        assert languageId > 0;
        assert registeredBy != null;
        this.publisher = publisher;
        this.authors = authors;
        this.yearPublished = yearPublished;
        this.name = name;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.languageId = languageId;
        this.registeredBy = registeredBy;
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void addRequest(Request req) {
        requests.add(req);
    }

    @Override
    public int getEdition() {
        return edition;
    }

    @Override
    public void setEdition(final int edition) {
        this.edition = edition;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public byte[] getCoverPhoto() {
        return coverPhoto;
    }

    @Override
    public void setCoverPhoto(final byte[] coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    @Override
    public long getCategoryId() {
        return categoryId;
    }

    @Override
    public long getLanguageId() {
        return languageId;
    }

    @Override
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    @Override
    public void setNumberOfCopies(final int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public User getRegisteredBy() {
        return registeredBy;
    }

    @Override
    public long getBookId() {
        return bookId;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public List<Keyword> getKeywords() {
        return keywords;
    }
}
