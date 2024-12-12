package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;

import java.util.Comparator;

public class AuthorSpecification extends AbstractSpecification<String> {
    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getTitle);
    }

    @Override
    public boolean match(Book book, String str) {
        return book.getAuthor().equalsIgnoreCase(str);
    }

}
