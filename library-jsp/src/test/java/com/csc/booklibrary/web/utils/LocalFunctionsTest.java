package com.csc.booklibrary.web.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;

public class LocalFunctionsTest {

    @Test(expected = InvalidIdParameterException.class)
    public void validateNull() {
        // when
        LocalFunctions.validateId(null);
    }

    @Test(expected = InvalidIdParameterException.class)
    public void validateEmpty() {
        // when
        LocalFunctions.validateId(""); //$NON-NLS-1$
    }

    @Test(expected = InvalidIdParameterException.class)
    public void stringToLongWithNull() {
        // when
        LocalFunctions.stringToLong(null);
    }

    @Test(expected = InvalidIdParameterException.class)
    public void stringToLongWithString() {
        // when
        LocalFunctions.stringToLong("ab23fghf1"); //$NON-NLS-1$
    }

    @Test
    public void stringToLongSuccess() {
        // given
        final String value = "123"; //$NON-NLS-1$
        final long expected = 123L;

        // when
        final long actual = LocalFunctions.stringToLong(value);

        // then
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidIdParameterException.class)
    public void stringToIntWithNull() {
        // when
        LocalFunctions.stringToLong(null);
    }

    @Test(expected = InvalidIdParameterException.class)
    public void stringToIntWithString() {
        // when
        LocalFunctions.stringToInt("aet345ty"); //$NON-NLS-1$
    }

    @Test
    public void stringToIntSuccess() {
        // given
        final String value = "654"; //$NON-NLS-1$
        final int expected = 654;

        // when
        final int actual = LocalFunctions.stringToInt(value);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void checkNull() {
        // when
        final boolean actual = LocalFunctions.checkIfNullOrEmpty(null);

        // then
        assertEquals(false, actual);
    }

    @Test
    public void checkEmpty() {
        // when
        final boolean actual = LocalFunctions.checkIfNullOrEmpty(""); //$NON-NLS-1$

        // then
        assertEquals(false, actual);
    }

    @Test
    public void checkSuccess() {
        // when
        final boolean actual = LocalFunctions.checkIfNullOrEmpty("asdasd"); //$NON-NLS-1$

        // then
        assertEquals(true, actual);
    }
}
