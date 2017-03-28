package com.csc.booklibrary.services.exceptions;

/**
 * Thrown to indicate that there is no such request.
 * @author lbosilkov
 *
 */
public class NoSuchRequestException extends RuntimeException {
    private final long requestId;
    private static final long serialVersionUID = -8704841720679650014L;

    /**
     * Constructor
     * @param requestId id of the request which wasn't found.
     */
    public NoSuchRequestException(final long requestId) {
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
