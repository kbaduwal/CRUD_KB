package com.example.crud.services;

import com.example.crud.dto.BookBinding;

import java.util.List;

//CRUD
public interface BookService {

    public BookBinding createBook(BookBinding bookBinding);

    public List<BookBinding> getAllBooks();

    public List<BookBinding> getBookByParameter(String authorName, String bookGenre, String bookName);

}
