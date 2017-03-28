package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.exceptions.WrongPasswordException;
import com.csc.booklibrary.services.impl.LoginServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public final class LoginTest {
    private final LoginServiceImplLogic logic = new LoginServiceImplLogic();
    private final UserRepository userRepository = new UserRepositoryMock();
    private String username;
    private String password;

    @Test(expected = NoSuchUsernameException.class)
    public void usernameDoesNotExist() {
        // given
        username = "Jon Snow";
        password = "Ikn0wnoth1ng";

        // when
        logic.login(username, password, userRepository);
    }

    @Test(expected = WrongPasswordException.class)
    public void passwordDoesNotMatch() {
        // given
        username = "user1";
        password = "Ikn0wnoth1ng";

        // when
        logic.login(username, password, userRepository);
    }

    @Test
    public void successfulLogin() {
        // given
        username = "user1";
        password = "123asd";

        // when
        logic.login(username, password, userRepository);

        // then
        assertEquals(userRepository.findUserByUsername(username).getPassword(), password);
    }
}
