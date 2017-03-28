package com.csc.booklibrary.services.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Contains information for message.
 *
 * @author lbosilkov
 *
 */
public class MessagesDTO implements Serializable {

    private static final long serialVersionUID = 1061369174856576967L;
    private final long messageID;
    private final UserDTO receiver;
    private final UserDTO fromUser;
    private final String messageText;
    private final LocalDateTime messageDate;
    private final LocalDateTime messageDateRead;

    /**
     * Constructor.
     *
     * @param messageID
     *            Id of the message.
     * @param receiver
     *            UserDTO object holds information about the receiver of the
     *            message.
     * @param fromUser
     *            UserDTO object holds information about the sender of the
     *            message.
     * @param messageText
     *            String contains text of the message.
     * @param messageDate
     *            Date object holds the date on which message has been sent.
     * @param messageDateRead
     *            Date object holds the date on which message has been read.
     */
    public MessagesDTO(final long messageID, final UserDTO receiver, final UserDTO fromUser, final String messageText,
            final LocalDateTime messageDate, final LocalDateTime messageDateRead) {
        this.messageID = messageID;
        this.receiver = receiver;
        this.fromUser = fromUser;
        this.messageText = messageText;
        this.messageDate = messageDate;
        this.messageDateRead = messageDateRead;
    }

    /**
     *
     * @return The id of the message.
     */
    public long getMessageID() {
        return messageID;
    }

    /**
     *
     * @return Object that contains information for the receiver of the message.
     */
    public UserDTO getReceiver() {
        return receiver;
    }

    /**
     *
     * @return Object that contains information for the sender of the message.
     */
    public UserDTO getFromUser() {
        return fromUser;
    }

    /**
     *
     * @return String that contains the text of the message.
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     *
     * @return Date object holds the date on which message has been sent.
     */
    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    /**
     *
     * @return Date object holds the date on which message has been read.
     */
    public LocalDateTime getMessageDateRead() {
        return messageDateRead;
    }
}
