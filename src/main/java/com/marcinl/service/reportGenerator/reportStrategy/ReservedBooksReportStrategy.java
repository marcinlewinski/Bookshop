package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public class ReservedBooksReportStrategy extends AbstractBookReportStrategy {
    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        return mapOverReservedOrBorrowedBooks(reservedBooks, getDefaultStringBuilder());
    }

    @Override
    protected StringBuilder getDefaultStringBuilder() {
        StringBuilder report = new StringBuilder("\nReserved books report:");
        report.append("\n Available books:\n");
        return report;
    }
}
