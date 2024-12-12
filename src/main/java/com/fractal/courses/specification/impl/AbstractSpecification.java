package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;
import com.fractal.courses.specification.Specification;

import java.util.*;

public abstract class AbstractSpecification<T> implements Specification<T> {
    private final String UNKNOWN_BOOK_TAG = "Unknown book tag";

    @Override
    public Set<Book> find(Set<Book> books, T value) {
        Set<Book> result = new HashSet<>();
        for (Book book : books) {
            if (match(book, value)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public Set<Book> sort(Set<Book> books) {
        // Преобразуем Set в List
        List<Book> bookList = new ArrayList<>(books);
        // Получаем компаратор
        Comparator<Book> comparator = getComparator();
        // Сортируем список
        bookList.sort(comparator);
        // Преобразуем обратно в Set (в зависимости от типа Set, будет либо LinkedHashSet, либо TreeSet для сохранения порядка)
        // Возвращаем отсортированный Set
        return new HashSet<>(bookList);
    }

    public abstract boolean match (Book book, T value);
    public abstract Comparator<Book> getComparator();
}
