package com.csc.booklibrary;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.UserService;

/**
 * A servlet that retrieves all users.
 *
 * @author mduhovnikov
 *
 */
public class UsersServlet extends HttpServlet {

    private static final long serialVersionUID = -4075690733248517507L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final UserService users = serviceFactory.findService(UserService.class);

        final List<Map<String, String>> viewModel = new ArrayList<>();
        final Format dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (UserDTO user : users.getUsers()) {
            Map<String, String> currentUser = new HashMap<>();
            currentUser.put("userId", String.valueOf(user.getId()));
            currentUser.put("username", user.getUsername());
            currentUser.put("firstName", user.getFirstName());
            currentUser.put("lastName", user.getLastName());
            currentUser.put("userRole", user.getUserRole().getUserRoleName());
            currentUser.put("dateRegist", user.getDateReg().toString());
            if (user.getInactive() != null) {
                currentUser.put("dateInactive", dateFormatter.format(user.getInactive()));
            }
            currentUser.put("email", user.getEmail());
            currentUser.put("phone", user.getPhone());

            viewModel.add(currentUser);

        }
        request.setAttribute("userList", viewModel);

        final RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/usersContent.jsp");
        rd.forward(request, response);
    }

}
