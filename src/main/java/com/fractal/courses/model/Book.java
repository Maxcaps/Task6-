package com.fractal.courses.model;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String  genre;
    private int year;

    public Book (String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle () {
        return title;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public  String getAuthor () {
        return author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre () {
        return genre;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    @Override
    public boolean equals (Object o) {
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
