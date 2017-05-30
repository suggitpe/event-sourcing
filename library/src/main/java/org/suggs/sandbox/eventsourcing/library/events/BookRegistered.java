package org.suggs.sandbox.eventsourcing.library.events;

import org.suggs.sandbox.eventsourcing.library.BookId;

public class BookRegistered implements Event {
    private final BookId id;
    private final String title;
    private final String isbn;

    public BookRegistered(BookId id, String title, String isbn) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
    }

    public BookId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }
}
