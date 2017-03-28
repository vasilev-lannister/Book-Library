package com.csc.booklibrary;

import java.io.IOException;
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
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.web.exceptions.InvalidProcessOperationException;

/**
 * Servlet that displays single request or all requests.
 *
 * @author lbosilkov
 *
 */
public abstract class CommonBookRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 4294125075280182310L;
    private static final Logger LOGGER = Logger.getLogger(CommonBookRequestServlet.class.getName());
    private static final String ERROR_PAGE = "/WEB-INF/jsp/oops.jsp";

    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final BookRequestService requestService = serviceFactory.findService(BookRequestService.class);
        assert requestService != null;
        final Map<String, Object> viewModel = new HashMap<>();

        try {
            process(request, response, requestService, viewModel);
        } catch (final InvalidProcessOperationException exc) {
            LOGGER.log(Level.INFO, "Process operation cannot fulfilled:" + exc.getMessage() + "!", exc);
            request.setAttribute("errorMessage", "Process operation cannot fulfilled!");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }
        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher("/WEB-INF/jsp/pages/requestsContent.jsp").forward(request, response);
    }

    abstract void process(final HttpServletRequest request, final HttpServletResponse response,
            final BookRequestService requestService, final Map<String, Object> viewModel)
            throws ServletException, IOException;

    final List<Map<String, String>> addRequestDTOInViewModel(final List<Map<String, String>> bookRequests,
            final RequestDTO requestDtTO) {
        final Map<String, String> requestMap = new HashMap<>();
        requestMap.put("requestId", Long.toString(requestDtTO.getId()));
        requestMap.put("byUser", requestDtTO.getUser().getUsername());
        requestMap.put("forBook", requestDtTO.getBook().getName());
        requestMap.put("type", requestDtTO.getType().getTypeName());
        requestMap.put("dateMade", requestDtTO.getDateMade().toString());
        if (requestDtTO.getAdmin() != null) {
            requestMap.put("admin", requestDtTO.getAdmin().getUsername());
        }
        requestMap.put("comment", requestDtTO.getComment());
        if (requestDtTO.getRejectReason() != null) {
            requestMap.put("rejectReason", requestDtTO.getRejectReason().getRejectReasonName());
        }
        if (requestDtTO.getDateAnswered() != null) {
            requestMap.put("dateAnswered", requestDtTO.getDateAnswered().toString());
        }
        bookRequests.add(requestMap);
        return bookRequests;
    }
}