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
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchRequestTypeException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * A servlet that requests a book selected by the user.
 * 
 * @author mduhovnikov
 *
 */
public class RequestBookServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RequestBookServlet.class.getName());
    private static final String ERROR_PAGE = "/errorHandler"; //$NON-NLS-1$
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/addRequestContent.jsp"; //$NON-NLS-1$
    private static final int TYPE_SUGGESTION_ID = 3;
    private static final String SERVICE_FACTORY = "serviceFactory";
    /**
     * 
     */
    private static final long serialVersionUID = -8192294959903346655L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        doGetWithErrorMessage(request, response, "");
    }

    private void doGetWithErrorMessage(final HttpServletRequest request, final HttpServletResponse response,
            final String errorMessage) throws ServletException, IOException {
        final long bcId;
        try {
            bcId = LocalFunctions.stringToLong(request.getParameter("bookId"));
        } catch (final InvalidIdParameterException invParam) {
            setError(invParam, request, response);
            return;
        }

        final Map<String, Object> viewModel = getBookData(bcId, request, response);
        if (viewModel == null) {
            return;
        }
        final List<Map<String, Object>> requestTypeList = getRequestTypes((int) viewModel.remove("bookQuant"));
        viewModel.put("typeList", requestTypeList);
        if (LocalFunctions.checkIfNullOrEmpty(errorMessage)) {
            viewModel.put("errorMessage", errorMessage);
        }
        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        try {
            final long bcId = LocalFunctions.stringToLong(request.getParameter("bookId"));
            final int requestType = LocalFunctions.stringToInt(request.getParameter("requestType"));

            final String comment = request.getParameter("comment");
            if (!LocalFunctions.checkIfNullOrEmpty(comment)) {
                doGetWithErrorMessage(request, response, "Please write a comment!");
                return;
            }

            final UserDTO user = (UserDTO) request.getSession(false).getAttribute("User");

            final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) getServletContext()
                    .getAttribute(SERVICE_FACTORY);
            final BookRequestService reqService = serviceFactory.findService(BookRequestService.class);
            reqService.createRequest(bcId, requestType, comment, user.getId());

            response.sendRedirect("pendingRequests");

        } catch (InvalidIdParameterException | NoSuchRequestTypeException | NoSuchUserIdException
                | NoSuchBookIdException exc) {
            setError(exc, request, response);
            return;
        }
    }

    private void setError(final RuntimeException exc, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final String message = setExceptionMessage(exc);
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }

    private String setExceptionMessage(final RuntimeException exc) throws ServletException, IOException {
        String message = "Unknown exception";
        if (exc instanceof InvalidIdParameterException) {
            message = "Can't parse: " + ((InvalidIdParameterException) exc).getId();
        }
        if (exc instanceof NoSuchRequestTypeException) {
            message = "Invalid request type: " + exc.getMessage();
        }
        if (exc instanceof NoSuchUserIdException) {
            message = "No user with id: " + ((NoSuchUserIdException) exc).getUserId();
        }
        if (exc instanceof NoSuchBookIdException) {
            message = "No book with id: " + ((NoSuchBookIdException) exc).getBookId();
        }
        LOGGER.log(Level.INFO, message, exc);
        return message;
    }

    private Map<String, Object> getBookData(final long bookId, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute(SERVICE_FACTORY);
        final BookService bookService = serviceFactory.findService(BookService.class);

        assert bookService != null;

        try {
            final BookDTO book = bookService.getBookById(bookId);
            final Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("bookQuant", book.getQuantity());
            viewModel.put("disableSubmit", String.valueOf(false));
            viewModel.put("bookName", book.getName());
            viewModel.put("authors", AuthorConverter.delimitByCommas(book.getAuthors()));
            return viewModel;
        } catch (final NoSuchBookIdException nsb) {
            setError(nsb, request, response);
            return null;
        }
    }

    private List<Map<String, Object>> getRequestTypes(final int bookQuant) {
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) getServletContext().getAttribute(SERVICE_FACTORY);
        final BookRequestService requestService = serviceFactory.findService(BookRequestService.class);

        final List<Map<String, Object>> reqTypeList = new ArrayList<>();

        for (final RequestTypeDTO reqTypeDto : requestService.getRequestTypes()) {
            final Map<String, Object> requestTypeViewModel = new HashMap<>();
            requestTypeViewModel.put("name", reqTypeDto.getTypeName());
            requestTypeViewModel.put("id", String.valueOf(reqTypeDto.getTypeId()));
            if (reqTypeDto.getTypeId() == TYPE_SUGGESTION_ID) {
                requestTypeViewModel.put("isActive", false);
            } else {
                requestTypeViewModel.put("isActive", !(bookQuant == 0 && reqTypeDto.getTypeId() < TYPE_SUGGESTION_ID));
            }
            reqTypeList.add(requestTypeViewModel);
        }

        return reqTypeList;
    }
}
