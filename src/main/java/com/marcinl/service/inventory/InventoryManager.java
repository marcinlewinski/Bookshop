package com.marcinl.service.inventory;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InventoryManager {
    void addNewBook(Book book);

    void deleteBook(UUID bookId);

    void editBook(UUID bookId, long quantity);
    List<Book> getAllBooks();

    Map<Client, List<Book>> getReservedBooks();

    Map<Client, List<Book>> getBorrowedBooks();

    Map<Book, Integer> getSoldOutBooks();
}
