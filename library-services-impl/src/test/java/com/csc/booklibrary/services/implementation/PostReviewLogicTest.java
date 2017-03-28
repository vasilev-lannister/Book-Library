package com.csc.booklibrary.services.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.csc.booklibrary.persistence.interfaces.Book;
import com.csc.booklibrary.persistence.interfaces.BookRepository;
import com.csc.booklibrary.persistence.interfaces.User;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.exceptions.NoSuchBookIdException;
import com.csc.booklibrary.services.exceptions.NoSuchUserIdException;
import com.csc.booklibrary.services.impl.ReviewServiceImplLogic;
import com.csc.booklibrary.services.implementation.mocks.BookRepositoryMock;
import com.csc.booklibrary.services.implementation.mocks.UserRepositoryMock;

public class PostReviewLogicTest {

    final ReviewServiceImplLogic logic = new ReviewServiceImplLogic();
    private final String content = "sample comment";
    BookRepository bookRepository;
    UserRepository userRepository;

    @Before
    public void initialize() {
        bookRepository = new BookRepositoryMock();
        userRepository = new UserRepositoryMock();
    }

    @Test(expected = NoSuchUserIdException.class)
    public void testWrongUser() {
        // given
        final long userId = 20;
        final long bookId = 1;

        // when
        logic.postReview(userId, bookId, content, userRepository, bookRepository);
    }

    @Test(expected = NoSuchBookIdException.class)
    public void testWrongBookContent() {
        // given
        final long userId = 12;
        final long bookId = 5;

        // when
        logic.postReview(userId, bookId, content, userRepository, bookRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongCommentText() {
        // given
        final long userId = 12;
        final long bookId = 1;
        final String content = null;

        // when
        logic.postReview(userId, bookId, content, userRepository, bookRepository);
    }

    @Test
    public void testSuccess() {
        // given
        final long userId = 12;
        final long bookId = 1;

        // when
        logic.postReview(userId, bookId, content, userRepository, bookRepository);

        // then
        final User user = userRepository.findUser(userId);
        final Book book = bookRepository.findBook(bookId);
        assertEquals(content, user.getComments().get(0).getContent());
        assertEquals(book, user.getComments().get(0).getBook());
    }
}
