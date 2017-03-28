package com.csc.booklibrary.services.interfaces;

import java.time.LocalDate;

/**
 * Service for processing book borrowing.
 *
 * @author lbosilkov
 *
 */
public interface BorrowBookService {
    /**
     * Borrowing a book.
     *
     * @param requestId
     *            Non-null, the id of the request for borrowing.
     * @param finalDate
     *            Non-null, the date on which the book must be returned.
     * @param adminId
     *            Non-null, the id of the admin who processed the request.
     */
    void borrowBook(long requestId, LocalDate finalDate, long adminId);
}
