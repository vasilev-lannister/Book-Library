package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csc.booklibrary.exceptions.UserAlreadyExistsException;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoSuchUserRoleException;
import com.csc.booklibrary.services.impl.UserServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public class UserServiceImplLogicTest {

    final UserServiceImplLogic userService = new UserServiceImplLogic();
    private UserRepository userRepository;

    /**
     * Initializing objects with new mock repositories before each test.
     */
    @Before
    public void initialize() {
        userRepository = new UserRepositoryMock();
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testUserWithThatUsernameAlreadyExists() {

        // given
        final String existingUsername = "jonsnow";
        CreateUserDTO user1 = new CreateUserDTO(existingUsername, "jonsnow123", "Jon", "Snow", "jonsnow@got.com",
                "0987654321", 1);
        CreateUserDTO user2 = new CreateUserDTO(existingUsername, "jonsnow1234", "JonSecond", "SnowSecond",
                "jonsnow2@got.com", "0987654321", 1);

        userService.addUser(user1, userRepository);

        // when
        userService.addUser(user2, userRepository);
    }

    @Test(expected = NoSuchUserRoleException.class)
    public void testInvalidUserRole() {

        // given
        int invalidRole = 150;
        CreateUserDTO user = new CreateUserDTO("jonsnow", "jonsnow123", "Jon", "Snow", "jonsnow@got.com", "0987654321",
                invalidRole);

        // when
        userService.addUser(user, userRepository);
    }

    @Test
    public void testMissingUsername() {
        // given
        final String username = "username";

        // when
        Assert.assertNull(userService.getUserByUserName(username, userRepository));
    }

    @Test
    public void testSuccessfulFoundUser() {
        // given
        final String username = "Jo75";

        // when
        final UserDTO user = userService.getUserByUserName(username, userRepository);

        // then
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSuccessfulFoundUsers() {
        // when
        final List<UserDTO> users = userService.getUsers(userRepository);

        // then
        assertNotEquals(null, users);
    }

}
