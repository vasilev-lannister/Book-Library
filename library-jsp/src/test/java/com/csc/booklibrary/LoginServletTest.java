package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.web.mocks.InvocationHandlerDefault;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;

public class LoginServletTest {

    private final LoginServlet servlet = new LoginServlet();
    HttpServletRequest request;
    HttpServletResponse response;

    public void initialize(final String username, final String password) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("username", username); //$NON-NLS-1$
        parameters.put("password", password); //$NON-NLS-1$
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerDefault());
    }

    @Test
    public void SuccessfulLogin() throws ServletException, IOException {
        // given
        final String username = "ivan1"; //$NON-NLS-1$
        final String password = "ivan123"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((UserDTO) request.getSession().getAttribute("User")).getUsername()); //$NON-NLS-1$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void nullUsername() throws ServletException, IOException {
        // given
        final String username = null;
        final String password = "ivan123"; //$NON-NLS-1$
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void emptyUsername() throws ServletException, IOException {
        // given
        final String username = ""; //$NON-NLS-1$
        final String password = "ivan123"; //$NON-NLS-1$
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void nullPassword() throws ServletException, IOException {
        // given
        final String username = "ivan1"; //$NON-NLS-1$
        final String password = null;
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void emptyPassword() throws ServletException, IOException {
        // given
        final String username = "ivan1"; //$NON-NLS-1$
        final String password = ""; //$NON-NLS-1$
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void invalidUsername() throws ServletException, IOException {
        // given
        final String username = "iv an1"; //$NON-NLS-1$
        final String password = "ivan123"; //$NON-NLS-1$
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("unchecked")
    @Test
    public void invalidPassword() throws ServletException, IOException {
        // given
        final String username = "ivan1"; //$NON-NLS-1$
        final String password = "iva n123"; //$NON-NLS-1$
        final String error = "loginServlet.error"; //$NON-NLS-1$
        initialize(username, password);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(username, ((Map<String, Object>) request.getAttribute("viewModel")).get("username")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(error, ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage")); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
