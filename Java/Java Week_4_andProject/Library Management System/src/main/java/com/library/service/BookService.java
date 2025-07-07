// In BookService.java
package com.library.service;

import com.library.data.DataPersistenceException;
import com.library.model.Book;
import com.library.service.exception.BookValidationException;

import java.time.Year;
import java.util.List;

public interface BookService {

    Book addBook(Book book) throws BookValidationException, DataPersistenceException;
    List<Book> findBooksByCategory(String category) throws DataPersistenceException;
    boolean removeBookByIsbn(String isbn) throws DataPersistenceException;
    Book findBookByIsbn(String isbn) throws DataPersistenceException;
    void updateBook(Book book) throws BookValidationException, DataPersistenceException;
    void validateYear(Year year) throws BookValidationException;
}