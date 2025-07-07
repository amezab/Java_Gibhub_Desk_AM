package com.library.data;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This is a fake repository that lives only in memory for testing.
public class BookRepositoryStub implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Book findBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> findAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book addBook(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public Book removeBook(String isbn) {
        return books.remove(isbn);
    }
}