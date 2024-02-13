package com.marcinl.repository;

import com.marcinl.mockData.MockDataProvider;
import com.marcinl.model.Book;
import com.marcinl.model.Client;
import com.marcinl.model.Genres;

import java.util.*;

public class BookshopRepository {
    MockDataProvider mockDataProvider;
    private List<Book> bookList;
    private List<Genres> genresList = new ArrayList<>();
    private List<Client> clientList;
    private Map<Client, List<Book>> borrowedBooks;
    private Map<Client, List<Book>> reservedBooks = new HashMap<>();
    private Map<Book, Integer> soldOutBooks;

    public BookshopRepository(MockDataProvider mockDataProvider) {
        this.mockDataProvider = mockDataProvider;
        this.bookList = mockDataProvider.generateAvailableBooks();
        this.clientList = mockDataProvider.generateAvailableClients();
        this.borrowedBooks = mockDataProvider.generateBorrowedBooks();
        this.soldOutBooks = mockDataProvider.generateSoldOutBooks();
    }

    public Map<Book, Integer> getSoldOutBooks() {
        return soldOutBooks;
    }

    public void setSoldOutBooks(Map<Book, Integer> soldOutBooks) {
        this.soldOutBooks = soldOutBooks;
    }

    public Map<Client, List<Book>> getReservedBooks() {
        return reservedBooks;
    }

    public Book findBookById(UUID id) {
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Client findClientByEmail(String email) {
        return clientList.stream()
                .filter(client -> client.email().equals(email))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public void removeBookFromReservedBooks(Book book) {
        this.reservedBooks.remove(book);
    }
    public void removeBookFromBookList(Book book) {
        this.reservedBooks.remove(book);
    }


    public void setReservedBooks(Map<Client, List<Book>> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Genres> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<Genres> genresList) {
        this.genresList = genresList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Map<Client, List<Book>> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Map<Client, List<Book>> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
