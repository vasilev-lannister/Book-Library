package com.csc.booklibrary.services.validators;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.validators.InputValidator;

/**
 * Validator for User data. Validates the fields of a CreateUserDTO object.
 *
 * @author adimitrov4
 *
 */
public class UserValidator extends InputValidator {

    public static final String USERNAME = "username";
    public static final String PASSWRD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phoneNumber";

    private static final Map<String, Predicate<CreateUserDTO>> validationOf = new LinkedHashMap<>();
    static {
        validationOf.put(USERNAME, user -> validateUsername(user.getUsername()));
        validationOf.put(PASSWRD, user -> validatePassword(user.getPassword()));
        validationOf.put(FIRST_NAME, user -> validateName(user.getFirstName()));
        validationOf.put(LAST_NAME, user -> validateName(user.getLastName()));
        validationOf.put(EMAIL, user -> validateEmail(user.getEmail()));
        validationOf.put(PHONE_NUMBER, user -> validatePhone(user.getPhone()));

    }

    private UserValidator() {
    }

    /**
     * Validates all fields of User.
     *
     * @param user
     *            CreateUserDTO object that needs field validation.
     */
    public static void validate(final CreateUserDTO user) {

        assert user != null;

        validationOf.forEach((key, predicate) -> {
            if (!predicate.test(user)) {
                throw new InvalidFieldDataInputException(key);
            }
        });
    }

    /**
     * Validates a single field of User.
     *
     * @param field
     *            Field from the CreateUserDTO that needs validation. Must be
     *            from the defined constants.
     * @param user
     *            CreateUserDTO object that needs field validation.
     *
     */
    public static void validateBy(final String field, final CreateUserDTO user) {
        assert user != null;
        assert field != null;
        if (!validationOf.get(field).test(user)) {
            throw new InvalidFieldDataInputException(field);
        }
    }
}