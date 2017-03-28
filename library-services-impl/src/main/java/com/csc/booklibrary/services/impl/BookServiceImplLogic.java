package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Category;
import com.csc.booklibrary.persistence.interfaces.Language;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.AuthorDTO;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.dto.PublisherDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchCategoryException;
import com.csc.booklibrary.services.exceptions.NoSuchLanguageException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.services.validators.BookValidator;

/**
 * @see BookService
 *
 * @author mduhovnikov
 *
 */
public class BookServiceImplLogic {

    /**
     * @see BookService#getBooks
     * @param bookRepository
     *            The repository used to find the books.
     * @return A list of the DTO books.
     */
    public List<BookDTO> getBooks(final BookRepository bookRepository) {

        final List<Book> books = bookRepository.findBooks();
        final List<BookDTO> booksDTO = new ArrayList<>();

        for (final Book book : books) {
            final List<AuthorDTO> authorsList = DTOConverter.convertAuthors(book.getAuthors());

            final PublisherDTO publisher = DTOConverter.convertPublisher(book.getPublisher());
            final UserDTO registeredBy = DTOConverter.convertUser(book.getRegisteredBy());

            booksDTO.add(new BookDTO(book.getBookId(), book.getEdition(), publisher, book.getName(), book.getIsbn(),
                    authorsList, book.getYearPublished(), book.getCategoryId(), book.getLanguageId(),
                    book.getNumberOfCopies(), registeredBy));
        }
        return booksDTO;
    }
    
    /**
     * @see BookService#getBookById
     * @param id The id of the sought book.
     * @param bookRepository The repository used to find the book.
     * @return The DTO book.
     */
    public BookDTO getBookById(final long id, final BookRepository bookRepository) {

        final Book book = bookRepository.findBook(id);
        if (book == null) {
            throw new NoSuchBookIdException(id);
        }
        return DTOConverter.convertBook(book);
    }
    
    /**
     * @see BookService#addBook
     * @param createBookDTO The DTO book with the content of the book.
     * @param publisherName The name of the publisher of the book.
     * @param authorsNames The name of the authors of the book.
     * @param categoryId The id of the category of the book.
     * @param languageId The id of the language of the book.
     * @param registeredBy The name of the user who registered the book.
     * @param userRepo The repository used to find the user.
     * @param bookRepository The repository used to find the book.
     * @return The created book.
     */
    public Book addBook(final CreateBookDTO createBookDTO, final String publisherName, final List<String> authorsNames,
            final long categoryId, final long languageId, final long registeredBy, final UserRepository userRepo,
            final BookRepository bookRepository) {

        BookValidator.validate(createBookDTO);

        final User user = userRepo.findUser(registeredBy);
        if (user == null) {
            throw new NoSuchUserIdException(registeredBy);
        }

        final Category category = getCategory(categoryId);
        final Language language = getLanguage(languageId);

        Publisher publisher = bookRepository.findPublisherByName(publisherName);

        if (publisher == null) {
            publisher = bookRepository.createPublisher(publisherName);
        }

        final List<Author> authors = new ArrayList<>();

        for (String name : authorsNames) {
            Author author = bookRepository.findAuthorByName(name);
            if (author == null) {
                author = bookRepository.createAuthor(name);
            }
            authors.add(author);
        }

        Book book = bookRepository.createBook(createBookDTO.getName(), createBookDTO.getIsbn(), publisher, authors,
                createBookDTO.getYearPublished(), category, language, user);
        book.setEdition(createBookDTO.getEdition());
        book.setNumberOfCopies(createBookDTO.getQuantity());

        return book;
    }

    private Category getCategory(final long categoryId) {
        Category category = null;
        for (final Category c : Category.values()) {
            if (c.getCategoryId() == categoryId) {
                category = c;
                break;
            }
        }
        if (category == null) {
            throw new NoSuchCategoryException(categoryId);
        }
        return category;
    }

    private Language getLanguage(final long languageId) {
        Language language = null;
        for (final Language l : Language.values()) {
            if (l.getLanguageId() == languageId) {
                language = l;
                break;
            }
        }
        if (language == null) {
            throw new NoSuchLanguageException(languageId);
        }
        return language;
    }
}
