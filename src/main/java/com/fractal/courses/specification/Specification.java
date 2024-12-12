package com.fractal.courses.specification;

import com.fractal.courses.model.Book;

import java.util.Set;

public interface Specification <T> {
     Set<Book> find(Set<Book> books, T value);
     Set<Book> sort(Set<Book> books);
}
