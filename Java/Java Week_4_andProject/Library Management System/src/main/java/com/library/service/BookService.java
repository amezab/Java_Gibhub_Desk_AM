package com.library.service;

import com.library.model.Book;
import com.library.service.exception.BookValidationException;
import java.util.List;

public interface BookService {

    Book addBook(Book book) throws BookValidationException;
    List<Book> findAllBooks();
    List<Book> findBooksByCategory(String category);
    boolean removeBookByIsbn(String isbn);
    Book updateBook(Book book) throws BookValidationException;
    Book findBookByIsbn(String isbn);
}