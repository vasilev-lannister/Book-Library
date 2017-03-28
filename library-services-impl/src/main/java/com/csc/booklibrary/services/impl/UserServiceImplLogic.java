package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csc.booklibrary.exceptions.UserAlreadyExistsException;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.persistence.interfaces.UserRole;
import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoSuchUserRoleException;
import com.csc.booklibrary.services.validators.UserValidator;

public class UserServiceImplLogic {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImplLogic.class.getName());

    public UserDTO getUserByUserName(final String username, final UserRepository userRepository) {
        final User user = userRepository.findUserByUsername(username);
        if (user != null)
            return DTOConverter.convertUser(user);
        return null;
    }

    public List<UserDTO> getUsers(final UserRepository userRepository) {
        final List<UserDTO> usersDtoList = new ArrayList<>();

        for (final User user : userRepository.findUsers()) {
            usersDtoList.add(DTOConverter.convertUser(user));
        }

        return usersDtoList;
    }

    public User addUser(final CreateUserDTO createUserDTO, final UserRepository userRepository) {

        UserValidator.validate(createUserDTO);

        UserRole role = null;
        for (final UserRole userRole : UserRole.values()) {
            if (userRole.getRoleId() == createUserDTO.getRole()) {
                role = userRole;
                break;
            }
        }
        if (role == null) {
            throw new NoSuchUserRoleException(createUserDTO.getRole());
        }

        for (final User user : userRepository.findUsers()) {
            if (user.getUsername().equals(createUserDTO.getUsername())) {
                throw new UserAlreadyExistsException(user.getUsername());
            }
        }

        try {
            final User user = userRepository.createUser(createUserDTO.getUsername(), createUserDTO.getPassword(),
                    createUserDTO.getFirstName(), createUserDTO.getLastName(), role, createUserDTO.getEmail());
            user.setPhone(createUserDTO.getPhone());
            return user;
        } catch (final UserAlreadyExistsException e) {
            LOGGER.log(Level.INFO, "User with username " + createUserDTO.getUsername() + " already exists.", e);
            throw new UserAlreadyExistsException(createUserDTO.getUsername());
        }

    }
}
