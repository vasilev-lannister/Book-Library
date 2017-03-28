package com.csc.booklibrary.services.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class has information about the users with their personal details.
 * 
 * @author mduhovnikov
 *
 */
public class UserDTO implements Serializable {

    /**
     * Generated ID
     */
    private static final long serialVersionUID = 3015294851238725065L;
    private final long id;
    private final UserRoleDTO userRole;
    private final String username, firstName, lastName, email, phone;
    private final LocalDate dateReg, inactive;

    /**
     * @param id
     *            The id of the user.
     * @param username
     *            The username of the user.
     * @param firstName
     *            The first name of the user.
     * @param lastName
     *            The last name of the user.
     * @param userRole
     *            The user role which the user has.
     * @param dateReg
     *            The date on which the user has registered.
     * @param inactive
     *            The date on which the user has become inactive.
     * @param email
     *            The email of the user.
     * @param phone
     *            The phone of the user.
     */
    public UserDTO(final long id, final String username, final String firstName, final String lastName,
            final UserRoleDTO userRole, final LocalDate dateReg, final LocalDate inactive, final String email,
            final String phone) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.dateReg = dateReg;
        this.inactive = inactive;
        this.email = email;
        this.phone = phone;
    }

    /**
     * @return The id of the user.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The user role which the user has.
     */
    public UserRoleDTO getUserRole() {
        return userRole;
    }

    /**
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The phone of the user.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return The date on which the user has registered.
     */
    public LocalDate getDateReg() {
        return dateReg;
    }

    /**
     * @return The date on which the user has become inactive.
     */
    public LocalDate getInactive() {
        return inactive;
    }
}
