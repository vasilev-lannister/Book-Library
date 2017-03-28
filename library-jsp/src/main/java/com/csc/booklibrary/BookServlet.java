package com.csc.booklibrary;

import java.io.IOException;
import java.util.HashMap;
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
 * A servlet that retrieves a book using its ID.
 * 
 * @author mduhovnikov
 *
 */
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 9062558596874043976L;
    private static final String BOOK_PAGE = "/WEB-INF/jsp/pages/bookContent.jsp";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String id = request.getParameter("id");
        final long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (final NumberFormatException e) {
            final RequestDispatcher rd = request.getRequestDispatcher(BOOK_PAGE);
            rd.forward(request, response);
            return;
        }

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final BookService bookService = serviceFactory.findService(BookService.class);

        final BookDTO targetBook = bookService.getBookById(parsedId);
        final Map<String, String> viewModel = new HashMap<>();

        if (targetBook != null) {
            viewModel.put("id", String.valueOf(targetBook.getBookId()));
            viewModel.put("name", targetBook.getName());
            viewModel.put("authors", AuthorConverter.delimitByCommas(targetBook.getAuthors()));
            viewModel.put("yearPublished", String.valueOf(targetBook.getYearPublished()));
            viewModel.put("isbn", targetBook.getIsbn());
            viewModel.put("edition", String.valueOf(targetBook.getEdition()));
            viewModel.put("publisherName", targetBook.getPublisher().getName());
            viewModel.put("category", String.valueOf(targetBook.getCategory()));
            viewModel.put("language", String.valueOf(targetBook.getLanguage()));
            viewModel.put("quantity", String.valueOf(targetBook.getQuantity()));
            viewModel.put("registeredByName", targetBook.getRegisteredBy().getUsername());
        }

        request.setAttribute("viewModel", viewModel);
        final RequestDispatcher rd = request.getRequestDispatcher(BOOK_PAGE);
        rd.forward(request, response);

    }
}