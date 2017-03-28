package com.csc.booklibrary.services.validators;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.validators.InputValidator;

/**
 * Validator for User data. Validates the fields of a CreateBookDTO object.
 * 
 * @author adimitrov4
 *
 */
public class BookValidator extends InputValidator {
    public static final String NAME = "name";
    public static final String ISBN = "isbn";
    public static final String YEAR = "yearPublished";

    private static final Map<String, Predicate<CreateBookDTO>> validationOf = new LinkedHashMap<>();
    static {
        validationOf.put(NAME, book -> validateName(book.getName()));
        validationOf.put(ISBN, book -> validateISBN(book.getIsbn()));
        validationOf.put(YEAR, book -> validateYear(book.getYearPublished()));
    }

    private BookValidator() {

    }

    /**
     * Validates all fields of Book.
     * 
     * @param book
     *            CreateBookDTO object that needs field validation.
     */
    public static void validate(final CreateBookDTO book) {

        assert book != null;

        validationOf.forEach((key, predicate) -> {
            if (!predicate.test(book)) {
                throw new InvalidFieldDataInputException(key);
            }
        });
    }

    /**
     * Validates a single field of Book.
     * 
     * @param field
     *            Field from the CreateBookDTO that needs validation. Must be
     *            from the defined constants.
     * @param user
     *            CreateBookDTO object that needs field validation.
     * 
     */
    public static void validateBy(final String field, final CreateBookDTO book) {
        assert book != null;
        assert field != null;
        if (!validationOf.get(field).test(book)) {
            throw new InvalidFieldDataInputException(field);
        }
    }

}
