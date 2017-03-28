package com.csc.booklibrary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoFreeCopyOfBookException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.exceptions.RequestAlreadyAnsweredException;
import com.csc.booklibrary.services.interfaces.BorrowBookService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * Servlet for borrowing a book.
 *
 * @author lbosilkov
 *
 */
public final class BorrowBookServlet extends HttpServlet {
    private static final long serialVersionUID = 7635950761083869859L;
    private static final Logger LOGGER = Logger.getLogger(BorrowBookServlet.class.getName());
    private static final String REQUEST_ID = "requestId";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/oops.jsp";
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/borrowBookContent.jsp";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final Map<String, String> viewModel = new HashMap<>();
        viewModel.put(REQUEST_ID, request.getParameter(REQUEST_ID));
        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String finalDate = request.getParameter("finalDate");
        LocalDate date = null;

        try {
            date = LocalDate.parse(finalDate);
        } catch (final DateTimeParseException e) {
            LOGGER.log(Level.INFO, "Invalid date:" + finalDate + "!", e);
            processWrongDate(request, response, finalDate, "Invalid date!");
            return;
        }

        final String requestId = request.getParameter(REQUEST_ID);
        long id = 0;

        try {
            id = LocalFunctions.stringToLong(requestId);
        } catch (final InvalidIdParameterException exc) {
            LOGGER.log(Level.WARNING, "Invalid id:" + exc.getId(), exc);
            processWrongDate(request, response, finalDate, "Invalid request id!");
            return;
        }

        if (isUserDateAfterCurrentDate(date)) {
            LOGGER.log(Level.INFO, "Invalid id - Date must be in the future!");
            processWrongDate(request, response, finalDate, "Invalid date! Date must be in the future!");
            return;
        }

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final BorrowBookService borrowBook = serviceFactory.findService(BorrowBookService.class);
        final HttpSession session = request.getSession();
        final UserDTO admin = (UserDTO) session.getAttribute("User");
        try {
            borrowBook.borrowBook(id, date, admin.getId());
        } catch (final NoFreeCopyOfBookException exc) {
            processException(request, response, exc, "No free copy of book with id:" + exc.getBookId(),
                    "No free copy of this book!");
            return;
        } catch (final NoSuchRequestException exc) {
            processException(request, response, exc, "Request with id:" + exc.getRequestId() + "doesn't exist!",
                    "Request doesn't exist!");
            return;
        } catch (final NoSuchUserIdException exc) {
            processException(request, response, exc, "User with id:" + exc.getUserId() + "doesn't exist!",
                    "User doesn't exist!");
            return;
        } catch (final RequestAlreadyAnsweredException exc) {
            processException(request, response, exc,
                    "Request with id:" + exc.getRequestId() + " has already been answered!",
                    "This request has already been answered!");
            return;
        }
        response.sendRedirect("request?id=" + id);
    }

    private void processException(final HttpServletRequest request, final HttpServletResponse response,
            final RuntimeException exc, final String exceptionMessage, final String errorPageMessage)
            throws ServletException, IOException {
        LOGGER.log(Level.WARNING, exceptionMessage, exc);
        request.setAttribute(ERROR_MESSAGE, errorPageMessage);
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }

    private void processWrongDate(final HttpServletRequest request, final HttpServletResponse response,
            final String finalDate, final String errorMessage) throws ServletException, IOException {
        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("errorMessage", errorMessage);
        viewModel.put("wrongDate", true);
        viewModel.put("finalDate", finalDate);
        viewModel.put(REQUEST_ID, request.getParameter(REQUEST_ID));
        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    private boolean isUserDateAfterCurrentDate(final LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}
