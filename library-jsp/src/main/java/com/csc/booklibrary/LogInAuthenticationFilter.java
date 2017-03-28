package com.csc.booklibrary;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet that always redirects to the login page if the user is not logged
 * in.
 * 
 * @author mduhovnikov
 *
 */
public class LogInAuthenticationFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // Don't need it in this case.
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final String url = req.getRequestURI();
        if (!exclude(url)) {

            final HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("User") == null) {
                req.getSession(true).setAttribute("logInFromURL", url + "?" + req.getQueryString());
                resp.sendRedirect("login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean exclude(final String url) {

        return Pattern.matches("(.*)[/](login|oops)(.*)", url) || Pattern.matches("(.*)[.](css|html|js)", url);
    }

    @Override
    public void destroy() {
        // Don't need it in this case.
    }

}
