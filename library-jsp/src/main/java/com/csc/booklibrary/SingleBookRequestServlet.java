package com.csc.booklibrary;

import static com.csc.booklibrary.web.utils.LocalFunctions.stringToLong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.exceptions.NoSuchRequestException;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.exceptions.InvalidProcessOperationException;

/**
 * Retrieves a request by Id.
 *
 * @author lbosilkov
 *
 */
public final class SingleBookRequestServlet extends CommonBookRequestServlet {
    private static final long serialVersionUID = 3070891579598204029L;
    private static final String ERROR_PAGE = "/WEB-INF/jsp/oops.jsp";
    private static final Logger LOGGER = Logger.getLogger(BorrowBookServlet.class.getName());

    @Override
    void process(final HttpServletRequest request, final  HttpServletResponse response, final BookRequestService requestService, final Map<String, Object> viewModel) throws ServletException, IOException {
        final String requestId = request.getParameter("id");
        request.setAttribute("requestId", request.getParameter("id"));
        final long id;

        try {
            id = stringToLong(requestId);
        } catch (InvalidIdParameterException exc) {
            LOGGER.log(Level.INFO, "Invalid id parameterd:" + exc.getId(), exc);
            request.setAttribute("errorMessage", "Invalid id parameter!");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            throw new InvalidProcessOperationException("Invalid id parameter!");
        }

        final RequestDTO requestDTO;
        try{
            requestDTO = requestService.getRequestById(id);
        } catch (final NoSuchRequestException exc) {
            LOGGER.log(Level.INFO, "There is no request with id:" + exc.getRequestId(), exc);
            request.setAttribute("errorMessage", "There is no such request!");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            throw new InvalidProcessOperationException("There is no such request!");
        }

        final List<Map<String, String>> bookRequests = new ArrayList<>();
        viewModel.put("bookRequests", bookRequests);
        addRequestDTOInViewModel(bookRequests, requestDTO);
        viewModel.put("isPending", Boolean.valueOf(requestDTO.getDateAnswered() == null));
    }
}
