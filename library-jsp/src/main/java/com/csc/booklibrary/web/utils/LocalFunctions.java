package com.csc.booklibrary.web.utils;

import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;

/**
 * A class that contains functions used in several places.
 * 
 * @author mduhovnikov
 *
 */
public final class LocalFunctions {

    private LocalFunctions() {
    }

    /**
     * Checks the id argument and throws exception if it is null or empty.
     * 
     * @param id
     * @throws InvalidIdParameterException
     */
    public static void validateId(final String id) {
        if (!checkIfNullOrEmpty(id)) {
            throw new InvalidIdParameterException(id);
        }
    }

    /**
     * Attempts to parse the string argument to long. Throws exception if
     * parameter cannot be parsed.
     * 
     * @param value
     * @return Long value of the argument.
     * @throws InvalidIdParameterException
     */
    public static long stringToLong(final String value) {
        try {
            return Long.parseLong(value);
        } catch (final NumberFormatException e) {
            throw new InvalidIdParameterException(value);
        }
    }

    /**
     * Attempts to parse the string argument to int. Throws exception if
     * parameter cannot be parsed.
     * 
     * @param value
     * @return Int value of the argument.
     * @throws InvalidIdParameterException
     */
    public static int stringToInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (final NumberFormatException e) {
            throw new InvalidIdParameterException(value);
        }
    }

    /**
     * Checks whether the parameter is non-empty and non-null.
     * 
     * @param parameter
     *            value to be checked
     * @return true if the parameter is non-empty and non-null, false otherwise.
     */
    public static boolean checkIfNullOrEmpty(final String parameter) {
        return parameter != null && !"".equals(parameter); //$NON-NLS-1$
    }
}
