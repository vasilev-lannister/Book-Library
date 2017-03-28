package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDateTime;

/**
 * This class has information about the messages sent to users. The fields
 * describe who sent the message, who received it, what was its content, when it
 * was received and when it was read.
 *
 * @author mvitanov
 *
 */
public interface Message {

    /**
     * @return Date when the message was read. Null if not read.
     */
    LocalDateTime getReadDate();

    /**
     * Set the date when the message was read.
     *
     * @param readDate
     *            Date when the message was read.
     */
    void setReadDate(LocalDateTime readDate);

    /**
     * @return DB id of the message.
     */
    long getMessageId();

    /**
     * @return Receiving User entity.
     */
    User getToUser();

    /**
     * @return Date when the message was sent.
     */
    LocalDateTime getMessageDate();

    /**
     * @return Message content.
     */
    String getMessageText();

    /**
     * @return Sending User entity.
     */
    User getFromUser();
}