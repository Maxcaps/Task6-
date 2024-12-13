package com.fractal.courses.specification.impl;

import com.fractal.courses.exceptions.UnknownFieldException;
import com.fractal.courses.model.BookTag;
import com.fractal.courses.specification.BookSpecificationFactory;
import com.fractal.courses.specification.Specification;

public class SpecificationFactory implements BookSpecificationFactory {
    private static final String UNKNOWN_FIELD = "Unknown book field. ";

    public Specification create(BookTag bookTag) throws UnknownFieldException {
        if (bookTag == null) {
            throw new UnknownFieldException("Null book tag received");
        }
        switch (bookTag) {
            case TITLE -> {
                return new TitleSpecification();
            }
            case AUTHOR -> {
                return new AuthorSpecification();
            }
            case GENRE -> {
                return new GenreSpecification();
            }
            case YEAR -> {
                return new YearSpecification();
            }
            default -> throw new UnknownFieldException(UNKNOWN_FIELD);
        }
    }
}
