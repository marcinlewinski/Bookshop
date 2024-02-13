package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public class SoldOutBooksReportStrategy extends AbstractBookReportStrategy {
    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        StringBuilder report = getDefaultStringBuilder();
        for (Map.Entry<Book, Integer> entry : soldOutBooks.entrySet()) {
            report
                    .append(entry.getKey().getTitle())
                    .append(" sold ")
                    .append(entry.getValue())
                    .append(" copy \n");
        }
        return report.toString();
    }


    @Override
    protected StringBuilder getDefaultStringBuilder() {
        StringBuilder report = new StringBuilder("\nSold out Books report:");
        report.append("\n Available books:\n");

        return report;
    }
}
