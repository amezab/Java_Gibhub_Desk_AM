package com.library.service;

import com.library.data.BookRepository;
import com.library.model.Book;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import com.library.service.exception.BookValidationException;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private static final int MAX_SHELF_OR_POSITION = 250;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) throws BookValidationException {
        validateBook(book);
        if (bookRepository.findBookByIsbn(book.getIsbn()) != null) {
            throw new BookValidationException("Book already exist");
        }
        return bookRepository.addBook(book);
    }
//    @Override
//    public List<Book> findAllBooks() {
//        return bookRepository.findAllBooks();
//    }
    @Override
    public List<Book> findBooksByCategory(String category) {
        List<Book> allBooks = bookRepository.findAllBooks();
        List<Book> results = new ArrayList<>();

        for (Book book : allBooks) {
            if (book.getCategory().equals(category)) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public boolean removeBookByIsbn(String isbn) {
        return false;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }




    @Override
    public Book updateBook(Book book) throws BookValidationException {
        validateBook(book);
        if (bookRepository.findBookByIsbn(book.getIsbn()) == null) {
            throw new BookValidationException("Book with this ISBN does not exist.");
        }
        return bookRepository.updateBook(book);
    }

    private void validateBook(Book book) throws BookValidationException {
        if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            throw new BookValidationException("Category is required.");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new BookValidationException("Book Author is required.");
        }

        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new BookValidationException("Isbn is required.");
        }

        if (book.getShelfNumber() < 1 || book.getShelfNumber() > MAX_SHELF_OR_POSITION ) {
            throw new BookValidationException("Shelf Number must be between 1 and 250.");
        }

        if (book.getPosition() < 1 || book.getPosition() > MAX_SHELF_OR_POSITION ) {
            throw new BookValidationException("Position Number must be between 1 and 250.");
        }

        if (book.getYearPublished().isAfter(Year.now())){
            throw new BookValidationException("Year Published must be in the past");
        }
    }
}


