package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final File library = new File("library.txt");
    private static final File adminsFile = new File("admins.txt");
    private static ArrayList<String> books = new ArrayList<>();
    private static ArrayList<String> admins = new ArrayList<>();

    public static void main(String[] args) {
        if(!logIn()) return;
        createFiles();
        readFile();
        menu();
    }

    public static String getInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    public static void getBookDetails() {
        boolean add = true;
        while(add) {
            String bookName = getInput("Enter the title");
            String bookISBN = getInput("Enter the ISBN");
            String bookAuthor = getInput("Enter the author");
            String bookGenre = getInput("Enter the genre");
            books.add(bookName + "," + bookISBN + "," + bookAuthor + "," + bookGenre);
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
    }

    public static void createFiles() {
        try {
            if(adminsFile.createNewFile()) {
                System.out.println("admins.txt did not exist. I have created the file.");
            }
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        try {
            if(library.createNewFile()) {
                System.out.println("library.txt did not exist. I have created the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the library.txt file.");
            e.printStackTrace();
        }
    }

    public static void readFile() {
        ArrayList<String> booksFromFile = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(library);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                booksFromFile.add(data + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the library.txt file.");
            e.printStackTrace();
        }
        books = booksFromFile;
    }

    public static ArrayList<String> formatBooks() {
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

    public static void menu() {
        boolean validInput = false;
        boolean run = true;
        while(run) {
            while(!validInput) {
                int input = Integer.parseInt(getInput("Would you like to:" +
                        "\n1. Add a new admin to the library" +
                        "\n2. Print the books in the library" +
                        "\n3. Add books to the library" +
                        "\n4. Remove a book from the library" +
                        "\n5. End the program"));
                switch (input) {
                    case 1:
                        register();
                        validInput = true;
                        break;
                    case 2:
                        System.out.println(formatBooks().size() == 0 ? "No books stored in the library" : String.join("\n", formatBooks()));
                        validInput = true;
                        break;
                    case 3:
                        getBookDetails();
                        validInput = true;
                        break;
                    case 4:
                        deleteBook();
                        validInput = true;
                        break;
                    case 5:
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
    }

    public static void deleteBook() {
        String name = getInput("Enter the name of the book you want to delete");
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

    public static void register() {
        String username = getInput("Enter username");
        String password = getInput("Enter password");
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);
        Matcher usernameMatcher = usernamePattern.matcher(username);
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!usernameMatcher.matches()) {
            System.out.println("Invalid email");
            return;
        } else if(!passwordMatcher.matches()) {
            System.out.println("Invalid password. It must have at least one number, one lower case letter, and one upper case letter");
            return;
        } else {
            admins.add(username + " " + password);
            System.out.println("Added admin to library");
        }
    }
}
