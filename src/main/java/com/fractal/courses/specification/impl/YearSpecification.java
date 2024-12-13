package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;

import java.util.Comparator;

public class YearSpecification extends AbstractSpecification<Integer> {

    @Override
    public boolean match(Book book, Integer value) {
        return (book.getYear() == value);
    }

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getYear);
    }
}
