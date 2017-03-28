package com.csc.booklibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.interfaces.BookService;

/**
 * A servlet that retrieves all books.
 * 
 * @author mduhovnikov
 *
 */
public class BooksServlet extends HttpServlet {

    private static final long serialVersionUID = 7504594553418352535L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final BookService bookService = serviceFactory.findService(BookService.class);

        final List<Map<String, String>> viewModel = new ArrayList<>();

        for (final BookDTO book : bookService.getBooks()) {

            final Map<String, String> bookDetails = new HashMap<>();
            bookDetails.put("id", String.valueOf(book.getBookId()));
            bookDetails.put("name", book.getName());
            bookDetails.put("authors", AuthorConverter.delimitByCommas(book.getAuthors()));
            bookDetails.put("yearPublished", String.valueOf(book.getYearPublished()));
            bookDetails.put("isbn", book.getIsbn());
            bookDetails.put("edition", String.valueOf(book.getEdition()));
            bookDetails.put("publisherName", book.getPublisher().getName());
            bookDetails.put("category", String.valueOf(book.getCategory()));
            bookDetails.put("language", String.valueOf(book.getLanguage()));
            bookDetails.put("quantity", String.valueOf(book.getQuantity()));
            bookDetails.put("registeredByName", book.getRegisteredBy().getUsername());

            viewModel.add(bookDetails);
        }

        request.setAttribute("books", viewModel);

        final RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pages/booksContent.jsp");
        rd.forward(request, response);
    }

}
