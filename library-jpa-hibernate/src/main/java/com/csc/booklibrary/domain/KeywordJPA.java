package com.csc.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Keyword;

/**
 * @see Keyword
 *
 * @author mduhovnikov
 *
 */
@Entity
@SequenceGenerator(name = "Keywords", sequenceName = "KEYWORDS_SEQ")
@Table(name = "KEYWORDS")
public class KeywordJPA implements Keyword {
    @Id
    @Column(name = "KEYWORDS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Keywords")
    private long keywordsId;

    private String keyword;

    @ManyToMany(mappedBy = "keywords", targetEntity = BookJPA.class)
    private List<Book> booksContaining = new ArrayList<>();

    KeywordJPA() {
        // Used By JPA
    }

    /**
     * Constructor of the class.
     * 
     * @param keywordsId
     *            The id of the keyword.
     * @param keyword
     *            The particular keyword.
     */
    public KeywordJPA(final long keywordsId, final String keyword) {
        this.keywordsId = keywordsId;
        this.keyword = keyword;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    @Override
    public long getKeywordsId() {
        return keywordsId;
    }

    @Override
    public List<Book> getBooksContaining() {
        return booksContaining;
    }
}
