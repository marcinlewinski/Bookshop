package com.marcinl.mockData;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.math.BigDecimal;
import java.util.*;

public class MockDataProvider {

    public List<Book> generateAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book(UUID.randomUUID(), "The Great Gatsby", "F. Scott Fitzgerald", 1925, BigDecimal.valueOf(20.99), 100));
        availableBooks.add(new Book(UUID.randomUUID(), "To Kill a Mockingbird", "Harper Lee", 1960, BigDecimal.valueOf(15.50), 80));
        availableBooks.add(new Book(UUID.randomUUID(), "1984", "George Orwell", 1949, BigDecimal.valueOf(18.75), 120));
        return availableBooks;
    }

    public List<Client> generateAvailableClients() {
        List<Client> availableClient = new ArrayList<>();
        availableClient.add(new Client(UUID.randomUUID(), "john.doe@example.com"));
        availableClient.add(new Client(UUID.randomUUID(), "jane.smith@example.com"));
        return availableClient;
    }

    public Map<Client, List<Book>> generateReservedBooks() {
        Map<Client, List<Book>> reservedBooks = new HashMap<>();
        reservedBooks.put(generateAvailableClients().get(0), generateAvailableBooks().subList(0, 2));
        return reservedBooks;
    }

    public Map<Client, List<Book>> generateBorrowedBooks() {
        Map<Client, List<Book>> borrowedBooks = new HashMap<>();
        Client client2 = new Client(UUID.randomUUID(), "jane.smith@example.com");
        borrowedBooks.put(client2, Collections.singletonList(generateAvailableBooks().get(2)));
        return borrowedBooks;
    }

    public Map<Book, Integer> generateSoldOutBooks() {
        Map<Book, Integer> soldOutBooks = new HashMap<>();
        List<Book> availableBooks = generateAvailableBooks();
        soldOutBooks.put(availableBooks.get(0), 50);
        soldOutBooks.put(availableBooks.get(1), 30);
        soldOutBooks.put(availableBooks.get(2), 70);
        return soldOutBooks;
    }


}
