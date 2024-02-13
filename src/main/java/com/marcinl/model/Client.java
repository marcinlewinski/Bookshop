package com.marcinl.model;

import com.marcinl.bookObserver.BookObserver;

import java.util.UUID;

public record Client(UUID id, String email) implements BookObserver {
    @Override
    public void update(Book book) {
        System.out.println("Client " + email + " has been notified about the availability of book: " + book.getTitle());
    }
}
