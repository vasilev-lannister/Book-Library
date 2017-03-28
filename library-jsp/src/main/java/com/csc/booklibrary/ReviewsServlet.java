package com.csc.booklibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.interfaces.ReviewService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * A servlet that displays all reviews for a selected book.
 *
 * @author mduhovnikov
 *
 */
public final class ReviewsServlet extends HttpServlet {

    private static final long serialVersionUID = -9120967594947444030L;
    private static final Logger LOGGER = Logger.getLogger(ReviewsServlet.class.getName());
    private static final String ERROR_PAGE = "/WEB-INF/jsp/oops.jsp"; //$NON-NLS-1$
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/reviewsContent.jsp"; //$NON-NLS-1$

    /**
     * This method creates a list of (@code ReviewDTO) objects by calling the
     * (@code getReviews(bookId)) method of the (@code ReviewService) interface
     * and forwards the list to a jsp.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final long id;
        try {
            id = LocalFunctions.stringToLong(request.getParameter("id"));
        } catch (final InvalidIdParameterException invParam) {
            LOGGER.log(Level.INFO, "Invalid page id " + invParam.getId(), invParam);
            request.setAttribute("errorMessage", "Invalid page id " + request.getParameter("id")); //$NON-NLS-1$
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }
        
        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final ReviewService rs = serviceFactory.findService(ReviewService.class);
        
        assert rs != null;

        try {
            final List<Map<String, String>> reviewMap = createViewModel(rs.getReviewsByBookId(id));
            request.setAttribute("reviews", reviewMap);
        } catch (final NoSuchBookIdException noBookEx) {
            LOGGER.log(Level.INFO, "Book content " + noBookEx.getBookId() + " not found", noBookEx);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }

        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    @SuppressWarnings("nls")
    private List<Map<String, String>> createViewModel(final List<ReviewDTO> reviews) {
        final List<Map<String, String>> viewModel = new ArrayList<>();
        for (final ReviewDTO r : reviews) {
            final Map<String, String> currentReview = new HashMap<>();
            currentReview.put("reviewId", Long.toString(r.getReviewId()));
            currentReview.put("authorName", r.getUser().getUsername());
            currentReview.put("book", r.getBook().getName());
            if (r.getParentId() != null) {
                currentReview.put("parentReview", Long.toString(r.getParentId()));
            }
            currentReview.put("reviewDate", r.getReviewDate().toString());
            currentReview.put("reviewContent", r.getReviewContent());
            viewModel.add(currentReview);
        }
        return viewModel;
    }

}
