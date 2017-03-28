package com.csc.booklibrary.services.interfaces;

import java.util.List;

import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;

/**
 * Interface for a service that transfers information about library requests
 * from the persistence layer to the web layer.
 *
 * @author mvitanov
 *
 */
public interface BookRequestService {
    /**
     *
     * @return A list of all requests.
     */
    List<RequestDTO> getRequests();

    /**
     *
     * @return A list of all requests from a particular user.
     * @param userId
     *            The id of the user.
     */
    List<RequestDTO> getRequests(long userId);

    /**
     *
     * @return A list of requests that haven't been answered yet.
     */
    List<RequestDTO> getPendingRequests();

    /**
     *
     * @return A list of requests from a particular user that haven't been
     *         answered yet.
     * @param userId
     *            The id of the user.
     */
    List<RequestDTO> getPendingRequests(long userId);

    /**
     * Creates new request
     *
     * @param reqestType
     *            The type of the request
     * @param bookContentId
     *            Id of the book content the request is for
     * @param comment
     *            Comment of the request
     * @param userId
     *            User Id of the user making the request
     */
    void createRequest(long bookContentId, int reqestType, String comment, long userId);

    /**
     *
     * @param id
     *            Id of the request. Non-null.
     * @return Request with that id.
     */
    RequestDTO getRequestById(long id);

    /**
     *
     * @return Returns DTO list of all request types.
     */
    List<RequestTypeDTO> getRequestTypes();
}
