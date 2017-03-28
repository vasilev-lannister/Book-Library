package com.csc.booklibrary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet that is used when the user decides to log out. It redirects the
 * user to the login page.
 *
 * @author mduhovnikov
 *
 */
public final class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = -8224830442816545592L;
    private static final String LOGIN_PAGE = "login"; //$NON-NLS-1$

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(LOGIN_PAGE);
        return;
    }

}
