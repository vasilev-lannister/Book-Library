package com.csc.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Publisher;

/**
 * @see Publisher
 *
 * @author mduhovnikov
 *
 */
@Entity
@Table(name = "PUBLISHER")
@SequenceGenerator(name = "Publisher", sequenceName = "PUBLISHER_SEQ")
@NamedQuery(name = "ql.publisher.publisherbyname", query = "select p from PublisherJPA p where p.name = :name")

public class PublisherJPA implements Publisher {
    @Id
    @Column(name = "PUBLISHER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Publisher")
    private long publisherId;

    private String name;

    @OneToMany(mappedBy = "publisher", targetEntity = BookJPA.class)
    List<Book> books = new ArrayList<>();

    PublisherJPA() {
        // Used by JPA
    }

    /**
     * Constructor of the class.
     *
     * @param name
     *            The name of the publisher.
     */
    public PublisherJPA(final String name) {
        assert publisherId > 0;
        assert name != null;
        this.name = name;
    }

    @Override
    public long getPublisherId() {
        return publisherId;
    }

    @Override
    public String getName() {
        return name;
    }
}
