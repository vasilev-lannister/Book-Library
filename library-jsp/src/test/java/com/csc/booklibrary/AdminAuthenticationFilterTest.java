package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Proxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.web.mocks.InvocationHandlerFilterChain;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;
import com.csc.booklibrary.web.mocks.InvocationHandlerResponse;

public class AdminAuthenticationFilterTest {

    private final AdminAuthenticationFilter filter = new AdminAuthenticationFilter();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    @Before
    public void init() {
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(null));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerResponse());
        chain = (FilterChain) Proxy.newProxyInstance(FilterChain.class.getClassLoader(),
                new Class[] { FilterChain.class }, new InvocationHandlerFilterChain());
    }

    @Test
    public void userNotNullandAdmin() throws IOException, ServletException {
        // before
        request.getSession().setAttribute("User", new UserDTO(4, "username", "first name", "last name",
                new UserRoleDTO(1, "admin"), null, null, "mail@mail.com", "456789"));

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }

    @Test
    public void userNotNullandNotAdmin() throws IOException, ServletException {
        // before
        request.getSession().setAttribute("User", new UserDTO(4, "username", "first name", "last name",
                new UserRoleDTO(2, "user"), null, null, "mail@mail.com", "456789"));

        // when
        filter.doFilter(request, response, chain);

        // then
        assertEquals(202, response.getStatus());
    }

    @Test
    public void userisNull() throws IOException, ServletException {
        // before
        request.getSession().setAttribute("User", new UserDTO(4, "username", "first name", "last name",
                new UserRoleDTO(1, "admin"), null, null, "mail@mail.com", "456789"));

        // when
        filter.doFilter(request, response, chain);

        // then
        assertTrue((boolean) request.getAttribute("chain.doFilter()Executed"));
    }
}
