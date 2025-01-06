package com.example.crud.dto;

import java.awt.print.Book;

public class BookBinding {
    private Long id;
    private String bookName;
    private String authorName;
    private String publisher;
    private String publishedDate;
    private String isbn;
    private String bookGenre;
    private Integer numberOfCopies;

    public BookBinding(Long id, String bookName, String authorName, String publishedDate,
                       String publisher, String isbn, String bookGenre, Integer numberOfCopies) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.isbn = isbn;
        this.bookGenre = bookGenre;
        this.numberOfCopies = numberOfCopies;
    }

    public BookBinding() {

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
