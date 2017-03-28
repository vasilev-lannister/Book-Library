package com.csc.booklibrary;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.MessagesService;

/**
 * This servlet retrieves all messages that a user has sent.
 * 
 * @author mduhovnikov
 *
 */
public class OutboxServlet extends HttpServlet {

    private static final long serialVersionUID = -2520843805325935038L;
    private static final String OUTBOX_PAGE = "/WEB-INF/jsp/pages/messagesContent.jsp";

    /**
     * Gets the messages (@code MessagesDTO), sent from the user, from servlet
     * context and creates viewModel object. viewModel is saved in request
     * context.
     */

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final MessagesService messagesService = serviceFactory.findService(MessagesService.class);
        final UserDTO sender = (UserDTO) request.getSession().getAttribute("User");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        final List<Map<String, String>> messages = new ArrayList<>();

        for (final MessagesDTO message : messagesService.getMessagesSentByUserId(sender.getId())) {

            final Map<String, String> messageDetails = new HashMap<>();
            messageDetails.put("id", String.valueOf(message.getMessageID()));
            messageDetails.put("text", message.getMessageText());
            messageDetails.put("receiver", message.getReceiver().getUsername());
            messageDetails.put("dateSent", message.getMessageDate().format(formatter));
            if (message.getMessageDateRead() != null) {
                messageDetails.put("dateRead", message.getMessageDateRead().format(formatter));
            }
            messages.add(messageDetails);
        }

        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("messages", messages);
        viewModel.put("title", "Outbox");
        viewModel.put("user", "messages.label.toUser");
        viewModel.put("isInbox", Boolean.FALSE);

        request.setAttribute("viewModel", viewModel);

        final RequestDispatcher rd = request.getRequestDispatcher(OUTBOX_PAGE);
        rd.forward(request, response);
    }

}
