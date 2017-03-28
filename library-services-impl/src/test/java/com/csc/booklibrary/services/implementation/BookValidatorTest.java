package com.csc.booklibrary.services.implementation;

import org.junit.Test;

import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.validators.BookValidator;

public class BookValidatorTest {

    @Test
    public void validateAll() {
        BookValidator.validate(new CreateBookDTO("Learn Java", "123-12345-12445-X", 2007, 0, 0, 0, 0));
    }

    @Test
    public void validateByName() {
        BookValidator.validateBy(BookValidator.NAME,
                new CreateBookDTO("Learn Java", "123-12345-12445-X", 0, 0, 0, 0, 0));
    }

    @Test
    public void validateByISBN() {
        BookValidator.validateBy(BookValidator.ISBN,
                new CreateBookDTO("Learn Java", "123-12345-12445-X", 0, 0, 0, 0, 0));
    }

    @Test
    public void validateByYear() {
        BookValidator.validateBy(BookValidator.YEAR,
                new CreateBookDTO("Learn Java", "123-12345-12445-X", 1999, 0, 0, 0, 0));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateAllWrong() {
        BookValidator.validate(new CreateBookDTO("Learn Java", "123-12ggg345-12445-123X", 0, 0, 0, 0, 0));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateByNameWrong() {
        BookValidator.validateBy(BookValidator.NAME,
                new CreateBookDTO("L-e!arn Java", "123-12345-12445-X", 0, 0, 0, 0, 0));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateByISBNWrogn() {
        BookValidator.validateBy(BookValidator.ISBN,
                new CreateBookDTO("Learn Java", "123-12ggg345-12445-123X", 0, 0, 0, 0, 0));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateByYearWrongLessThan4Digits() {
        BookValidator.validateBy(BookValidator.YEAR,
                new CreateBookDTO("Learn Java", "123-12345-12445-X", 123, 0, 0, 0, 0));
    }

    @Test(expected = InvalidFieldDataInputException.class)
    public void validateByYearWrongMoreThan4Digits() {
        BookValidator.validateBy(BookValidator.YEAR,
                new CreateBookDTO("Learn Java", "123-12345-12445-X", 12345, 0, 0, 0, 0));
    }

}
