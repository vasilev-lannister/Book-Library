package com.csc.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;

/**
 * @see Author
 *
 * @author mvasilev
 *
 */
@Entity
@Table(name = "AUTHOR")
@SequenceGenerator(name = "Author", sequenceName = "AUTHOR_SEQ")
@NamedQuery(name = "ql.author.authorbyname", query = "select a from AuthorJPA a where a.name = :name")
public class AuthorJPA implements Author {
    @Id
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Author")
    private long authorId;

    private String name;

    private String nationality;

    private String nickname;

    @ManyToMany(mappedBy = "authors", targetEntity = BookJPA.class)
    private List<Book> booksWritten = new ArrayList<>();

    AuthorJPA() {
        // Used By JPA
    }

    /**
     * Constructor of the class.
     * 
     * @param name
     *            The name of the author.
     */
    public AuthorJPA(final String name) {
        assert authorId > 0;
        assert name != null;
        this.name = name;
    }

    @Override
    public long getAuthorId() {
        return authorId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    @Override
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    @Override
    public List<Book> getBooksWritten() {
        return booksWritten;
    }

    @Override
    public void setBooksWritten(final List<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }
}
