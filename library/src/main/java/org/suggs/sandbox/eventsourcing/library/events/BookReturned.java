package org.suggs.sandbox.eventsourcing.library.events;

import org.suggs.sandbox.eventsourcing.library.BookId;

public class BookReturned implements Event{

    private final BookId bookId;
    private final String returnedBy;
    private final long retunedAfterInDays;
    private final boolean late;

    public BookReturned(BookId bookId, String returnedBy, long retunedAfterInDays, boolean late) {
        this.bookId = bookId;
        this.returnedBy = returnedBy;
        this.retunedAfterInDays = retunedAfterInDays;
        this.late = late;
    }

    public BookId getBookId() {
        return bookId;
    }

    public String getReturnedBy() {
        return returnedBy;
    }

    public long getRetunedAfterInDays() {
        return retunedAfterInDays;
    }

    public boolean isLate() {
        return late;
    }
}
