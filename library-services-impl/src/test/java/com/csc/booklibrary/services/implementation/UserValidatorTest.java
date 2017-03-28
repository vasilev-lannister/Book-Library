package com.csc.booklibrary.services.implementation;

import org.junit.Test;

import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.validators.UserValidator;

public class UserValidatorTest {

    @Test
    public void validateAll() {
        UserValidator.validate(
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByUsername() {
        UserValidator.validateBy(UserValidator.USERNAME,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByPasword() {
        UserValidator.validateBy(UserValidator.PASSWRD,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByFirstName() {
        UserValidator.validateBy(UserValidator.FIRST_NAME,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByLastName() {
        UserValidator.validateBy(UserValidator.LAST_NAME,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByMail() {
        UserValidator.validateBy(UserValidator.EMAIL,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test
    public void validateByPhone() {
        UserValidator.validateBy(UserValidator.PHONE_NUMBER,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongUsername() {
        UserValidator.validateBy(UserValidator.USERNAME,
                new CreateUserDTO("us3r-N4m e", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongPassword() {
        UserValidator.validateBy(UserValidator.PASSWRD,
                new CreateUserDTO("us3r-N4me", "p4ss W0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongFirstName() {
        UserValidator.validateBy(UserValidator.FIRST_NAME,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "I123van", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongLastName() {
        UserValidator.validateBy(UserValidator.LAST_NAME,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Iva123 nov", "vankata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongEmail() {
        UserValidator.validateBy(UserValidator.EMAIL,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "va.nk@ata1@mail.com", "123456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateWrongPhone() {
        UserValidator.validateBy(UserValidator.PHONE_NUMBER,
                new CreateUserDTO("us3r-N4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "12asd3456", 1));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateAllWrong() {
        UserValidator.validate(
                new CreateUserDTO("us3r-N@4me", "p4ssW0rd", "Ivan", "Ivanov", "vankata1@mail.com", "123456", 1));
    }

}
