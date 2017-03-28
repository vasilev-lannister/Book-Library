package com.csc.booklibrary.services.impl;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.WrongPasswordException;
import com.csc.booklibrary.services.interfaces.LoginService;

public final class LoginServiceImplLogic {

    public LoginServiceImplLogic() {
        // empty constructor because there is no state to set
    }

    /**
     * @see LoginService#performLogin
     * @param userRepository
     *            Repository used to find the user.
     */
    public UserDTO login(final String username, final String password, final UserRepository userRepository) {
        assert username != null;
        assert password != null;
        assert userRepository != null;
        final User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new NoSuchUsernameException(username);
        }
        if (!password.equals(user.getPassword())) {
            throw new WrongPasswordException(username, password);
        }
        return DTOConverter.convertUser(user);
    }
}
