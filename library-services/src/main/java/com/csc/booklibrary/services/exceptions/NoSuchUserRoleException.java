package com.csc.booklibrary.services.exceptions;

/**
 * Exception thrown when an user role cannot be found by id in the persistence
 * layer. Stores the id of that user role.
 * 
 * @author mduhovnikov
 *
 */
public class NoSuchUserRoleException extends RuntimeException {

    private static final long serialVersionUID = -7867079407512681521L;
    private final int roleId;

    /**
     * Constructor.
     * 
     * @param roleId
     *            id of the user role which wasn't found.
     */
    public NoSuchUserRoleException(final int roleId) {
        this.roleId = roleId;
    }
    
    /**
    *
    * @return id of the user role which wasn't found.
    */
    public int getRoleId() {
        return roleId;
    }

}
