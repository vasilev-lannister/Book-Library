package com.csc.booklibrary.web.exceptions;

/**
 * Exception thrown when a operation cannot be fulfilled.
 *
 * @author lbosilkov
 *
 */
public class InvalidProcessOperationException extends RuntimeException{
    private static final long serialVersionUID = 5714034075479111445L;
    final String excpetionMessage;

    /**
     * Constructor.
     *
     * @param excpetionMessage message that describe the problem.
     */
    public InvalidProcessOperationException(String excpetionMessage) {
        this.excpetionMessage = excpetionMessage;
    }

    /**
     * Returns description of the problem.
     *
     * @return excpetionMessage
     */
    public String getExcpetionMessage() {
        return excpetionMessage;
    }

}
