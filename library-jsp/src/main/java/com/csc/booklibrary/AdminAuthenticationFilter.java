package com.csc.booklibrary;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.booklibrary.services.dto.UserDTO;

/**
 * This class is used for determining whether the user who attempts to log in is
 * an admin or a regular user. It blocks particular content from the regular
 * user.
 * 
 * @author mduhovnikov
 *
 */
public class AdminAuthenticationFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // Don't need it in this case.
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpSession session = ((HttpServletRequest) request).getSession();
        final UserDTO user = (UserDTO) session.getAttribute("User");
        if (user != null && !"admin".equals(user.getUserRole().getUserRoleName())) {
            ((HttpServletResponse) response).sendRedirect("homepage");
            return;
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // Don't need it in this case.
    }

}
