package com.csc.booklibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.ReviewService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * A servlet that creates a new review for a book.
 * 
 * @author mvitanov
 *
 */
public final class WriteReviewServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 8096357971936697369L;
    private static final Logger LOGGER = Logger.getLogger(WriteReviewServlet.class.getName());
    private static final String ERROR_PAGE = "/WEB-INF/jsp/oops.jsp"; //$NON-NLS-1$
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/writeReviewContent.jsp"; //$NON-NLS-1$

    /**
     * Jsp is a form that redirects to reviews address with same id.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String bookId = request.getParameter("id");
        try {
            LocalFunctions.validateId(bookId);
        } catch (final InvalidIdParameterException invPar) {
            onException("Invalid page id " + request.getParameter("id"), invPar, request, response);
            return;
        }
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    /**
     * This method creates a new review. Text of the review: comment parameter
     * (from post form). Id of the book the review was submitted for: id
     * parameter.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String reviewText = request.getParameter("comment");
        if (LocalFunctions.checkIfNullOrEmpty(reviewText)) {
            try {
                final long userId = ((UserDTO) request.getSession().getAttribute("User")).getId();
                final long bookId = LocalFunctions.stringToLong(request.getParameter("id"));
                final ServletContext sc = request.getServletContext();
                final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
                final ReviewService reviewService = serviceFactory.findService(ReviewService.class);
                assert reviewService != null;
                reviewService.postReview(userId, bookId, reviewText);
                response.sendRedirect("reviews?id=" + bookId);
            } catch (final InvalidIdParameterException invPar) {
                onException("Invalid page id " + invPar.getId(), invPar, request, response);
                return;
            } catch (final NoSuchUserIdException noUserEx) {
                onException("User " + noUserEx.getUserId() + " not found", noUserEx, request, response);
                return;
            } catch (final NoSuchBookIdException noBookEx) {
                onException("Book content " + noBookEx.getBookId() + " not found", noBookEx, request, response);
                return;
            } catch (final InvalidFieldDataInputException invFieldEx) {
                onException("Missing comment text", invFieldEx, request, response);
                return;
            }
        } else {
            final Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("placeholder", "writeReview.placeholder");
            viewModel.put("error", "writeReview.error");
            request.setAttribute("viewModel", viewModel);
            doGet(request, response);
        }
    }

    private void onException(final String logMessage, final RuntimeException ex, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        LOGGER.log(Level.WARNING, logMessage, ex);
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }
}
