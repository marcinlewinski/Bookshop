package com.marcinl.service.bookshop;

import com.marcinl.model.Book;
import com.marcinl.model.Client;
import com.marcinl.repository.BookshopRepository;
import com.marcinl.service.inventory.InventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookshopImpl implements Bookshop {
    private final BookshopRepository bookshopRepository;
    private final InventoryManager inventoryManager;

    public BookshopImpl(BookshopRepository bookshopRepository, InventoryManager inventoryManager) {
        this.bookshopRepository = bookshopRepository;
        this.inventoryManager = inventoryManager;
    }

    @Override
    public void reserveBook(UUID bookId, String email) {
        Client currentClient = bookshopRepository.findClientByEmail(email);
        if (currentClient == null) {
            System.out.println("Client not found");
            return;
        }

        Book currentBook = bookshopRepository.findBookById(bookId);
        if (currentBook == null) {
            System.out.println("Book not found");
            return;
        }

        if (currentBook.getQuantity() > 0) {
            reserveBookForClient(currentClient, currentBook);
            currentBook.updateQuantity();
        } else {
            inventoryManager.deleteBook(currentBook.getId());
            System.out.println("Book is not available");
        }
    }

    private void reserveBookForClient(Client client, Book book) {
        Map<Client, List<Book>> reservedBooks = bookshopRepository.getReservedBooks();
        List<Book> clientBooks = reservedBooks.getOrDefault(client, new ArrayList<>());
        clientBooks.add(book);
        reservedBooks.put(client, clientBooks);
        bookshopRepository.setReservedBooks(reservedBooks);
    }

    @Override
    public void sellBook(UUID bookId, String email) {
        Client currentClient = bookshopRepository.findClientByEmail(email);

        Book currentBook = bookshopRepository.findBookById(bookId);

        if (currentBook.getQuantity() > 0) {
            sellBookForClient(currentClient, currentBook);
        } else {
            System.out.println("Book is not available");
            inventoryManager.deleteBook(bookId);
        }
    }

    private void sellBookForClient(Client client, Book book) {
        if (isBookReservedByClient(book, client)) {
            bookshopRepository.removeBookFromReservedBooks(book);
        } else {
            bookshopRepository.removeBookFromBookList(book);
        }
        book.updateQuantity();
        updateSoldOutBooks(book);
    }

    private void updateSoldOutBooks(Book book) {
        Map<Book, Integer> soldOutBooks = bookshopRepository.getSoldOutBooks();
        Integer amountOfCopies = soldOutBooks.get(book);
        if (amountOfCopies != null) {
            soldOutBooks.replace(book, amountOfCopies, amountOfCopies + 1);
        } else {
            soldOutBooks.put(book, 1);
        }
        bookshopRepository.setSoldOutBooks(soldOutBooks);
    }

    private boolean isBookReservedByClient(Book book, Client client) {
        Map<Client, List<Book>> reservedBooks = bookshopRepository.getReservedBooks();
        List<Book> clientReservedBooks = reservedBooks.get(client);
        return clientReservedBooks != null &&
                clientReservedBooks.stream().anyMatch(book1 -> book1.getId().equals(book.getId()));
    }
}
