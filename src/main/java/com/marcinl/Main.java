package com.marcinl;


import com.marcinl.controller.BookShopController;
import com.marcinl.mockData.MockDataProvider;
import com.marcinl.repository.BookshopRepository;
import com.marcinl.service.bookshop.BookshopImpl;
import com.marcinl.service.inventory.InventoryManager;
import com.marcinl.service.inventory.InventoryManagerImpl;
import com.marcinl.service.reportGenerator.ReportGenerator;
import com.marcinl.service.reportGenerator.reportStrategy.*;
import com.marcinl.view.ConsoleInterface;
import com.marcinl.view.ConsoleInterfaceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ReportStrategy> strategies = new ArrayList<>();
        strategies.add(new InventoryReportStrategy());
        strategies.add(new BorrowedBookReportStrategy());
        strategies.add(new ReservedBooksReportStrategy());
        strategies.add(new SoldOutBooksReportStrategy());
        BookshopRepository bookshopRepository = new BookshopRepository(new MockDataProvider());
        InventoryManager inventoryManager = new InventoryManagerImpl(bookshopRepository);

        ConsoleInterface consoleInterface = new ConsoleInterfaceImpl(
                new BookShopController(
                        new ReportGenerator(new GeneralReportStrategy(strategies)),
                        inventoryManager,
                        new BookshopImpl(bookshopRepository, inventoryManager)
                )
        );
        consoleInterface.start();
    }
}