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

import com.csc.booklibrary.web.mocks.InvocationHandlerDefault;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;

public class BookServletTest {
    private final BookServlet bookServlet = new BookServlet();
    HttpServletRequest request;
    HttpServletResponse response;

    @Test
    public void nonParsableId() throws ServletException, IOException {
        // given
        final String id = "d/8f*4";
        initialize(id);

        // when
        bookServlet.doGet(request, response);
        // then
        assertEquals(null, request.getAttribute("viewModel"));
    }

    private void initialize(final String id) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id);
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerDefault());
    }

}
