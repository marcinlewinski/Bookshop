package com.marcinl.controller;

import com.marcinl.model.Book;
import com.marcinl.service.bookshop.Bookshop;
import com.marcinl.service.inventory.InventoryManager;
import com.marcinl.service.reportGenerator.ReportGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BookShopController {
    private final ReportGenerator reportGenerator;
    InventoryManager inventoryManager;
    Bookshop bookshop;


    public BookShopController(ReportGenerator reportGenerator, InventoryManager inventoryManager, Bookshop bookshop) {
        this.reportGenerator = reportGenerator;
        this.inventoryManager = inventoryManager;
        this.bookshop = bookshop;
    }

    public String generateReport() {
        return reportGenerator.generateReport(
                this.getAllBooks(),
                inventoryManager.getReservedBooks(),
                inventoryManager.getBorrowedBooks(),
                inventoryManager.getSoldOutBooks());
    }

    public void addBook(String title, String author, int year, BigDecimal price, long quantity) {
        inventoryManager.addNewBook(new Book(UUID.randomUUID(), title, author, year, price, quantity));
    }

    public List<Book> getAllBooks() {
        return inventoryManager.getAllBooks();
    }

    public void buyBook(UUID id) {
        bookshop.sellBook(id, "john.doe@example.com");
    }

    public void reserveBook(UUID id) {
        bookshop.reserveBook(id, "john.doe@example.com");
    }
}
