package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.ReviewDTO;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.impl.ReviewServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.BookRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public class GetReviewByBookIdTest {

    private final BookRepository bookRepoMock = new BookRepositoryMock();
    private final UserRepository userRepoMock = new UserRepositoryMock();

    @Test(expected = NoSuchBookIdException.class)
    public void testWrongBookContent() {
        // given
        final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();
        final long bookId = 7;

        // when
        logic.getReviewsByBookId(bookId, userRepoMock, bookRepoMock);
    }

    @Test
    public void testSuccessNoComments() {
        // given
        final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();
        final long bookId = 1;

        // when
        final List<ReviewDTO> reviews = logic.getReviewsByBookId(bookId, userRepoMock, bookRepoMock);

        // then
        assertEquals(true, reviews.isEmpty());
    }

    @Test
    public void testSuccess() {
        // given
        final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();
        final long bookId = 2;

        // when
        final List<ReviewDTO> reviews = logic.getReviewsByBookId(bookId, userRepoMock, bookRepoMock);

        // then
        assertEquals(2, reviews.size());
        assertEquals(bookId, reviews.get(0).getBook().getBookId());
        assertEquals(bookId, reviews.get(1).getBook().getBookId());
    }

    @Test
    public void testSuccessCommentsNull() {
        // given
        final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();
        final long bookId = 3;

        // when
        final List<ReviewDTO> reviews = logic.getReviewsByBookId(bookId, userRepoMock, bookRepoMock);

        // then
        assertEquals(true, reviews.isEmpty());
    }
}
