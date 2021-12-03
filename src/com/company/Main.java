package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final File library = new File("library.txt");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        createFile();
        menu();
    }

    public static ArrayList<String> getInput() {
        boolean add = true;
        ArrayList<String> books = new ArrayList<>();
        while(add) {
            String book = "";
            System.out.println("Enter the book title");
            book += scanner.next();
            System.out.println("Enter the ISBN");
            book += "," + scanner.nextInt();
            System.out.println("Enter the author");
            book += "," + scanner.next();
            System.out.println("Enter the genre");
            book += "," + scanner.next();
            books.add(book);
            System.out.println("Would you like to add another book? (yes/no)");
            boolean validInput = false;
            while(!validInput) {
                String addAnother = scanner.next();
                if(addAnother.equalsIgnoreCase("yes")) {
                    validInput = true;
                    book += "\n";
                } else if(addAnother.equalsIgnoreCase("no")) {
                    add = false;
                    validInput = true;
                } else {
                    System.out.println("Not a valid input.");
                }
            }
        }
        return books;
    }

    public static void createFile() {
        try {
            if(library.createNewFile()) {
                System.out.println("library.txt did not exist. I have created the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the library.txt file.");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile() {
        ArrayList<String> books = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(library);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                books.add(data + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the library.txt file.");
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<String> printBooks() {
        ArrayList<String> books = readFile();
        ArrayList<String> prettyBooks = new ArrayList<>();
        for (String book : books) {
            String[] bookDetails = book.split(",");
            String bookDetail = "";
            bookDetail += "Book Title: " + bookDetails[0];
            bookDetail += "\nISBN: " + bookDetails[1];
            bookDetail += "\nAuthor: " + bookDetails[2];
            bookDetail += "\nGenre: " + bookDetails[3];
            prettyBooks.add(bookDetail);
        }
        return prettyBooks;
    }

    public static void writeToFile(ArrayList<String> books) {
        try {
            FileWriter myWriter = new FileWriter(library.getName(), true);
            for (String book : books) {
                myWriter.write(book + "\n");
            }
            myWriter.close();
            System.out.println("Added books to the library");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the library.txt file.");
            e.printStackTrace();
        }
    }

    public static void menu() {
        System.out.println("Would you like to:" +
                "\n1. Print the books in the library" +
                "\n2. Add books to the library" +
                "\n3. Remove a book from the library");
        boolean validInput = false;
        while(!validInput) {
            int input = scanner.nextInt();
            if(input == 1) {
                System.out.println(printBooks().size() == 0 ? "No books stored in the library" : String.join("\n", printBooks()));
                validInput = true;
            } else if(input == 2) {
                writeToFile(getInput());
                validInput = true;
            } else if(input == 3) {
                deleteBook();
                validInput = true;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static void deleteBook() {
        System.out.println("Enter the ISBN of the book you want to delete");
        int ISBN = scanner.nextInt();
        ArrayList<String> books = readFile();
        System.out.println(books.size());
        //not finished
    }
}
