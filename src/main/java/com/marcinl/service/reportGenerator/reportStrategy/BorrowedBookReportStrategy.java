package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public class BorrowedBookReportStrategy extends AbstractBookReportStrategy {
    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        return mapOverReservedOrBorrowedBooks(borrowedBooks, getDefaultStringBuilder());
    }

    @Override
    protected StringBuilder getDefaultStringBuilder() {
        StringBuilder report = new StringBuilder("\nBorrowed books report:");
        report.append("\n Available books:\n");
        return report;
    }

}
