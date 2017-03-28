package com.csc.booklibrary.services.exceptions;

/**
 * Thrown to indicate that the request has already been answered.
 * @author lbosilkov
 *
 */
public class RequestAlreadyAnsweredException extends RuntimeException {
    private static final long serialVersionUID = 9056095557428248771L;
    private final long requestId;

    /**
     * Constructor
     * @param requestId id of the request which has already been answered.
     */
    public RequestAlreadyAnsweredException(final long requestId) {
        this.requestId = requestId;
    }

    /**
     *
     * @return id of the request.
     */
    public long getRequestId() {
        return requestId;
    }
}
