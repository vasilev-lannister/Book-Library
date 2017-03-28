package com.csc.booklibrary;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.csc.booklibrary.exceptions.UserAlreadyExistsException;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.persistence.interfaces.UserRole;
import com.csc.booklibrary.repositories.TransactionHandlerJPA;

/**
 * This class tests the proper functionality of the UserRepository class.
 *
 * @author lbosilkov
 *
 */
public class UserRepositoryTest extends UnitilsJUnit4 {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library-jpa-hibernate");
    static TransactionHandler handler = new TransactionHandlerJPA(entityManagerFactory);

    /**
     * Find user by username and check the returned object's fields.
     */
    @Test
    @DataSet({ "user/createUserAndRole.xml", "clean.xml" })
    public void findByUsername() {
        final User user = handler.execute(service -> {
            final UserRepository userRepository = service.findService(UserRepository.class);
            return userRepository.findUserByUsername("ivan1");
        });
        assertPropertyLenientEquals("username", "ivan1", user);
        assertPropertyLenientEquals("password", "ivan123", user);
        assertPropertyLenientEquals("firstName", "ivan", user);
        assertPropertyLenientEquals("lastName", "ivanov", user);
        assertPropertyLenientEquals("userRoleId", 2, user);
        assertPropertyLenientEquals("email", "ivan1v@gmail.com", user);
    }

    /**
     * Try to find non existent user.
     */
    @Test()
    @DataSet("clean.xml")
    public void findNonExistentUser() {
        final User user = handler.execute(service -> {
            final UserRepository userRepository = service.findService(UserRepository.class);
            return userRepository.findUserByUsername("ivan2");
        });
        assertReflectionEquals(null, user);
    }

    /**
     * Create user and check the state of the database.
     */
    @Test
    @DataSet({ "user/createUserRole.xml", "clean.xml" })
    @ExpectedDataSet("user/expectedUser.xml")
    public void createUserCheckDatabase() {
        handler.execute(service -> {
            final UserRepository userRepository = service.findService(UserRepository.class);
            return userRepository.createUser("ivo1", "123", "Ivo", "I", UserRole.USER, "ivo@abv.bg");
        });

    }

    /**
     * Create user and check the state of the returned object.
     */
    @Test
    @DataSet({ "user/createUserRole.xml", "clean.xml" })
    public void createUserCheckReturnedObject() {
        final User user = handler.execute(service -> {
            final UserRepository userRepository = service.findService(UserRepository.class);
            return userRepository.createUser("ivo1", "123", "Ivo", "I", UserRole.USER, "ivo@abv.bg");
        });
        assertPropertyLenientEquals("username", "ivo1", user);
        assertPropertyLenientEquals("password", "123", user);
        assertPropertyLenientEquals("firstName", "Ivo", user);
        assertPropertyLenientEquals("lastName", "I", user);
        assertPropertyLenientEquals("userRoleId", 2, user);
        assertPropertyLenientEquals("email", "ivo@abv.bg", user);
    }

    /**
     * Try to create user, who is already created. RollbackException exception
     * is expected.
     */
    @Test(expected = RollbackException.class)
    @DataSet({ "user/createUserAndRole.xml", "clean.xml" })
    public void createExistentingUserAgain() {
        thrown.expect(RollbackException.class);
        thrown.expectCause(IsInstanceOf.<Throwable>instanceOf(UserAlreadyExistsException.class));
        handler.execute(service -> {
            final UserRepository userRepository = service.findService(UserRepository.class);
            return userRepository.createUser("ivan1", "ivan123", "ivan", "ivanov", UserRole.USER, "ivan1v@gmail.com");
        });
    }

    @Test
    @DataSet({ "user/createReceiverSenderAndRole.xml", "clean.xml" })
    @ExpectedDataSet("user/expectedMessage.xml")
    public void addMessage() {
        handler.execute(ServiceFactory -> {
            final UserRepository userRepository = ServiceFactory.findService(UserRepository.class);
            final User receiver = userRepository.findUserByUsername("Chris32");
            final User sender = userRepository.findUserByUsername("John123");
            return userRepository.addMessage(receiver, "Test Message", sender);
        });
    }

    @Test(expected = RollbackException.class)
    @DataSet({ "user/createReceiverSenderAndRole.xml", "clean.xml" })
    public void addEmptyMessage() {
        handler.execute(ServiceFactory -> {
            final UserRepository userRepository = ServiceFactory.findService(UserRepository.class);
            final User receiver = userRepository.findUserByUsername("Chris32");
            final User sender = userRepository.findUserByUsername("John123");
            return userRepository.addMessage(receiver, "", sender);
        });

    }

}
