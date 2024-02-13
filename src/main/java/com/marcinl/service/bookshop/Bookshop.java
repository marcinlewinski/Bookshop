package com.marcinl.service.bookshop;

import java.util.UUID;

public interface Bookshop {
    void reserveBook(UUID bookId, String email);
    void sellBook(UUID bookId, String email);
}
