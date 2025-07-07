package com.library.data;

import com.library.model.Book;
import java.util.List;

public interface BookRepository {

    Book findBookByIsbn(String isbn) throws DataPersistenceException;

    List<Book> findAllBooks() throws DataPersistenceException;

    Book addBook(Book book) throws DataPersistenceException;

    Book updateBook(Book book) throws DataPersistenceException;

    Book removeBook(String isbn) throws DataPersistenceException;
}