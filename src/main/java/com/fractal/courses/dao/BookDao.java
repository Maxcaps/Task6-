package com.fractal.courses.dao;

import com.fractal.courses.model.Book;
import com.fractal.courses.model.BookTag;

import java.util.Set;

public interface BookDao {
    void addBook(Book book);
    void removeBook(Book book);
    void printAllBooks();
    <T> Set<Book> findByTag(BookTag bookTag, T value);
    void sortByTag(BookTag bookTag);
    Set<Book> getBookSet();
}
