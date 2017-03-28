package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Category;
import com.csc.booklibrary.persistence.interfaces.Language;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.CategoryDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.dto.LanguageDTO;
import com.csc.booklibrary.services.interfaces.BookService;

/**
 * @see BookService
 *
 * @author mduhovnikov
 *
 */
public class BookServiceImpl implements BookService {

    private final TransactionHandler transactionHandler;
    private final BookServiceImplLogic logic = new BookServiceImplLogic();

    /**
     * Constructor of the class.
     * 
     * @param transactionHandler
     *            The handler used for executing the methods in services' logic.
     */
    public BookServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public List<BookDTO> getBooks() {
        return transactionHandler.execute(sf -> logic.getBooks(sf.findService(BookRepository.class)));
    }

    @Override
    public BookDTO getBookById(final long id) {

        return transactionHandler.execute(sf -> logic.getBookById(id, sf.findService(BookRepository.class)));
    }

    @Override
    public void addBook(final CreateBookDTO book, final String publisherName, final List<String> authorsNames,
            final long categoryId, final long languageId, final long registeredBy) {

        transactionHandler.execute(sf -> {
            logic.addBook(book, publisherName, authorsNames, categoryId, languageId, registeredBy,
                    sf.findService(UserRepository.class), sf.findService(BookRepository.class));
            return null;
        });

    }

    @Override
    public List<CategoryDTO> getCategories() {
        final List<CategoryDTO> cl = new ArrayList<>();
        for (final Category c : Category.values()) {
            cl.add(new CategoryDTO(c.getCategoryId(), c.getCategoryName()));
        }
        return cl;
    }

    @Override
    public List<LanguageDTO> getLanguages() {
        final List<LanguageDTO> ll = new ArrayList<>();
        for (final Language l : Language.values()) {
            ll.add(new LanguageDTO(l.getLanguageId(), l.getLanguageName()));
        }
        return ll;
    }
}