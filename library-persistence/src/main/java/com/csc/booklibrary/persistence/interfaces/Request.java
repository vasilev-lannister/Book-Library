package com.csc.booklibrary.persistence.interfaces;

import java.time.LocalDateTime;

/**
 * This class has information about the requests made by users. The fields
 * describe the type of the request, the book content for which it applies, the
 * dates on which the request has been made and answered and the comment for the
 * request and the admin who has responded with the reject reason if there are
 * any.
 *
 * @author mduhovnikov
 *
 */
public interface Request {

    /**
     * @return The comment for the request if there is any.
     */
    String getRequestComment();

    /**
     * @return The id of the request.
     */
    long getRequestId();

    /**
     * Set a new id for the request.
     *
     * @param requestId
     *            The new id.
     */
    void setRequestId(long requestId);

    /**
     * @return The book for which the request has been made.
     */
    Book getBook();

    /**
     * @return The id of the type of the request.
     */
    int getTypeId();

    /**
     * Set a new type for the request.
     *
     * @param typeId
     *            The new type.
     */
    void setTypeId(int typeId);

    /**
     * @return The date on which the request has been made.
     */
    LocalDateTime getDateMade();

    /**
     * @return The admin who responded to the request.
     */
    User getAdmin();

    /**
     * Change the admin who responded.
     *
     * @param admin
     *            The new admin.
     */
    void setAdmin(User admin);

    /**
     * Add a new comment for the request.
     *
     * @param requestComment
     *            The new comment.
     */
    void setRequestComment(String requestComment);

    /**
     * @return The reason for which the request has been rejected.
     */
    RejectReason getRejectReason();

    /**
     * Change the reason for rejecting the request.
     *
     * @param rejectReason
     *            The new reason.
     */
    void setRejectReasonId(RejectReason rejectReason);

    /**
     * @return The date on which the request has been answered.
     */
    LocalDateTime getDateAnswered();

    /**
     * Add a date on which the request has been answered.
     *
     * @param dateAnswered
     *            The new date.
     */
    void setDateAnswered(LocalDateTime dateAnswered);

    /**
     * @return The user who has made the request.
     */
    User getUser();

    /**
     * @return The borrow corresponding to this request. Can return null.
     */
    Borrow getBorrow();
}
