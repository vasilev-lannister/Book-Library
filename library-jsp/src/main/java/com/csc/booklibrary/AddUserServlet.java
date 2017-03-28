package com.csc.booklibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.exceptions.UserAlreadyExistsException;
import com.csc.booklibrary.services.dto.CreateUserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.interfaces.UserService;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * A servlet that adds a new user.
 *
 * @author mvasilev
 *
 */

public class AddUserServlet extends HttpServlet {

    private static final String USERNAME_STRING = "username";
    private static final String FIRST_NAME_STRING = "firstName";
    private static final String LAST_NAME_STRING = "lastName";
    private static final String EMAIL_STRING = "email";
    private static final String PHONE_NUMBER_STRING = "phoneNumber";
    private static final long serialVersionUID = 7655276885476435063L;
    private static final Logger LOGGER = Logger.getLogger(AddUserServlet.class.getName());
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/addUserContent.jsp";

    /**
     * Method that redirects to the /addUser page.
     */

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final UserService userService = serviceFactory.findService(UserService.class);

        final Map<String, Object> viewModel = new HashMap<>();
        final List<Map<String, Object>> userRoleList = createUserRoleList(userService, 1);
        viewModel.put("userRoleList", userRoleList);

        request.setAttribute("viewModel", viewModel);

        final RequestDispatcher rd = request.getRequestDispatcher(THIS_PAGE);
        rd.forward(request, response);
    }

    /**
     * This method creates a new user. All of the details for it are taken from
     * the form, filled up by the user. In case of incorrect data, entered by
     * the user, it saves the filled fields (excluding password and confirmation
     * password fields) and reloads the page. Incorrect field becomes red and a
     * proper message is displayed.
     *
     */

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String username = request.getParameter(USERNAME_STRING);
        final String password = request.getParameter("password");
        final String passwordConfirm = request.getParameter("passwordConfirm");
        final String userRole = request.getParameter("userRole");
        final String firstName = request.getParameter(FIRST_NAME_STRING);
        final String lastName = request.getParameter(LAST_NAME_STRING);
        final String email = request.getParameter(EMAIL_STRING);
        final String phoneNumber = request.getParameter(PHONE_NUMBER_STRING);

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final UserService userService = serviceFactory.findService(UserService.class);

        if (!password.equals(passwordConfirm)) {
            processError("passwordConfirm", "addUser.error.passwordConfirm", request, response, userService);
            return;
        }

        final CreateUserDTO createUserDTO = new CreateUserDTO(username, password, firstName, lastName, email,
                phoneNumber, LocalFunctions.stringToInt(userRole));

        try {
            userService.addUser(createUserDTO);
        } catch (final InvalidFieldDataInputException e) {
            LOGGER.log(Level.INFO, "Invalid data input in form field.", e);
            String message = null;
            final String fieldName = e.getFieldName();

            if (USERNAME_STRING.equals(fieldName)) {
                message = "addUser.error.username";
            } else if ("password".equals(fieldName)) {
                message = "addUser.error.password";
            } else if (FIRST_NAME_STRING.equals(fieldName)) {
                message = "addUser.error.firstName";
            } else if (LAST_NAME_STRING.equals(fieldName)) {
                message = "addUser.error.lastName";
            } else if (EMAIL_STRING.equals(fieldName)) {
                message = "addUser.error.email";
            } else if (PHONE_NUMBER_STRING.equals(fieldName)) {
                message = "addUser.error.phoneNumber";
            }

            processError(fieldName, message, request, response, userService);
            return;
        } catch (final UserAlreadyExistsException e) {
            LOGGER.log(Level.INFO, "User with the given username already exists.", e);
            final String message = "addUser.error.userExists";
            processError(USERNAME_STRING, message, request, response, userService);
            return;
        }

        response.sendRedirect("users");
    }

    private void processError(final String errorField, final String message, final HttpServletRequest request,
            final HttpServletResponse response, final UserService userService) throws ServletException, IOException {

        final List<Map<String, Object>> userRoleList = createUserRoleList(userService,
                LocalFunctions.stringToInt(request.getParameter("userRole")));
        final Map<String, Object> viewModel = new HashMap<>();

        viewModel.put(USERNAME_STRING, request.getParameter(USERNAME_STRING));
        viewModel.put(FIRST_NAME_STRING, request.getParameter(FIRST_NAME_STRING));
        viewModel.put(LAST_NAME_STRING, request.getParameter(LAST_NAME_STRING));
        viewModel.put(EMAIL_STRING, request.getParameter(EMAIL_STRING));
        viewModel.put(PHONE_NUMBER_STRING, request.getParameter(PHONE_NUMBER_STRING));
        viewModel.put("errorMessage", message);
        viewModel.put("errorField", errorField);
        viewModel.put("userRoleList", userRoleList);

        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(THIS_PAGE).forward(request, response);
    }

    private List<Map<String, Object>> createUserRoleList(final UserService userService, final long selected) {
        final List<Map<String, Object>> rolesList = new ArrayList<>();
        for (final UserRoleDTO role : userService.getUserRoles()) {
            final Map<String, Object> roleType = new HashMap<>();
            roleType.put("name", role.getUserRoleName());
            roleType.put("id", String.valueOf(role.getUserRoleId()));
            if (role.getUserRoleId() == selected) {
                roleType.put("isSelected", Boolean.TRUE);
            } else {
                roleType.put("isSelected", Boolean.FALSE);
            }
            rolesList.add(roleType);
        }
        return rolesList;
    }

}
