package com.csc.booklibrary.persistence.interfaces;

import java.util.List;

/**
 * This class has information about the possible reject reasons when requesting
 * a book. The fields contain the text which is the reason why the request is
 * rejected.
 *
 * @author mduhovnikov
 *
 */
public interface RejectReason {

    /**
     * @return The reason why the request is rejected.
     */
    String getRejectReasonText();

    /**
     * @return The id of the reject reason.
     */
    long getRejectReasonId();

    /**
     * Adds another reason why the request might be rejected.
     *
     * @param rejectReasonText
     *            The text for the reason.
     */
    void setRejectReasonText(String rejectReasonText);

    /**
     * @return All requests rejected for this reason.
     */
    List<Request> getRequests();
}