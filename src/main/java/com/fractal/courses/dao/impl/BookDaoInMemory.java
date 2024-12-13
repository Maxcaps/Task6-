package com.fractal.courses.dao.impl;

import com.fractal.courses.Main;
import com.fractal.courses.dao.BookDao;
import com.fractal.courses.exceptions.UnknownFieldException;
import com.fractal.courses.model.Book;
import com.fractal.courses.model.BookTag;
import com.fractal.courses.specification.Specification;
import com.fractal.courses.specification.impl.SpecificationFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookDaoInMemory implements BookDao {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Set<Book> bookSet = new HashSet<>();
    private SpecificationFactory specificationFactory = new SpecificationFactory();

    @Override
    public <T> Set<Book> findByTag(BookTag bookTag, T value) throws UnknownFieldException {
        if (bookTag == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        Specification<T> currentSpec = specificationFactory.create(bookTag);
        Set<Book> findResultBook = currentSpec.find(bookSet, value);
        if (findResultBook.isEmpty()) {
            LOGGER.info("There is no bookSet with this tag: " + value);
        }
        return findResultBook;
    }

    @Override
    public List<Book> sortByTag(BookTag bookTag) throws UnknownFieldException {
        if (bookTag == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        Specification<?> currentSpec = specificationFactory.create(bookTag);
        return currentSpec.sort(bookSet);
    }

    @Override
    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void addBook(Book book) {
        boolean isAlreadyExist = bookSet.add(book);
        if (isAlreadyExist) {
            LOGGER.info("com.fractal.courses.model.Book added" + book);
        } else {
            LOGGER.info("com.fractal.courses.model.Book is already in the collection" + book);
        }
    }

    public void removeBook(Book book) {
        boolean isDeleted = bookSet.remove(book);
        if (isDeleted) {
            LOGGER.info("Removed book" + book);
        } else {
            LOGGER.info("com.fractal.courses.model.Book not found:" + book);
        }
    }

    public void printAllBooks() {
        if (bookSet.isEmpty()) {
            LOGGER.info("No book available.");
        } else {
            LOGGER.info("bookSet in collection:");
            for (Book book : bookSet) {
                LOGGER.info(book.toString());
            }
        }
    }


}
