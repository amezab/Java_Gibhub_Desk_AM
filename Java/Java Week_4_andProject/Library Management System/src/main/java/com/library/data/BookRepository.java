package com.library.data;

import com.library.model.Book;

import java.util.List;

public interface BookRepository {
    Book findBookByIsbn(String isbn);
    List<Book> findAllBooks();
    Book addBook(Book addBook);
    Book updateBook(Book updateBook);
    Boolean deleteBookByIsbn(String isbn);

}
