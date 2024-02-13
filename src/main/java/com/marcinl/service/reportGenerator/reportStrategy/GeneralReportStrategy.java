package com.marcinl.service.reportGenerator.reportStrategy;

import com.marcinl.model.Book;
import com.marcinl.model.Client;

import java.util.List;
import java.util.Map;

public class GeneralReportStrategy extends AbstractBookReportStrategy {
    private final List<ReportStrategy> strategies;

    public GeneralReportStrategy(List<ReportStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String generateReport(List<Book> availableBooks, Map<Client, List<Book>> reservedBooks, Map<Client, List<Book>> borrowedBooks, Map<Book, Integer> soldOutBooks) {
        StringBuilder report = getDefaultStringBuilder();
        for (ReportStrategy strategy : strategies) {
            report.append(strategy.generateReport(availableBooks, reservedBooks, borrowedBooks, soldOutBooks));
        }
        return report.toString();

    }

    @Override
    protected StringBuilder getDefaultStringBuilder() {
        return new StringBuilder("General Report:");
    }
}
