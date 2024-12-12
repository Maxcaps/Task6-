package com.fractal.courses.dao.impl;

import java.util.HashSet;
import java.util.Set;

import com.fractal.courses.model.Book;
import com.fractal.courses.model.BookTag;
import com.fractal.courses.model.Main;
import com.fractal.courses.exceptions.UnknownFieldException;
import com.fractal.courses.dao.BookDao;
import com.fractal.courses.specification.impl.SpecificationFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.fractal.courses.specification.Specification;


public class BookDaoInMemory implements BookDao {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Set<Book> bookSet = new HashSet<>();
    private Set<Book> books = new HashSet<>();
    private SpecificationFactory specificationFactory = new SpecificationFactory();

    @Override
    public <T> Set<Book> findByTag(BookTag bookTag, T value) throws UnknownFieldException {
        if (bookTag == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        Specification<T> currentSpec = specificationFactory.create(bookTag);
        Set<Book> findResultBook = currentSpec.find(books, value);
        if (findResultBook.isEmpty()) {
            LOGGER.info("There is no books with this tag: " + value);
        }
        return findResultBook;
    }

    @Override
    public void sortByTag(BookTag tag) throws UnknownFieldException {
        Specification<?> currentSpec = specificationFactory.create(tag);
        books = currentSpec.sort(books);
    }

    @Override
    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void addBook(Book book) {
        boolean isTrue = books.add(book);
        if (isTrue) {
            LOGGER.info("com.fractal.courses.model.Book added" + book);
        } else {
            LOGGER.info("com.fractal.courses.model.Book is already in the collection" + book);
        }
    }

    public void removeBook(Book book) {
        boolean isTrue = books.remove(book);
        if (isTrue) {
            LOGGER.info("Removed book" + book);
        } else {
            LOGGER.info("com.fractal.courses.model.Book not found:" + book);
        }
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            LOGGER.info("No book available.");
        } else {
            LOGGER.info("Books in collection:");
            for (Book book : books) {
                LOGGER.info(book.toString());
            }
        }
    }


}
