package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.persistence.interfaces.UserRole;
import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

    private final TransactionHandler transactionHandler;
    private final UserServiceImplLogic userServiceImplLogic = new UserServiceImplLogic();

    public UserServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    /**
     * @author dgetsov
     * @param username
     *            The username of the target user
     * @return userDTO object with all information about the user
     * @throws NoSuchUsernameException
     *             if the target user doesn't exist
     *
     */
    @Override
    public UserDTO getUserByUserName(final String username) {
        return transactionHandler.execute(servicesFactory -> {
            return userServiceImplLogic.getUserByUserName(username, servicesFactory.findService(UserRepository.class));
        });
    }

    @Override
    public List<UserDTO> getUsers() {

        return transactionHandler.execute(servicesFactory -> {
            return userServiceImplLogic.getUsers(servicesFactory.findService(UserRepository.class));
        });
    }

    @Override
    public void addUser(final CreateUserDTO createUserDTO) {

        transactionHandler.execute(servicesFactory -> {
            userServiceImplLogic.addUser(createUserDTO, servicesFactory.findService(UserRepository.class));
            return null;
        });
    }

    @Override
    public UserRoleDTO getUserRoleById(final int userRoleId) {

        return new UserRoleDTO(userRoleId, UserRole.getRoleFromId(userRoleId));
    }

    @Override
    public List<UserRoleDTO> getUserRoles() {
        final List<UserRoleDTO> roles = new ArrayList<>();
        for (final UserRole role : UserRole.values()) {
            roles.add(new UserRoleDTO(role.getRoleId(), role.getRoleName()));
        }
        return roles;

    }

}
