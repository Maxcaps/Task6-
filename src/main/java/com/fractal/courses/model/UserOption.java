package com.fractal.courses.model;

import com.fractal.courses.Main;
import com.fractal.courses.dao.BookDao;
import com.fractal.courses.dao.impl.BookDaoInMemory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;
import java.util.Set;

public class UserOption {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Scanner scanner = new Scanner(System.in);
    private final BookDao bookDao = new BookDaoInMemory();

    public void showMenu() {
        LOGGER.info("Choose one option: ");
        LOGGER.info("1. Add new book");
        LOGGER.info("2. Delete new book");
        LOGGER.info("3. Find books by tag");
        LOGGER.info("4. Sort books by tag");
        LOGGER.info("5. Show available books");
        int choice = inputInt("Choose option 1 - 5");

        switch (choice) {
            case 1 -> addBookByUser();
            case 2 -> removeBookByUser();
            case 3 -> findBookByUser();
            case 4 -> sortBooksByUser();
            case 5 -> showBooks();
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 4");
                showMenu();
            }
        }
        showMenu(); // рекурсия
    }

    public void sortBooksByUser() {
        LOGGER.info("Enter sorting tag (1. Title; 2. Author; 3. Creation year; 4. Genre)");
        int choice = inputInt("Choose option 1 - 4");

        switch (choice) {
            case 1 -> bookDao.sortByTag(BookTag.TITLE);
            case 2 -> bookDao.sortByTag(BookTag.AUTHOR);
            case 3 -> bookDao.sortByTag(BookTag.YEAR);
            case 4 -> bookDao.sortByTag(BookTag.GENRE);
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 4");
                sortBooksByUser();
                return;
            }
        }
        LOGGER.info(bookDao.getBookSet());
    }

    public void findBookByUser() {
        LOGGER.info("Enter finding tag (1. Title; 2. Author; 3. Creation year 4. Genre)");
        int choice = inputInt("Choose option 1 - 4");
        Set<Book> bookToFind = null;

        switch (choice) {
            case 1 -> {
                String title = inputString("Enter title: ");
                bookToFind = bookDao.findByTag(BookTag.TITLE, title);
            }
            case 2 -> {
                String author = inputString("Enter author: ");
                bookToFind = bookDao.findByTag(BookTag.AUTHOR, author);
            }
            case 3 -> {
                int year = inputInt("Enter creation year: ");
                bookToFind = bookDao.findByTag(BookTag.YEAR, year);
            }
            case 4 -> {
                String genre = inputString("Enter genre: ");
                bookToFind = bookDao.findByTag(BookTag.GENRE, genre);
            }
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 4");
                findBookByUser();
            }
        }
        if (bookToFind != null && !bookToFind.isEmpty()) {
            LOGGER.info("Find result books: " + bookToFind);
        }
    }

    public void addBookByUser() {
        String title = inputString("Enter book title");
        String author = inputString("Enter author");
        String genre = inputString("Enter book genre");
        int year = inputInt("Enter creation year");
        bookDao.addBook(new Book(title, author, genre, year));
    }

    public void removeBookByUser() {
        String title = inputString("Enter book title to delete");
        Set<Book> bookToDelete = bookDao.findByTag(BookTag.TITLE, title);
        if (bookToDelete.isEmpty()) {
            LOGGER.info("There is no books with this title");
        } else {
            for (Book book : bookToDelete) {
                bookDao.removeBook(book);
                LOGGER.info(book + "com.fractal.courses.model.Book was successfully removed");
            }
        }
    }

    public void showBooks() {
        LOGGER.info("Available books: ");
        bookDao.printAllBooks();
    }

    private String inputString(String message) {
        LOGGER.info(message);
        return scanner.nextLine();
    }

    private int inputInt(String message) {
        LOGGER.info(message);
        while (!scanner.hasNextInt()) {
            LOGGER.info("Invalid input. " + message);
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
