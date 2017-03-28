package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Author;
import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.Message;
import com.csc.booklibrary.persistence.interfaces.Publisher;
import com.csc.booklibrary.persistence.interfaces.RejectReason;
import com.csc.booklibrary.persistence.interfaces.Request;
import com.csc.booklibrary.persistence.interfaces.Type;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRole;
import com.csc.booklibrary.services.dto.AuthorDTO;
import com.csc.booklibrary.services.dto.BookDTO;
import com.csc.booklibrary.services.dto.MessagesDTO;
import com.csc.booklibrary.services.dto.PublisherDTO;
import com.csc.booklibrary.services.dto.RejectReasonDTO;
import com.csc.booklibrary.services.dto.RequestDTO;
import com.csc.booklibrary.services.dto.RequestTypeDTO;
import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;

/**
 * Utility class with methods for converting interfaces from the persistence
 * layer to DTOS.
 *
 * @author mvitanov
 *
 */
public final class DTOConverter {

    private DTOConverter() {
    }

    /**
     * Creates a UserRoleDTO object for an implementation of the User interface.
     *
     * @param user
     *            Non-null.
     * @return A DTO object for the role of the user parameter.
     */
    public static UserRoleDTO convertUserRole(final User user) {
        assert user != null;
        final int userRoleId = user.getUserRoleId();
        final String userRoleName = UserRole.getRoleFromId(userRoleId);
        return new UserRoleDTO(userRoleId, userRoleName);
    }

    /**
     * Converts an implementation of the User interface to a UserDTO object.
     *
     * @param user
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static UserDTO convertUser(final User user) {
        assert user != null;
        final UserRoleDTO userRole = convertUserRole(user);
        return new UserDTO(user.getUserId(), user.getUsername(), user.getFirstName(), user.getLastName(), userRole,
                user.getDateRegistered(), user.getDateInactive(), user.getEmail(), user.getPhone());
    }

    /**
     * Converts an implementation of the Publisher interface to a PublisherDTO
     * object.
     *
     * @param publisher
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static PublisherDTO convertPublisher(final Publisher publisher) {
        assert publisher != null;
        return new PublisherDTO(publisher.getPublisherId(), publisher.getName());
    }

    /**
     * Converts an implementation of the Author interface to an AuthorDTO
     * object.
     *
     * @param author
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static AuthorDTO convertAuthor(final Author author) {
        assert author != null;
        return new AuthorDTO(author.getAuthorId(), author.getName(), author.getNationality(), author.getNickname());
    }

    /**
     * Converts a list implementations of the Author interface to a list of
     * AuthorDTO objects.
     *
     * @param authors
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static List<AuthorDTO> convertAuthors(final List<Author> authors) {
        assert authors != null;
        final List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (final Author a : authors) {
            authorsDTO.add(convertAuthor(a));
        }
        return authorsDTO;
    }

    /**
     * Converts an implementation of the BookContent interface to a
     * BookContentDTO object.
     *
     * @param bookContent
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static BookDTO convertBook(final Book book) {
        assert book != null;
        final List<AuthorDTO> authors = convertAuthors(book.getAuthors());
        final PublisherDTO publisher = convertPublisher(book.getPublisher());
        final UserDTO registeredBy = convertUser(book.getRegisteredBy());

        return new BookDTO(book.getBookId(), book.getEdition(), publisher, book.getName(), book.getIsbn(), authors,
                book.getYearPublished(), book.getCategoryId(), book.getLanguageId(), book.getNumberOfCopies(),
                registeredBy);
    }

    /**
     * Converts an implementation of the Message interface to a MessagetDTO
     * object.
     *
     * @param message
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static MessagesDTO convertMessage(final Message message) {
        assert message != null;
        final UserDTO receiver = convertUser(message.getToUser());
        final UserDTO sender = convertUser(message.getFromUser());
        return new MessagesDTO(message.getMessageId(), receiver, sender, message.getMessageText(),
                message.getMessageDate(), message.getReadDate());
    }

    /**
     * Converts an implementation of the RejectReason interface to a
     * RejectReasonDTO object.
     *
     * @param request
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static RequestTypeDTO convertRequestType(final Request request) {
        assert request != null;
        final int typeId = request.getTypeId();
        final String typeName = Type.getTypeFromId(typeId);
        return new RequestTypeDTO(typeId, typeName);
    }

    /**
     * Converts an implementation of the RejectReason interface to a
     * RejectReasonDTO object.
     *
     * @param rejectReason
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static RejectReasonDTO convertRejectReason(final RejectReason rejectReason) {
        assert rejectReason != null;
        return new RejectReasonDTO(rejectReason.getRejectReasonId(), rejectReason.getRejectReasonText());
    }

    /**
     * Converts an implementation of the Request interface to a RequestDTO
     * object.
     *
     * @param request
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static RequestDTO convertRequest(final Request request) {
        assert request != null;
        final UserDTO user = convertUser(request.getUser());
        final BookDTO book = convertBook(request.getBook());
        final RequestTypeDTO type = convertRequestType(request);
        RejectReasonDTO rejectReason = null;
        if (request.getRejectReason() != null) {
            rejectReason = convertRejectReason(request.getRejectReason());
        }
        UserDTO admin = null;
        if (request.getAdmin() != null) {
            admin = convertUser(request.getAdmin());
        }

        return new RequestDTO(request.getRequestId(), user, book, type, request.getDateMade(), admin,
                request.getRequestComment(), rejectReason, request.getDateAnswered());
    }

    /**
     * Converts an implementation of the Review interface to a ReviewDTO object.
     *
     * @param review
     *            Non-null.
     * @return A DTO object for the parameter.
     */
    public static ReviewDTO convertReview(final Comment review) {
        assert review != null;
        final UserDTO user = convertUser(review.getUser());
        final BookDTO book = convertBook(review.getBook());
        Long parentId = null;
        if (review.getParent() != null) {
            parentId = Long.valueOf(review.getParent().getId());
        }

        return new ReviewDTO(review.getId(), user, book, parentId, review.getCommentDate(), review.getContent());
    }
}
