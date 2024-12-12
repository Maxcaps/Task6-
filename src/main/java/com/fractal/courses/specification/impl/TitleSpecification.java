package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;

import java.util.Comparator;

public class TitleSpecification extends AbstractSpecification<String> {

    @Override
    public boolean match(Book book, String str) {
        return book.getTitle().equalsIgnoreCase(str);
    }

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getTitle);
    }
}
