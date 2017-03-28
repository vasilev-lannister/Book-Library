package com.csc.booklibrary.services.interfaces;

import java.util.List;

import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;

/**
 * Interface for a service that gets all the Users and transfers data from the
 * persistence layer to the web layer.
 *
 * @author adimitrov4
 *
 */

public interface UserService {
    /**
     * Gets all Users and converts them to UserDTO
     *
     * @return Returns a list of Users in the form of UserDTO.
     */
    List<UserDTO> getUsers();

    /**
     * Gets a CreateUserDTO object, converts it to User object and adds it in
     * the Database.
     *
     * @param createUserDTO
     */
    void addUser(CreateUserDTO createUserDTO);

    /**
     * Searches in the DB for a user with the given username. If found converts
     * it to UserDTO and returns it.
     *
     * @param username
     * @return the created UserDTO object.
     */
    UserDTO getUserByUserName(String username);

    /**
     * Creates a UserRoleDTO object by an ID.
     *
     * @param userRoleId
     * @return Returns the created UserRoleDTO object.
     */
    UserRoleDTO getUserRoleById(int userRoleId);

    /**
     * Gets all the user roles.
     * 
     * @return A list of UserRoleDTO objects.
     */
    List<UserRoleDTO> getUserRoles();
}
