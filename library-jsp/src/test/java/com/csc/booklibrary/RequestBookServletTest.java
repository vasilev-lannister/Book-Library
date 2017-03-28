package com.csc.booklibrary;

//import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.web.mocks.BookRequestServiceMock;
import com.csc.booklibrary.web.mocks.BookServiceMock;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;
import com.csc.booklibrary.web.mocks.InvocationHandlerResponse;
import com.csc.booklibrary.web.mocks.InvocationHandlerServletConfig;
import com.csc.booklibrary.web.mocks.TransactionHandlerMock;

public class RequestBookServletTest {
    private final RequestBookServlet servlet = new RequestBookServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletConfig servletConfig;

    public void initialize(final String bookId, final String comment, final String requestType)
            throws ServletException, IOException {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("bookId", bookId);
        parameters.put("comment", comment);
        parameters.put("requestType", requestType);
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerResponse());
        servletConfig = (ServletConfig) Proxy.newProxyInstance(ServletConfig.class.getClassLoader(),
                new Class[] { ServletConfig.class }, new InvocationHandlerServletConfig());

    }

    @Test
    public void invalidBookId() throws ServletException, IOException {
        // given
        final String bookId = "abc";
        final String errorMessage = "Can't parse: " + bookId;
        initialize(bookId, "", "");

        // when
        servlet.doGet(request, response);

        // then
        assertEquals(errorMessage, request.getAttribute("errorMessage"));
    }

    @Test
    public void noSuchBookId() throws ServletException, IOException {
        // given
        final String bookId = "555";
        final String errorMessage = "No book with id: " + bookId;
        initialize(bookId, "", "");

        // when
        servlet.init(servletConfig);
        servlet.doGet(request, response);

        // then
        assertEquals(errorMessage, request.getAttribute("errorMessage"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void successfulldoGet() throws ServletException, IOException {
        // given
        final String bookId = "123";
        initialize(bookId, "", "");
        final BookDTO book = new BookServiceMock(new TransactionHandlerMock()).getBookById(123);
        final BookRequestService brq = new BookRequestServiceMock(new TransactionHandlerMock());

        final List<Map<String, Object>> requestTypeList = new ArrayList<>();
        for (final RequestTypeDTO r : brq.getRequestTypes()) {
            final Map<String, Object> map = new HashMap<>();
            map.put("name", r.getTypeName());
            map.put("id", String.valueOf(r.getTypeId()));
            map.put("isActive", r.getTypeId() != 3);
            requestTypeList.add(map);
        }

        // when
        servlet.init(servletConfig);
        servlet.doGet(request, response);

        // then
        final List<Map<String, Object>> requestTypeListExpected = (List<Map<String, Object>>) ((Map<String, Object>) request
                .getAttribute("viewModel")).get("typeList");

        assertEquals(book.getName(), ((Map<String, Object>) request.getAttribute("viewModel")).get("bookName"));
        assertThat(requestTypeList, Is.is(requestTypeListExpected));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void disabledRequestTypes() throws ServletException, IOException {
        // given
        final String bookId = "321";
        initialize(bookId, "", "");
        final BookDTO book = new BookServiceMock(new TransactionHandlerMock()).getBookById(321);
        final BookRequestService brq = new BookRequestServiceMock(new TransactionHandlerMock());

        final List<Map<String, Object>> requestTypeList = new ArrayList<>();
        for (final RequestTypeDTO r : brq.getRequestTypes()) {
            final Map<String, Object> map = new HashMap<>();
            map.put("name", r.getTypeName());
            map.put("id", String.valueOf(r.getTypeId()));
            map.put("isActive", r.getTypeId() > 3);
            requestTypeList.add(map);
        }

        // when
        servlet.init(servletConfig);
        servlet.doGet(request, response);

        // then
        final List<Map<String, Object>> requestTypeListExpected = (List<Map<String, Object>>) ((Map<String, Object>) request
                .getAttribute("viewModel")).get("typeList");

        assertEquals(book.getName(), ((Map<String, Object>) request.getAttribute("viewModel")).get("bookName"));
        assertThat(requestTypeList, Is.is(requestTypeListExpected));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void noComment() throws ServletException, IOException {
        // given
        final String bookId = "321";
        initialize(bookId, "", "1");

        // when
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        // then
        assertEquals("Please write a comment!",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }

    @Test
    public void invalidRequestType() throws ServletException, IOException {
        // given
        final String bookId = "444";
        initialize(bookId, "coment", "5");
        request.getSession().setAttribute("User", new UserDTO(2, "username", "first name", "last name",
                new UserRoleDTO(1, "admin"), null, null, "mail@mail.com", "456789"));

        // when
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        // then
        assertEquals("Invalid request type: 5", request.getAttribute("errorMessage"));
    }

    @Test
    public void invalidUserId() throws ServletException, IOException {
        // given
        final String bookId = "444";
        initialize(bookId, "coment", "2");
        request.getSession().setAttribute("User", new UserDTO(4, "username", "first name", "last name",
                new UserRoleDTO(1, "admin"), null, null, "mail@mail.com", "456789"));

        // when
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        // then
        assertEquals("No user with id: 4", request.getAttribute("errorMessage"));
    }

    @Test
    public void doPostSuccess() throws ServletException, IOException {
        // given
        final String bookId = "123";
        initialize(bookId, "comment on this", "1");
        request.getSession().setAttribute("User", new UserDTO(2, "username", "first name", "last name",
                new UserRoleDTO(1, "admin"), null, null, "mail@mail.com", "456789"));

        // when
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        // then
        assertEquals(null, request.getAttribute("errorMessage"));
        assertEquals(202, response.getStatus());
    }

}
