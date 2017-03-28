package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the user roles.
 *
 * @author mduhovnikov
 *
 */
public class UserRoleDTO implements Serializable {

    /**
     * Generated ID
     */
    private static final long serialVersionUID = -3061210417053395919L;
    private int userRoleId;
    private String userRoleName;

    /**
     * @param userRoleId
     *            The id of the user role.
     * @param userRoleName
     *            The name of the user role.
     */
    public UserRoleDTO(final int userRoleId, final String userRoleName) {
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
    }

    /**
     * @return The id of the user role.
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @return The name of the user role.
     */
    public String getUserRoleName() {
        return userRoleName;
    }
}
