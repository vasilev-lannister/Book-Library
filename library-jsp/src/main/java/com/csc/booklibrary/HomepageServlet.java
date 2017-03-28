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
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.services.interfaces.UserService;

/**
 * Servlet that displays the homepage with the last added books, users and
 * requests.
 * 
 * @author mduhovnikov
 *
 */
public class HomepageServlet extends HttpServlet {

    private static final long serialVersionUID = 9547677070837942L;
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/homepageContent.jsp"; //$NON-NLS-1$

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final BookService bookService = serviceFactory.findService(BookService.class);
        final UserService userService = serviceFactory.findService(UserService.class);
        final BookRequestService requestService = serviceFactory.findService(BookRequestService.class);

        List<Map<String, String>> bookViewModel = getBooks(bookService);
        List<Map<String, String>> userViewModel = getUsers(userService);
        List<Map<String, String>> requestsViewModel = getRequests(requestService);

        request.setAttribute("books", bookViewModel);
        request.setAttribute("users", userViewModel);
        request.setAttribute("requests", requestsViewModel);

        final RequestDispatcher rd = request.getRequestDispatcher(THIS_PAGE);
        rd.forward(request, response);
    }

    private List<Map<String, String>> getBooks(final BookService bookService) {

        final List<Map<String, String>> viewModel = new ArrayList<>();
        for (final BookDTO book : bookService.getBooks()) {

            final Map<String, String> bookDetails = new HashMap<>();
            bookDetails.put("name", book.getName());
            bookDetails.put("isbn", book.getIsbn());
            viewModel.add(bookDetails);
        }
        return viewModel;
    }

    private List<Map<String, String>> getUsers(final UserService userService) {

        final List<Map<String, String>> viewModel = new ArrayList<>();
        for (final UserDTO user : userService.getUsers()) {

            final Map<String, String> userDetails = new HashMap<>();
            userDetails.put("username", user.getUsername());
            userDetails.put("firstName", user.getFirstName());
            userDetails.put("lastName", user.getLastName());
            viewModel.add(userDetails);
        }
        return viewModel;
    }

    private List<Map<String, String>> getRequests(final BookRequestService requestService) {

        final List<Map<String, String>> viewModel = new ArrayList<>();
        for (final RequestDTO req : requestService.getRequests()) {

            final Map<String, String> requestDetails = new HashMap<>();
            requestDetails.put("madeBy", req.getUser().getUsername());
            requestDetails.put("forBook", req.getBook().getName());
            requestDetails.put("type", req.getType().getTypeName());
            viewModel.add(requestDetails);
        }
        return viewModel;
    }
}
