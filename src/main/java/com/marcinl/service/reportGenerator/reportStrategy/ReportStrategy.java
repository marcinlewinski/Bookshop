package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public interface ReportStrategy {
    String generateReport(
            List<Book> availableBooks,
            Map<Client, List<Book>> reservedBooks,
            Map<Client, List<Book>> borrowedBooks,
            Map<Book, Integer> soldOutBooks);
}
