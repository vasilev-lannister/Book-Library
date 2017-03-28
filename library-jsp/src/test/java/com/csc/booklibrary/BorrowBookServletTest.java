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
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.web.mocks.InvocationHandlerDefault;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;

/**
 * This class tests the proper functionality of the BorrowBookServlet class.
 *
 * @author lbosilkov
 *
 */
public class BorrowBookServletTest {
    private final BorrowBookServlet servlet = new BorrowBookServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;

    private void initialize(final String finalDate, final String requestId) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("finalDate", finalDate);
        parameters.put("requestId", requestId);
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerDefault());
    }

    /**
     * Successfully executing of doPost.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void borrowBookSuccess() throws ServletException, IOException {
        final String finalDate = "2017-12-03";
        final String requestId = "1";
        initialize(finalDate, requestId);
        request.getSession().setAttribute("User", new UserDTO(2, "username", "first name", "last name",
                new UserRoleDTO(2, "user"), null, null, "mail@mail.com", "456789"));
        servlet.doPost(request, response);
        assertEquals(null, request.getAttribute("viewModel"));
    }

    /**
     * Insert characters instead of real date.
     *
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void nonParsableDate() throws ServletException, IOException {
        final String finalDate = "asd";
        final String requestId = "1";
        initialize(finalDate, requestId);
        servlet.doPost(request, response);
        assertEquals("Invalid date!", ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage"));
    }

    /**
     * Insert date before current date.
     *
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void insertOldDate() throws ServletException, IOException {
        final String finalDate = "2007-12-03";
        final String requestId = "1";
        initialize(finalDate, requestId);
        servlet.doPost(request, response);
        assertEquals("Invalid date! Date must be in the future!",
                ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage"));
    }

    /**
     * Insert character instead of real request id (long value).
     *
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void insertNonParsableRequestId() throws ServletException, IOException {
        final String finalDate = "2017-12-03";
        final String requestId = "a";
        initialize(finalDate, requestId);
        servlet.doPost(request, response);
        assertEquals("Invalid request id!",
                ((Map<String, Object>) request.getAttribute("viewModel")).get("errorMessage"));
    }
}
