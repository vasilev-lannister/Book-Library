package com.csc.booklibrary;

import java.util.List;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.BookRequestService;

/**
 * Retrieves all requests (pending and answered).
 *
 * @author lbosilkov
 *
 */
public final class BookRequestsServlet extends CommonMultipleBookRequestsServlet {
    private static final long serialVersionUID = 1827564245098519556L;

    @Override
    List<RequestDTO> getApplicableRequests(final BookRequestService requestService, final UserDTO user) {
        return user != null ? requestService.getRequests(user.getId()) : requestService.getRequests();
    }
}
