package com.csc.booklibrary;

import java.util.List;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;

/**
 * Retrieves all pending requests.
 *
 * @author lbosilkov
 *
 */
public final class BookPendingRequstsServlet extends CommonMultipleBookRequestsServlet {
    private static final long serialVersionUID = 8545826689746559413L;

    @Override
    List<RequestDTO> getApplicableRequests(final BookRequestService requestService, final UserDTO user) {
        return user != null ? requestService.getPendingRequests(user.getId()) : requestService.getPendingRequests();
    }
}
