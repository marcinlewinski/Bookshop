package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public abstract class AbstractBookReportStrategy implements ReportStrategy {

    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        StringBuilder report = getDefaultStringBuilder();

        return mapOverReservedOrBorrowedBooks(borrowedBooks, report);
    }

    protected String mapOverReservedOrBorrowedBooks(Map<Client, List<Book>> borrowedBooks, StringBuilder report) {
        for (Map.Entry<Client, List<Book>> entry : borrowedBooks.entrySet()) {
            Client client = entry.getKey();
            List<Book> clientBorrowedBooks = entry.getValue();
            for (Book book : clientBorrowedBooks) {
                report.append(client.email()).append(" book: ").append(book.getTitle()).append("\n");
            }
        }
        return report.toString();
    }


    protected StringBuilder getDefaultStringBuilder() {
        StringBuilder report = new StringBuilder("Books report:");
        report.append("\n Available books:\n");
        return report;
    }
}
