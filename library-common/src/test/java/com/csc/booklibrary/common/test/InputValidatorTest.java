package com.csc.booklibrary.common.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.csc.booklibrary.validators.InputValidator;

/**
 * 
 * @author adimitrov4
 *
 *         Unit test for UserInputValidator
 */
public class InputValidatorTest {

    /**
     * tests for checkSingleName(String)
     */

    @Test
    public void singleNameCorectInput() {
        assertTrue(InputValidator.validateName("Ivan"));
    }

    @Test
    public void singleNameIncorectInput() {
        assertFalse(InputValidator.validateName("Iv an3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void singleNameNullInput() {
        InputValidator.validateName(null);
    }

    /**
     * test for multy names
     */
    @Test
    public void multyNameCorectInput() {
        assertTrue(InputValidator.validateName("Ivan-Ivanov i. Ivanov"));
    }

    @Test
    public void multyNameIncorectInput() {
        assertFalse(InputValidator.validateName("Ivan-Iva123nov i. Ivanov"));
    }

    /**
     * test for checkEmail(String)
     */
    @Test
    public void emailCorectInput() {
        assertTrue(InputValidator.validateEmail("a1a@a2a.aa"));
    }

    @Test
    public void emailIncorectInput() {
        assertFalse(InputValidator.validateEmail("a@a@aa.a.a"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void emailNullInput() {
        InputValidator.validateEmail(null);
    }

    /**
     * tests for checkUsername(String)
     */
    @Test
    public void usernameCorectInput() {
        assertTrue(InputValidator.validateUsername("us3r-N4_me"));
    }

    @Test
    public void usernameIncorectInput() {
        final List<String> incorrect = new ArrayList<>();
        incorrect.add("us@3rN4!me");
        incorrect.add("a1");
        for (final String s : incorrect) {
            assertFalse(InputValidator.validateUsername(s));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void usernameNullInput() {
        InputValidator.validateUsername(null);
    }

    /**
     * tests for checkIsbn(String)
     */
    @Test
    public void isbnCorrectInpitEndWithX() {
        assertTrue(InputValidator.validateISBN("123-12345-12445-X"));
    }

    @Test
    public void isbnCorrectInpitEndWitchDigits() {
        assertTrue(InputValidator.validateISBN("123-12345-12445-2345"));
    }

    @Test
    public void isbnIncorrectInput() {
        final List<String> incorectIsbn = new ArrayList<>();
        incorectIsbn.add("123-12345-12445-123x");
        incorectIsbn.add("1-2323--123");
        incorectIsbn.add("1-2323-12312-Xx");
        incorectIsbn.add("1-2323-23dasd-123");
        incorectIsbn.add("1-2323-23-");
        for (final String s : incorectIsbn) {
            assertFalse(InputValidator.validateISBN(s));
        }
    }

    /**
     * tests for checkPhoneNumber(String)
     */
    @Test
    public void phoneNumberCorectInput() {
        assertTrue(InputValidator.validatePhone("+123456"));
    }

    @Test
    public void phoneNumberIncorectInput() {
        assertFalse(InputValidator.validatePhone("ss123a456vbv"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void phoneNumberNullInput() {
        InputValidator.validatePhone(null);
    }

    /**
     * test for checkPassword(String)
     */
    @Test
    public void passwordCorectInput() {
        assertTrue(InputValidator.validatePassword("p4ssW0rd"));
    }

    @Test
    public void passwordIncorectInput() {
        assertFalse(InputValidator.validatePassword("p@ssW()rd"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void passwordNullInput() {
        InputValidator.validatePassword(null);
    }

}