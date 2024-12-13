package com.fractal.courses.model;

import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final String genre;
    private final int year;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getGenre() {
        return genre;
    }


    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.year == book.year &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(title, book.title);
    }

    @Override
    public String toString() {
        return "com.fractal.courses.model.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, year);
    }


}
