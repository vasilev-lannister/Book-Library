package com.csc.booklibrary.services.interfaces;

import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.WrongPasswordException;

/**
 * Interface for a service that assists in logging a user in the web layer.
 * 
 * @author mvitanov
 *
 */
public interface LoginService {

    /**
     * A method that checks whether the provided username and password match in
     * the persistence layer.
     * 
     * @param username
     *            Username provided by the user.
     * @param password
     *            Password provided by the user.
     * @return DTO object with the information of the user who logged in.
     * @throws NoSuchUsernameException
     *             If there is no user with the provided username in the
     *             persistence layer.
     * @throws WrongPasswordException
     *             If the provided username and password do not match.
     */
    UserDTO performLogin(final String username, final String password);

}
