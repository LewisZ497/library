package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final File library = new File("library.txt");

    public static void main(String[] args) {
        createFile();
        menu();
    }

    public static String getInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    public static ArrayList<Book> getBookDetails() {
        boolean add = true;
        ArrayList<Book> addBooks = new ArrayList<>();
        while(add) {
            String bookName = getInput("Enter the title");
            String bookISBN = getInput("Enter the ISBN");
            String bookAuthor = getInput("Enter the author");
            String bookGenre = getInput("Enter the genre");
            Book book = new Book(bookName, bookISBN, bookAuthor, bookGenre);
            addBooks.add(book);
            boolean validInput = false;
            while(!validInput) {
                String addAnother = getInput("Would you like to add another book? (yes/no)");
                if(addAnother.equalsIgnoreCase("yes")) {
                    validInput = true;
                } else if(addAnother.equalsIgnoreCase("no")) {
                    add = false;
                    validInput = true;
                } else {
                    System.out.println("Not a valid input.");
                }
            }
        }
        return addBooks;
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

    public static ArrayList<String> formatBooks() {
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

    public static void writeToFile(ArrayList<Book> books) {
        try {
            FileWriter myWriter = new FileWriter(library.getName(), true);
            for (Book book : books) {
                myWriter.write(book.getTitle() + "," + book.getISBN() + "," + book.getAuthor() + "," + book.getGenre() + "\n");
            }
            myWriter.close();
            System.out.println("Added books to the library");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the library.txt file.");
            e.printStackTrace();
        }
    }

    public static void menu() {
        boolean validInput = false;
        while(!validInput) {
            int input = Integer.parseInt(getInput("Would you like to:" +
                    "\n1. Print the books in the library" +
                    "\n2. Add books to the library" +
                    "\n3. Remove a book from the library"));
            switch (input) {
                case 1:
                    System.out.println(formatBooks().size() == 0 ? "No books stored in the library" : String.join("\n", formatBooks()));
                    validInput = true;
                    break;
                case 2:
                    writeToFile(getBookDetails());
                    validInput = true;
                    break;
                case 3:
                    deleteBook();
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void deleteBook() {
        String name = getInput("Enter the name of the book you want to delete");
        ArrayList<String> books = readFile();
        System.out.println(books.size());
        System.out.println(name);
        //not finished
        System.out.println("Among Us");
    }

    public static Boolean logIn() {
        String username = getInput("Enter username");
        String password = getInput("Enter password");

        return true;
    }

    public static Boolean register() {
        Boolean valid = false;
        String username = getInput("Enter username");
        String password = getInput("Enter password");
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = usernamePattern.matcher(username);
        if (!matcher.matches()) {
            System.out.println("Invalid email");
        } else if(password.length() < 8) {
            // need to finish with more regex
        } else {
            valid = true;
        }
        return valid;
    }
}
