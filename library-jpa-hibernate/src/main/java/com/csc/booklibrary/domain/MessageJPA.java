package com.csc.booklibrary.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.User;

/**
 * @see Message
 *
 * @author mduhovnikov
 *
 */
@Entity
@Table(name = "MESSAGE")
@SequenceGenerator(name = "Message", sequenceName = "MESSAGE_SEQ")
@NamedQuery(name = "ql.message.byreceivinguser", query = "select m from MessageJPA m where m.toUser = :toUser order by m.messageDate desc")
public class MessageJPA implements Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Message")
    @Column(name = "MESSAGE_ID")
    private long messageId;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    @Column(name = "MESSAGE_DATE")
    private LocalDateTime messageDate;

    @Column(name = "MESSAGE_TEXT")
    private String messageText;

    @Column(name = "READ_DATE")
    private LocalDateTime readDate;

    @ManyToOne(targetEntity = UserJPA.class)
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    MessageJPA() {
        // Used by JPA framework
    }

    /**
     * Constructor.
     *
     * @param toUser
     *            Receiving user entity.
     * @param messageText
     *            Message contents.
     * @param fromUser
     *            Sending user entity.
     */
    public MessageJPA(final User toUser, final String messageText, final User fromUser) {
        assert toUser != null;
        assert messageText != null;
        assert fromUser != null;
        this.toUser = toUser;
        this.messageText = messageText;
        this.fromUser = fromUser;
        this.messageDate = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getReadDate() {
        return readDate;
    }

    @Override
    public void setReadDate(final LocalDateTime readDate) {
        this.readDate = readDate;
    }

    @Override
    public long getMessageId() {
        return messageId;
    }

    @Override
    public User getToUser() {
        return toUser;
    }

    @Override
    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    @Override
    public String getMessageText() {
        return messageText;
    }

    @Override
    public User getFromUser() {
        return fromUser;
    }

}
