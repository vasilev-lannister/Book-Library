package com.csc.booklibrary.services.interfaces;

import java.util.List;

import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;

/**
 * Interface for a service that gets the reviews for a selected book and
 * transfers them from the persistence to the web layer.
 * 
 * @author mduhovnikov
 *
 */
public interface ReviewService {
    /**
     * A method that gets all the reviews for a selected book.
     * 
     * @param bookId
     *            The id of the selected book.
     * @return A list of reviews for the selected book.
     * @throws NoSuchBookIdException
     *             If no book in the persistence layer has id bookId.
     */
    List<ReviewDTO> getReviewsByBookId(final long bookId);

    /**
     * A method that creates a review in the database.
     * 
     * @param userId
     *            Id of the user who made the review.
     * @param bookId
     *            id of the book reviewed.
     * @param content
     *            Content of the review.
     * @throws IllegalArgumentException
     *             if content is null
     * @throws NoSuchUserIdException
     *             If no user in the persistence layer has id userId.
     * @throws NoSuchBookIdException
     *             If no book in the persistence layer has id bookId.
     */
    void postReview(final long userId, final long bookId, final String content);
}
