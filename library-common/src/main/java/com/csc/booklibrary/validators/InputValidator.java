package com.csc.booklibrary.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    protected InputValidator() {
    }

    public static boolean validateName(final String names) {
        checkForNull(names);
        return validate(names, "[a-zA-Z\\s.’\\-,]+");
    }

    public static boolean validatePassword(final String password) {
        checkForNull(password);
        return validate(password, "([a-zA-Z0-9]+){6,}");
    }

    public static boolean validatePhone(final String phone) {
        checkForNull(phone);
        return "".equals(phone) ? true : validate(phone, "[//+]?[0-9]+");
    }

    public static boolean validateUsername(final String username) {
        checkForNull(username);
        return validate(username, "([a-zA-Z0-9_-]+){3,}");
    }

    public static boolean validateEmail(final String mail) {
        checkForNull(mail);
        return validate(mail, "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
    }

    public static boolean validateISBN(final String isbn) {
        checkForNull(isbn);
        return validate(isbn, "([0-9]+)(-{1}[0-9]+){2}(-{1}([0-9]+|[Xx]))");
    }

    public static boolean validateYear(final int year) {
        return year <= 9999 && year > 999;
    }

    private static boolean validate(final String data, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    private static void checkForNull(final String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null value parameter");
        }
    }

}
