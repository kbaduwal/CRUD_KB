package com.example.crud.services;

import com.example.crud.dto.BookBinding;
import com.example.crud.entity.Book;
import com.example.crud.repository.BooksRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    public BooksRepo booksRepo;

    @Override
    public BookBinding createBook(BookBinding bookBinding) {
        Book book = new Book();
        BeanUtils.copyProperties(bookBinding, book);

        Book savedBook = booksRepo.save(book);

        BookBinding savedBookBinding = new BookBinding();
        BeanUtils.copyProperties(savedBook, savedBookBinding);

        return savedBookBinding;

    }

    @Override
    public List<BookBinding> getAllBooks() {
        List<Book> bookList = booksRepo.findAll();

        return bookList.stream()
                .map(books -> new BookBinding(
                        books.getId(),
                        books.getBookName(),
                        books.getAuthorName(),
                        books.getPublishedDate(),
                        books.getBookGenre(),
                        books.getIsbn(),
                        books.getPublisher(),
                        books.getNumberOfCopies()
                )).toList();
    }

    @Override
    public List<BookBinding> getBookByParameter(String authorName, String bookGenre, String bookName) {
        Specification<Book> specification = Specification.where(null);

        if (authorName != null && !authorName.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("authorName"), "%" + authorName + "%")
            );
        }
        if (bookGenre != null && !bookGenre.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("bookGenre"), bookGenre)
            );
        }
        if (bookName != null && !bookName.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("bookName"), "%" + bookName + "%")
            );
        }

        List<Book> books = booksRepo.findAll(specification);

        // Convert List<Book> to List<BookBinding>
        return books.stream()
                .map(book -> new BookBinding(
                                book.getId(),
                                book.getBookName(),
                                book.getAuthorName(),
                                book.getPublishedDate(),
                                book.getPublisher(),
                                book.getIsbn(),
                                book.getBookGenre(),
                                book.getNumberOfCopies()
                        )
                )
                .collect(Collectors.toList());
    }


}
