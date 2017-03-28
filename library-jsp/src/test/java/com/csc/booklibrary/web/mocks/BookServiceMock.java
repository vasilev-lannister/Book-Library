package com.csc.booklibrary.web.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csc.booklibrary.services.dto.AuthorDTO;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.CategoryDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.dto.LanguageDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.interfaces.BookService;

/**
 * Mock of the BookService class used for testing.
 * 
 * @author mduhovnikov
 *
 */
public class BookServiceMock implements BookService {

    TransactionHandlerMock handler;

    Map<Long, BookDTO> books = new HashMap<>();
    
    /**
     * Constructor that initializes the needed mock objects.
     * 
     * @param handler The transaction handler used for mocking.
     */
    public BookServiceMock(final TransactionHandlerMock handler) {
        this.handler = handler;
        final List<AuthorDTO> authors = new ArrayList<>();
        authors.add(new AuthorDTO(1, "someone", "bulgarian", "ivancho"));
        books.put((long) 123,
                new BookDTO(123, 0, null, "Test Book", "1234-1234-1234-x", authors, 1999, 2, 2, 10, null));
        books.put((long) 321,
                new BookDTO(321, 0, null, "Test Book 2", "1234-1234-1234-x", authors, 1999, 2, 2, 0, null));
        books.put((long) 444,
                new BookDTO(444, 0, null, "Test Book 2", "1234-1234-1234-x", authors, 1999, 5, 2, 0, null));
    }

    @Override
    public void addBook(final CreateBookDTO book, final String publisherName, final List<String> authorsNames,
            final long categoryId, final long languageId, final long registeredBy) {
        if (book.getName() == null || book.getName() == "") {
            throw new InvalidFieldDataInputException("name");
        }
        if (book.getIsbn() == null || book.getIsbn() == "") {
            throw new InvalidFieldDataInputException("isbn");
        }
    }

    @Override
    public List<BookDTO> getBooks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BookDTO getBookById(final long id) {

        final BookDTO book = books.get(id);
        if (book == null) {
            throw new NoSuchBookIdException(id);
        }
        return book;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        final List<CategoryDTO> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDTO(1, "Fiction"));
        categoryList.add(new CategoryDTO(2, "Drama"));
        categoryList.add(new CategoryDTO(3, "Fantasy"));
        categoryList.add(new CategoryDTO(4, "Comedy"));
        return categoryList;
    }

    @Override
    public List<LanguageDTO> getLanguages() {
        final List<LanguageDTO> languageList = new ArrayList<>();
        languageList.add(new LanguageDTO(1, "English"));
        languageList.add(new LanguageDTO(2, "Bulgarian"));
        languageList.add(new LanguageDTO(3, "German"));
        languageList.add(new LanguageDTO(4, "Russian"));
        languageList.add(new LanguageDTO(5, "Spanish"));
        languageList.add(new LanguageDTO(6, "French"));
        return languageList;
    }

}
