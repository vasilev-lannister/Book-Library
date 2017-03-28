package com.csc.booklibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;

/**
 * Processing all requests.
 *
 * @author lbosilkov
 *
 */
public abstract class CommonMultipleBookRequestsServlet extends CommonBookRequestServlet {
    private static final long serialVersionUID = -8552811201880972229L;
    private static final long ADMIN_ID = 1;

    @Override
    final void process(final HttpServletRequest request, final HttpServletResponse response,
            final BookRequestService requestService, final Map<String, Object> viewModel) {
        final HttpSession session = request.getSession(false);
        assert session != null;
        final UserDTO sessionUser = (UserDTO) session.getAttribute("User");
        assert sessionUser != null;

        final List<RequestDTO> applicableRequests = getApplicableRequests(requestService,
                sessionUser.getUserRole().getUserRoleId() == ADMIN_ID ? null : sessionUser);
        final List<Map<String, String>> bookRequests = new ArrayList<>();
        viewModel.put("bookRequests", bookRequests);

        for (final RequestDTO r : applicableRequests) {
            addRequestDTOInViewModel(bookRequests, r);
        }
    }

    abstract List<RequestDTO> getApplicableRequests(BookRequestService requestService, UserDTO requestingUser);
}
