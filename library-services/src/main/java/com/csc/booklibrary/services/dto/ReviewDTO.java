package com.csc.booklibrary.services.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * This class has information about the reviews for a selected book.
 *
 * @author mduhovnikov
 *
 */
public class ReviewDTO implements Serializable {

    private static final long serialVersionUID = 3445770638191336265L;
    private long reviewId;
    private final UserDTO user;
    private final BookDTO book;
    private final Long parentId;
    private final LocalDateTime reviewDate;
    private final String reviewContent;

    /**
     * @param reviewId
     *            The id of the written review.
     * @param user
     *            The user who wrote the review.
     * @param book
     *            The book for which the review was written.
     * @param parentId
     *            The id of the review to which this review is a reply.
     * @param reviewDate
     *            The date on which the review was written.
     * @param reviewContent
     *            The content of the written review.
     */
    public ReviewDTO(long reviewId, UserDTO user, BookDTO book, Long parentId, LocalDateTime reviewDate,
            String reviewContent) {
        this.reviewId = reviewId;
        this.user = user;
        this.book = book;
        this.parentId = parentId;
        this.reviewDate = reviewDate;
        this.reviewContent = reviewContent;
    }

    /**
     * @return The id of the review.
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * @return The user who wrote the review.
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @return The book for which the review was written.
     */
    public BookDTO getBook() {
        return book;
    }

    /**
     * @return The id of the review to which this review is a reply.
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @return The date on which the review was written.
     */
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    /**
     * @return The content of the review.
     */
    public String getReviewContent() {
        return reviewContent;
    }
}
