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
 * A servlet that displays all messages by Id.
 *
 * @author lbosilkov
 *
 */
public class MessagesServlet extends HttpServlet {

    private static final long serialVersionUID = -7068554703507079710L;
    private static final String THIS_PAGE = "/WEB-INF/jsp/pages/messagesContent.jsp"; //$NON-NLS-1$

    /**
     * Gets the messages (@code MessagesDTO), sent to the user, from servlet
     * context and creates viewModel object. viewModel is saved in request
     * context.
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute("serviceFactory");
        final MessagesService ms = serviceFactory.findService(MessagesService.class);

        assert ms != null;
        final UserDTO user = (UserDTO) request.getSession().getAttribute("User");
        final long id = user.getId();
        final List<MessagesDTO> allMmessages = ms.getMessagesReceivedByUserId(id);
        final List<Map<String, String>> messages = new ArrayList<>();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (final MessagesDTO msg : allMmessages) {
            final Map<String, String> currentMessage = new HashMap<>();
            currentMessage.put("sender", msg.getFromUser().getUsername());
            currentMessage.put("id", Long.toString(msg.getMessageID()));
            currentMessage.put("text", msg.getMessageText());
            currentMessage.put("dateSent", msg.getMessageDate().format(formatter));
            if (msg.getMessageDateRead() != null) {
                currentMessage.put("dateRead", msg.getMessageDateRead().format(formatter));
            }
            messages.add(currentMessage);
        }
        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("messages", messages);
        viewModel.put("title", "Inbox");
        viewModel.put("user", "messages.label.fromUser");
        viewModel.put("isInbox", Boolean.TRUE);
        request.setAttribute("viewModel", viewModel);

        final RequestDispatcher rd = request.getRequestDispatcher(THIS_PAGE);
        rd.forward(request, response);
    }
}
