package com.fractal.courses.specification;

import com.fractal.courses.model.BookTag;

public interface Factory {
    <T> Specification<T> create(BookTag bookTag);
}
