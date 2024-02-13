package com.marcinl.service.inventory;

import com.marcinl.model.Book;
import com.marcinl.model.Client;
import com.marcinl.repository.BookshopRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InventoryManagerImpl implements InventoryManager {
    private final BookshopRepository bookshopRepository;

    public InventoryManagerImpl(BookshopRepository bookshopRepository) {
        this.bookshopRepository = bookshopRepository;
    }

    @Override
    public void addNewBook(Book book) {
        List<Book> bookList = bookshopRepository.getBookList();
        bookList.add(book);
        bookshopRepository.setBookList(bookList);
    }

    @Override
    public void deleteBook(UUID bookId) {
        List<Book> bookList = bookshopRepository.getBookList()
                .stream()
                .filter(e -> !e.getId().equals(bookId))
                .toList();
        bookshopRepository.setBookList(bookList);

    }

    @Override
    public void editBook(UUID bookId, long quantity) {
        List<Book> updatedBookList = bookshopRepository.getBookList()
                .stream()
                .peek(book -> {
                    if (bookId == book.getId()) {
                        book.setQuantity(quantity);
                    }
                }).toList();
        bookshopRepository.setBookList(updatedBookList);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookshopRepository.getBookList();
    }

    @Override
    public Map<Client, List<Book>> getReservedBooks() {
        return new HashMap<>(bookshopRepository.getReservedBooks());
    }

    @Override
    public Map<Client, List<Book>> getBorrowedBooks() {
        return new HashMap<>(bookshopRepository.getBorrowedBooks());
    }

    @Override
    public Map<Book, Integer> getSoldOutBooks() {
        return new HashMap<>(bookshopRepository.getSoldOutBooks());
    }
}
