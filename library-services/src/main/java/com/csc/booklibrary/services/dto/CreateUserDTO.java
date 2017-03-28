package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * Instances of this class manage the data transfer from the web
 * (AddUserServlet) to the service (UserService), needed for a User object to be
 * created.
 *
 * @author mvasilev
 *
 */

public class CreateUserDTO extends UserDTO implements Serializable {

    private static final long serialVersionUID = 6030136374135198312L;

    private final String password;
    
    /**
     * @param username The username of the user.
     * @param password The password of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email of the user.
     * @param phoneNumber The phone number of the user.
     * @param role The role of the user.
     */
    public CreateUserDTO(final String username, final String password, final String firstName, final String lastName,
            final String email, final String phoneNumber, final int role) {
        super(0, username, firstName, lastName, new UserRoleDTO(role, null), null, null, email, phoneNumber);
        this.password = password;
    }
    
    /**
     * @return The role of the user.
     */
    public int getRole() {
        return getUserRole().getUserRoleId();
    }
    
    /**
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
}
