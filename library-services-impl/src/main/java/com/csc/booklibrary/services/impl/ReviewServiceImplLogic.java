package com.csc.booklibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.Comment;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.interfaces.ReviewService;

public final class ReviewServiceImplLogic {

    public ReviewServiceImplLogic() {
        // empty constructor because there is no state to set
    }

    /**
     * @see ReviewService#getReviewsByBookId
     * 
     * @param userRepository
     *            Repository used to find the comments.
     * @param bookRepository
     *            Repository used to check whether a book with this id exists.
     */
    public List<ReviewDTO> getReviewsByBookId(final long bookId, final UserRepository userRepository,
            final BookRepository bookRepository) {
        assert bookRepository != null;
        assert userRepository != null;
        final Book book = bookRepository.findBook(bookId);
        if (book == null) {
            throw new NoSuchBookIdException(bookId);
        }
        final List<Comment> comments = userRepository.findCommentsByBookId(bookId);
        final List<ReviewDTO> reviews = new ArrayList<>();
        if (comments != null) {
            for (final Comment r : comments) {
                reviews.add(DTOConverter.convertReview(r));
            }
        }
        return reviews;
    }

    /**
     * @see ReviewService#postReview
     *
     * @param userRepository
     *            Repository used to find the user who made the review. Must be
     *            non-null.
     * @param bookRepository
     *            Repository used to find the book for which the review was
     *            written. Must be non-null.
     */
    public void postReview(final long userId, final long bookId, final String content,
            final UserRepository userRepository, final BookRepository bookRepository) {
        assert userRepository != null;
        assert bookRepository != null;
        if (content == null) {
            throw new IllegalArgumentException("Null passed as comment text");
        }
        final User user = userRepository.findUser(userId);
        if (user == null) {
            throw new NoSuchUserIdException(userId);
        }
        final Book book = bookRepository.findBook(bookId);
        if (book == null) {
            throw new NoSuchBookIdException(bookId);
        }
        userRepository.addCommentFrom(user, book, content);
    }
}
