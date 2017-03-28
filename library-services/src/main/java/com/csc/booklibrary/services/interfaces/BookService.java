package com.csc.booklibrary.services.interfaces;

import java.util.List;

import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.CategoryDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.dto.LanguageDTO;

/**
 * The role of this service class is to be the link between the Web module and
 * the current realization of the Book class. In order to do so it uses Data
 * Transfer Objects- BookDTO in this case.
 *
 * @author mvasilev, dgetsov
 *
 */
public interface BookService {

    /**
     * Adds a new book.
     *
     * @param book
     *            the information for the book
     * @param publisherName
     *            the name of the publisher of the book
     * @param authorsNames
     *            the names of the authors of the book
     * @param categoryId
     *            the id of the book's category
     * @param languageId
     *            the id of the book's language
     * @param registeredBy
     *            the id of the user who registered the book
     */
    void addBook(CreateBookDTO book, String publisherName, List<String> authorsNames, long categoryId, long languageId,
            long registeredBy);

    /**
     * Gets all the books in the database and transforms them from Book to
     * BookDTO type by filling up a list of BookDTO objects.
     *
     * @return the list of BookDTOs
     */
    List<BookDTO> getBooks();

    /**
     * Gets a book by a given id from the database and transforms it from Book
     * to BookDTO type by filling up a list of BookDTO objects.
     *
     * @param id
     *            the id of the book in the database
     * @return the selected BookDTO
     */
    BookDTO getBookById(long id);

    /**
     * @return Returns DTO list of all categories.
     */
    List<CategoryDTO> getCategories();

    /**
     * @return Returns DTO list of all languages.
     */
    List<LanguageDTO> getLanguages();
}
