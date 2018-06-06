package org.suggs.sandbox.eventsourcing.library;

import org.suggs.sandbox.eventsourcing.library.events.BookLent;
import org.suggs.sandbox.eventsourcing.library.events.BookRegistered;
import org.suggs.sandbox.eventsourcing.library.events.BookReturned;
import org.suggs.sandbox.eventsourcing.library.events.Event;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

public class Book {

    private final BookId id;
    private String title;
    private String isbn;
    private String borrower;
    private LocalDate date;
    private long expectedLoanLength;

    public Book(BookId id, Collection<Event> events) {
        this.id = id;,
        events.forEach(event -> apply(event));
    }

    public Book(BookId id, String title, String isbn) {
        this.id = id;
        BookRegistered event = new BookRegistered(id, title, isbn);
        apply(event);
        append(event);
    }

    public BookId getBookId() {
        return id;
    }

    public void lendBook(String borrower, LocalDate date, int lendLengthInDays) {
        if (this.borrower != null) {
            throw new RuntimeException("The book [" + getBookId() + "] is already lent");
        }
        BookLent event = new BookLent(id, borrower, date, lendLengthInDays);
        apply(event);
        append(event);
    }

    public void returnBook(LocalDate returnDate) {
        if (borrower == null) {
            throw new RuntimeException("The book has not been lent");
        }
        if (returnDate.isBefore(date)) {
            throw new RuntimeException("cannot return a book before it has been lent");
        }

        long duration = Duration.between(returnDate, date).toDays();
        BookReturned event = new BookReturned(id, borrower, duration, duration > expectedLoanLength);
        apply(event);
        append(event);
    }

    public void apply(BookRegistered event) {
        title = event.getTitle();
        isbn = event.getIsbn();
    }

    public void apply(BookLent event) {
        borrower = event.getBorrower();
        date = event.getBorrowDate();
        expectedLoanLength = event.getExpectedDurationInDays();
    }

    public void apply(BookReturned event) {
        borrower = null;
    }

}
