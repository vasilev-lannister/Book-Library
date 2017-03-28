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
import javax.servlet.http.HttpSession;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.WrongPasswordException;
import com.csc.booklibrary.services.interfaces.LoginService;
import com.csc.booklibrary.validators.InputValidator;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * Servlet that tries to login the user after being provided with a username and
 * password combination. Redirects to the home page afterwards.
 * 
 * @author mvitanov
 *
 */
public final class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 6577529821962344156L;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private static final String THIS_PAGE = "/WEB-INF/jsp/login.jsp"; //$NON-NLS-1$
    private static final String LOGOUT_PAGE = "/WEB-INF/jsp/logout.jsp"; //$NON-NLS-1$
    private static final String HOME_PAGE = "homepage"; //$NON-NLS-1$
    private static final String USERNAME_STRING = "username"; //$NON-NLS-1$
    private static final String VIEWMODEL_STRING = "viewModel"; //$NON-NLS-1$

    /**
     * Forwards to the login form if there is no user logged in. Forwards to the
     * logout page if there is a user logged in.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final UserDTO user = (UserDTO) request.getSession().getAttribute("User");
        if (user == null) {
            request.getRequestDispatcher(THIS_PAGE).forward(request, response);
            return;
        } else {
            final Map<String, Object> viewModel = new HashMap<>();
            viewModel.put(USERNAME_STRING, user.getUsername());
            request.setAttribute(VIEWMODEL_STRING, viewModel);
            request.getRequestDispatcher(LOGOUT_PAGE).forward(request, response);
            return;
        }
    }

    /**
     * If the provided username and password are valid tries to log in the user.
     * Calls a service that checks whether the username and password match in
     * the persistence layer. If the login is successful sets a DTO
     * corresponding to the user as a session parameter.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String username = request.getParameter(USERNAME_STRING);
        final String password = request.getParameter("password");
        if (!LocalFunctions.checkIfNullOrEmpty(username) || !LocalFunctions.checkIfNullOrEmpty(password)
                || !InputValidator.validateUsername(username) || !InputValidator.validatePassword(password)) {
            onError(username, request, response);
            return;
        } else {
            final ServletContext sc = request.getServletContext();
            final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
            final LoginService loginService = serviceFactory.findService(LoginService.class);

            try {
                final UserDTO currentUser = loginService.performLogin(username, password);
                LOGGER.log(Level.INFO, "New user logged in: " + username);
                final HttpSession session = request.getSession();
                session.setAttribute("User", currentUser);
                final String url = (String) session.getAttribute("logInFromURL");
                if (url != null) {
                    response.sendRedirect(url);
                } else {
                    response.sendRedirect(HOME_PAGE);
                }
                return;
            } catch (final NoSuchUsernameException ex) {
                LOGGER.log(Level.WARNING, "No user named: " + username, ex);
                onError(username, request, response);
                return;
            } catch (final WrongPasswordException ex) {
                LOGGER.log(Level.WARNING, "Wrong password for user: " + username, ex);
                onError(username, request, response);
                return;
            }
        }

    }

    @SuppressWarnings("nls")
    private void onError(final String username, final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put(USERNAME_STRING, username);
        viewModel.put("errorMessage", "loginServlet.error");
        request.setAttribute(VIEWMODEL_STRING, viewModel);
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }
}
