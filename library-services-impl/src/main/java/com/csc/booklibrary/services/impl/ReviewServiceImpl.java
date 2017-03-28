package com.csc.booklibrary.services.impl;

import java.util.List;

import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.interfaces.ReviewService;

/**
 * @see ReviewService
 *
 * @author mduhovnikov
 *
 */
public final class ReviewServiceImpl implements ReviewService {

    private final TransactionHandler transactionHandler;
    private final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();

    public ReviewServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public List<ReviewDTO> getReviewsByBookId(final long bookId) {
        return transactionHandler.execute(sf -> {
            return logic.getReviewsByBookId(bookId, sf.findService(UserRepository.class),
                    sf.findService(BookRepository.class));
        });
    }

    @Override
    public void postReview(final long userId, final long bookId, final String content) {
        transactionHandler.execute(sf -> {
            logic.postReview(userId, bookId, content, sf.findService(UserRepository.class),
                    sf.findService(BookRepository.class));
            return null;
        });
    }
}
