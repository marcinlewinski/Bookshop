package com.marcinl.service.reportGenerator;

import com.marcinl.model.Book;
import com.marcinl.model.Client;
import com.marcinl.service.reportGenerator.reportStrategy.ReportStrategy;

import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private final ReportStrategy reportStrategy;

    public ReportGenerator(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public String generateReport(
            List<Book> availableBooks,
            Map<Client, List<Book>> reservedBooks,
            Map<Client, List<Book>> borrowedBooks,
            Map<Book, Integer> soldOutBooks) {
        return reportStrategy.generateReport(
                availableBooks,
                reservedBooks,
                borrowedBooks,
                soldOutBooks);
    }
}
