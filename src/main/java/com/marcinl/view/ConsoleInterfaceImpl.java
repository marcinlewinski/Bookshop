package com.marcinl.view;

import com.marcinl.controller.BookShopController;
import com.marcinl.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterfaceImpl implements ConsoleInterface {
    private final BookShopController bookShopController;

    public ConsoleInterfaceImpl(BookShopController bookShopController) {
        this.bookShopController = bookShopController;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add new book");
            System.out.println("2. Make new reservation ");
            System.out.println("3. By book ");
            System.out.println("4. Generate report ");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addNewBook();
                case 2 -> buyOrReserveBook(Command.RESERVE);
                case 3 -> buyOrReserveBook(Command.BUY);
                case 4 -> System.out.println(bookShopController.generateReport());
                case 5 -> {
                    System.out.println("Exit.");
                    System.exit(0);
                }
                default -> System.out.println("Your chose is not correct, please try again!");
            }
        }

    }

    private void addNewBook() {
        System.out.println("Enter book details:");
        System.out.print("Title: ");
        String title = getUserInput();

        System.out.print("Author: ");
        String author = getUserInput();

        System.out.print("Year: ");
        int year = Integer.parseInt(getUserInput());

        System.out.print("Price: ");
        BigDecimal price = new BigDecimal(getUserInput());

        System.out.print("Quantity: ");
        long quantity = Long.parseLong(getUserInput());

        bookShopController.addBook(title, author, year, price, quantity);
        System.out.print("Book has been saved into DB");
    }
    private void buyOrReserveBook(Command command) {
        List<Book> books = bookShopController.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("All books:");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " | Price: " + book.getPrice());
            }

            System.out.print("Enter the number of the book you want to " + command + "  (or enter '0' to go back): ");
            int bookNumber = Integer.parseInt(getUserInput());

            if (bookNumber != 0 && bookNumber <= books.size()) {
                Book selectedBook = books.get(bookNumber - 1);

                if (command == Command.BUY) {
                    bookShopController.buyBook(selectedBook.getId());
                } else {
                    bookShopController.reserveBook(selectedBook.getId());
                }

                System.out.println("Book '" + selectedBook.getTitle() + "' purchased successfully.");
            } else {
                System.out.println("Invalid book number or operation canceled.");
            }
        }
    }


    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
