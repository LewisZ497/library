package com.company;

import java.util.ArrayList;

public class Borrower extends User {

    private ArrayList<Book> books;

    public Borrower(String name, ArrayList<Book> books) {
        super(name);
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
