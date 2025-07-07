// In BookService.java
package com.library.service;

import com.library.data.DataPersistenceException;
import com.library.model.Book;
import com.library.service.exception.BookValidationException;
import java.util.List;

public interface BookService {

    // In BookService.java

    Book addBook(Book book) throws BookValidationException, DataPersistenceException;

    List<Book> findBooksByCategory(String category) throws DataPersistenceException;

    boolean removeBookByIsbn(String isbn) throws DataPersistenceException;

    // In BookService.java

    Book findBookByIsbn(String isbn) throws DataPersistenceException;

    // In BookService.java

    Book updateBook(Book book) throws BookValidationException, DataPersistenceException;
}