package com.csc.booklibrary;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
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
 * Class which tests the functionality of the SendMessageServlet
 *
 * @author mvasilev
 *
 */
public class SendMessageServletTest {

    private final SendMessageServlet servlet = new SendMessageServlet();
    HttpServletRequest request;
    HttpServletResponse response;

    private void initialize(final String receiverUsername, final String messageText) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("receiver", receiverUsername);
        parameters.put("messageText", messageText);
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerDefault());
    }

    /**
     * A test for sending a message to a user with null username value. Tests if
     * the error message is correct and if the data is preserved in the text
     * field.
     *
     * @throws ServletException
     * @throws IOException
     */

    @SuppressWarnings("unchecked")
    @Test
    public void nullUsername() throws ServletException, IOException {
        // given
        final String receiverUsername = null;
        final String messageText = "Sample";
        final String error = "messages.error.typeInUsername";
        initialize(receiverUsername, messageText);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(error, ((Map<String, String>) request.getAttribute("viewModel")).get("notification"));
        assertEquals(messageText, ((Map<String, String>) request.getAttribute("viewModel")).get("message"));
    }

    /**
     * A test for sending a message with null value. Tests if the error message
     * is correct and if the data is preserved in the receiver username field.
     *
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void nullMessageText() throws ServletException, IOException {
        // given
        final String receiverUsername = "ivan1";
        final String messageText = null;
        final String error = "messages.error.typeInMessage";
        initialize(receiverUsername, messageText);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(error, ((Map<String, String>) request.getAttribute("viewModel")).get("notification"));
        assertEquals(receiverUsername, ((Map<String, String>) request.getAttribute("viewModel")).get("receiver"));
    }

    /**
     * A test for sending a message to a user with nonexistent username. Tests
     * if the error message is correct and if the data is preserved in the
     * receiver username and message text fields.
     *
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void nonexistentUserWithGivenUsername() throws ServletException, IOException {
        // given
        final String receiverUsername = "ivan23";
        final String messageText = "Sample text";
        final String error = "messages.error.noSuchUser";
        initialize(receiverUsername, messageText);
        final UserRoleDTO role = new UserRoleDTO(1, "admin");
        final UserDTO loggedUser = new UserDTO(1, "sample", "sampleFirst", "sampleLast", role, LocalDate.now(), null,
                "a@a.com", "099876541");

        request.getSession().setAttribute("User", loggedUser);

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(error, ((Map<String, String>) request.getAttribute("viewModel")).get("notification"));
        assertEquals(messageText, ((Map<String, String>) request.getAttribute("viewModel")).get("message"));
        assertEquals(receiverUsername, ((Map<String, String>) request.getAttribute("viewModel")).get("receiver"));
    }

}
