package com.example.crud.services;

import com.example.crud.dto.BookBinding;
import com.example.crud.entity.Books;
import com.example.crud.repository.BooksRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    public BooksRepo booksRepo;

    @Override
    public BookBinding createBook(BookBinding bookBinding) {
        Books books = new Books();
        BeanUtils.copyProperties(bookBinding, books);

        Books savedBook = booksRepo.save(books);

        BookBinding savedBookBinding = new BookBinding();
        BeanUtils.copyProperties(savedBook, savedBookBinding);

        return savedBookBinding;

    }

    @Override
    public List<BookBinding> getAllBooks() {
        List<Books> booksList = booksRepo.findAll();

        return booksList.stream()
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
        Specification<Books> specification = Specification.where(null);

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

        List<Books> books = booksRepo.findAll(specification);

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
