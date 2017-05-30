package org.suggs.sandbox.eventsourcing.library.events;

import org.suggs.sandbox.eventsourcing.library.BookId;

import java.time.LocalDate;

public class BookLent implements Event {
    public final BookId bookId;
    public final String borrower;
    public final LocalDate borrowDate;
    public final int expectedDurationInDays;

    public BookLent(BookId bookId, String borrower, LocalDate borrowDate, int expectedDurationInDays) {
        this.bookId = bookId;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.expectedDurationInDays = expectedDurationInDays;
    }

    public BookId getBookId() {
        return bookId;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public int getExpectedDurationInDays() {
        return expectedDurationInDays;
    }
}
