package com.csc.booklibrary;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet that handlers errors and exceptions and in case of such redirects
 * to the correct error page and provides the ID of the error.
 * 
 * @author mduhovnikov
 *
 */
public class ErrorHandlerServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -9126668653864387309L;
    private static final String ERROR_PAGE = "/WEB-INF/jsp/ErrorPage.jsp";
    private static final Logger LOGGER = Logger.getLogger(ErrorHandlerServlet.class.getName());

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        handleError(request, response);
    }

    private void handleError(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        final UUID id = UUID.randomUUID();
        LOGGER.log(Level.WARNING, "Error ID : [" + id.toString() + "]", throwable);
        request.setAttribute("details", "Error ID : " + id.toString());
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }

}
