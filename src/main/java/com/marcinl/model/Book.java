package com.marcinl.model;

import com.marcinl.bookObserver.BookObserver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final String title;
    private final String author;


    private final int year;
    private final BigDecimal price;
    private long quantity;

    public void addObserver(BookObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookObserver observer) {
        observers.remove(observer);
    }


    private List<BookObserver> observers = new ArrayList<>();


    public Book(UUID id, String title, String author, int year, BigDecimal price, long quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void notifyObservers() {
        for (BookObserver observer : observers) {
            observer.update(this);
        }
    }

    public void updateQuantity() {
        this.quantity--;
        notifyObservers();
    }
}
