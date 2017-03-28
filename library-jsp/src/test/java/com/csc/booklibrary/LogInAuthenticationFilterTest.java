package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.web.mocks.InvocationHandlerFilterChain;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;
import com.csc.booklibrary.web.mocks.InvocationHandlerResponse;

public class LogInAuthenticationFilterTest {

    private final LogInAuthenticationFilter filter = new LogInAuthenticationFilter();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    public void init(final String uri) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("uri", uri);

        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerResponse());
        chain = (FilterChain) Proxy.newProxyInstance(FilterChain.class.getClassLoader(),
                new Class[] { FilterChain.class }, new InvocationHandlerFilterChain());
    }

    @Test
    public void excludeLogInPage() throws IOException, ServletException {

        // before
        init("library-jsp/login");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void excludeErrorPage() throws IOException, ServletException {

        // before
        init("library-jsp/oops");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void excludeCss() throws IOException, ServletException {

        // before
        init("library-jsp/style.css");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void excludeHtml() throws IOException, ServletException {

        // before
        init("library-jsp/index.html");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void excludeJs() throws IOException, ServletException {

        // before
        init("library-jsp/index.js");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void noExcludeNoUser() throws IOException, ServletException {

        // before
        init("library-jsp/books");

        // when
        filter.doFilter(request, response, chain);

        // then
        assertEquals(202, response.getStatus());
    }

    @Test
    public void noExcludeWithUser() throws IOException, ServletException {

        // before
        init("library-jsp/books");
        request.getSession().setAttribute("User", new UserDTO(4, "username", "first name", "last name",
                new UserRoleDTO(2, "user"), null, null, "mail@mail.com", "456789"));

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }
}
