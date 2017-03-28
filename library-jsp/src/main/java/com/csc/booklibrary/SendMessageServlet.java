
package com.csc.booklibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.exceptions.NoSuchUsernameException;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.MessagesService;

/**
 * A servlet that sends a message to another user.
 *
 * @author mduhovnikov
 *
 */
public class SendMessageServlet extends HttpServlet {

    /**
     * Contains logic to send a message to specified user
     *
     * @author dgetsov
     */
    private static final long serialVersionUID = 6370523886056462922L;
    private static final Logger LOGGER = Logger.getLogger(SendMessageServlet.class.getName());
    private static final String SEND_MESSAGE_PAGE = "/WEB-INF/jsp/pages/sendMessageContent.jsp";
    private static final String RECEIVER_STRING = "receiver";
    private static final String NOTIFICATION_STRING = "notification";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final RequestDispatcher rd = request.getRequestDispatcher(SEND_MESSAGE_PAGE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");

        final String receiverUsername = request.getParameter(RECEIVER_STRING);
        final String messageText = request.getParameter("messageText");

        if (receiverUsername == null || "".equals(receiverUsername)) {
            forwardOnError(viewModel -> {
                viewModel.put(NOTIFICATION_STRING, "messages.error.typeInUsername");
                viewModel.put("message", messageText);
            }, request, response);
            return;
        }

        if ("".equals(messageText) || messageText == null) {
            forwardOnError(viewModel -> {
                viewModel.put(NOTIFICATION_STRING, "messages.error.typeInMessage");
                viewModel.put(RECEIVER_STRING, receiverUsername);
            }, request, response);
            return;
        } else {
            try {
                final UserDTO sender = (UserDTO) request.getSession().getAttribute("User");
                final long senderId = sender.getId();

                final MessagesService messagesServices = serviceFactory.findService(MessagesService.class);
                messagesServices.sendMessage(senderId, receiverUsername, messageText);

            } catch (final NoSuchUsernameException | NoSuchUserIdException e) {
                LOGGER.log(Level.INFO, "Username not found:" + "\"" + receiverUsername + "\"", e);
                forwardOnError(viewModel -> {
                    viewModel.put(NOTIFICATION_STRING, "messages.error.noSuchUser");
                    viewModel.put("message", messageText);
                    viewModel.put(RECEIVER_STRING, receiverUsername);
                }, request, response);
                return;
            }
        }

        response.sendRedirect("messagesOut");
    }

    private void forwardOnError(final Consumer<Map<String, String>> notficationActions,
            final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final Map<String, String> viewModel = new HashMap<>();
        notficationActions.accept(viewModel);
        final RequestDispatcher rd = request.getRequestDispatcher(SEND_MESSAGE_PAGE);
        request.setAttribute("viewModel", viewModel);
        rd.forward(request, response);
    }

}
