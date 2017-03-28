package com.csc.booklibrary.services.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class has information about requests made by users. It has fields for
 * the user who made the request, the book which was requested, the type of the
 * request, the date on which the request was made, the admin who processed the
 * request, a comment by the requesting user, a reason for the rejection of the
 * request (if it was rejected) and the date on which it was answered.
 *
 * @author mvitanov
 *
 */
public class RequestDTO implements Serializable {

    /**
     * Default serializable id.
     */
    private static final long serialVersionUID = -691598393416961404L;

    private final long requestId;
    private final UserDTO byUser;
    private final BookDTO forBook;
    private final RequestTypeDTO type;
    private final LocalDateTime dateMade;
    private final UserDTO admin;
    private final String comment;
    private final RejectReasonDTO rejectReason;
    private final LocalDateTime dateAnswered;

    /**
     * /**
     *
     * @param requestId
     *            Id of the request in the database.
     * @param byUser
     *            DTO object for the user who made the request.
     * @param forBook
     *            DTO object for the book content that is being requested.
     * @param type
     *            DTO object for the type of the request.
     * @param dateMade
     *            The date when the request was made.
     * @param admin
     *            The admin who processed the request. Can be null.
     * @param comment
     *            Comment accompanying the request. Can be null.
     * @param rejectReason
     *            DTO object for the reason the request was rejected. Can be
     *            null.
     * @param dateAnswered
     *            The date when the request was answered. Can be null
     */

    public RequestDTO(final long requestId, final UserDTO byUser, final BookDTO forBook,
            final RequestTypeDTO type, final LocalDateTime dateMade, final UserDTO admin, final String comment,
            final RejectReasonDTO rejectReason, final LocalDateTime dateAnswered) {
        this.requestId = requestId;
        this.byUser = byUser;
        this.forBook = forBook;
        this.type = type;
        this.dateMade = dateMade;
        this.admin = admin;
        this.comment = comment;
        this.rejectReason = rejectReason;
        this.dateAnswered = dateAnswered;
    }

    /**
     * @return Id of the request in the database.
     */
    public long getId() {
        return requestId;
    }

    /**
     * @return DTO for the user who made the request.
     */
    public UserDTO getUser() {
        return byUser;
    }

    /**
     * @return DTO object for the book content that is being requested.
     */
    public BookDTO getBook() {
        return forBook;
    }

    /**
     * @return DTO object for the type of the request.
     */
    public RequestTypeDTO getType() {
        return type;
    }

    /**
     * @return The date when the request was made.
     */
    public LocalDateTime getDateMade() {
        return dateMade;
    }

    /**
     * Can return null.
     *
     * @return The admin who processed the request.
     */
    public UserDTO getAdmin() {
        return admin;
    }

    /**
     * Can return null.
     *
     * @return Comment accompanying the request.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Can return null.
     *
     * @return DTO object for the reason the request was rejected. Can be null.
     */
    public RejectReasonDTO getRejectReason() {
        return rejectReason;
    }

    /**
     * Can return null.
     *
     * @return The date when the request was answered.
     */
    public LocalDateTime getDateAnswered() {
        return dateAnswered;
    }

}
