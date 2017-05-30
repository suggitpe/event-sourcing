package org.suggs.sandbox.eventsourcing.library;

import java.util.UUID;

public class BookId {
    private final UUID id;

    public BookId(UUID id) {
        this.id = id;
    }

    public static BookId newBookId() {
        return new BookId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookId)) return false;

        BookId bookId = (BookId) o;

        return id != null ? id.equals(bookId.id) : bookId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
