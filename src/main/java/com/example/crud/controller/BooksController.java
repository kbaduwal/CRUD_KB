package com.example.crud.controller;

import com.example.crud.dto.BookBinding;
import com.example.crud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    public BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookBinding> addBook(@RequestBody BookBinding bookBinding) {

        BookBinding createdBook = bookService.createBook(bookBinding);
        return ResponseEntity.status(201).body(createdBook);

    }

    @GetMapping
    public ResponseEntity<List<BookBinding>> getAllBooks() {
        List<BookBinding> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(200).body(allBooks);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookBinding>> getBooks(
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String bookGenre,
            @RequestParam(required = false) String bookName) {

        List<BookBinding> books = bookService.getBookByParameter(authorName, bookGenre, bookName);

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }


}
