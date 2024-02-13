package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public class InventoryReportStrategy extends AbstractBookReportStrategy {
    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        StringBuilder report = getDefaultStringBuilder();
        availableBooks.forEach(
                book -> report
                        .append(book.getTitle())
                        .append(" - ")
                        .append(book.getQuantity())
                        .append("copies \n"));
        return report.toString();
    }


    @Override
    protected StringBuilder getDefaultStringBuilder() {
        StringBuilder report = new StringBuilder("\nInventory books report:");
        report.append("\n Available books:\n");
        return report;
    }
}
