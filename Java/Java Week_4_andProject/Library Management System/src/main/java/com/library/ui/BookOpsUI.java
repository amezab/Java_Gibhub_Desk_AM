package com.library.ui;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.exception.BookValidationException;

import java.time.Year;
import java.util.List;

public class BookOpsUI {
    private final BookService bookService;
    private final UserIO userIO;

    public BookOpsUI(BookService bookService, UserIO userIO) {
        this.bookService = bookService;
        this.userIO = userIO;
    }

    public void displayMenu() {
        System.out.println("===========================");
        System.out.println("Welcome to the Library Management System");
        System.out.println("===========================");
        System.out.println("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Find Books by Category");
        System.out.println("2. Add a Book");
        System.out.println("3. Update a Book");
        System.out.println("4. Remove a Book");
    }

    public void bookToRemove() {
        String isbnToRemove = userIO.readString("Enter ISBN of book to remove:");
        Book foundBook = bookService.findBookByIsbn(isbnToRemove);

        if (foundBook == null) {
            System.out.println("Book does not exist");
        } else {
            System.out.println("ISBN: " + foundBook.getIsbn());
            System.out.println("Author: " + foundBook.getAuthor());
            String confirm = userIO.readString("Are you sure you want to delete (y/n)");
            if (confirm.equals("y")) {
                bookService.removeBookByIsbn(isbnToRemove);
                System.out.println("Book has been deleted");
            } else {
                System.out.println("Book has not been deleted");
            }
        }
    }

    public void bookToUpdate() {
        String isbnUpdate = userIO.readString("Enter ISBN of book to update:");
        String updateName = userIO.readString("Enter category name:");
        String updateAuthor = userIO.readString("Enter Authors name:");
        int updateShelfNumber = userIO.readInt("Enter shelf number between: ", 1, 250);
        int updatePositionNumber = userIO.readInt("Enter position number between: ", 1, 250);
        Year updateYearPublished = Year.parse(userIO.readString("Enter year published:"));

        Book updateBook = new Book(updateName, updateShelfNumber, updatePositionNumber, updateYearPublished, updateAuthor, isbnUpdate);
        try {
            bookService.updateBook(updateBook);
            System.out.println("Book updated successfully");
        } catch (BookValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void bookToAdd() {
        String categoryName = userIO.readString("Enter category name:");
        String author = userIO.readString("Enter Authors name:");
        String isbn = userIO.readString("Enter isbn:");
        int shelfNumber = userIO.readInt("Enter shelf number between: ", 1, 250);
        int positionNumber = userIO.readInt("Enter position number between: ", 1, 250);
        Year yearPublished = Year.parse(userIO.readString("Enter year published:"));

        Book newBook = new Book(categoryName, shelfNumber, positionNumber, yearPublished, author, isbn);
        try {
            bookService.addBook(newBook);
            System.out.println("Book added successfully");
        } catch (BookValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void bookToFind() {
        String category = userIO.readString("Enter category name:");
        List<Book> books = bookService.findBooksByCategory(category);

        if (books.isEmpty()) {
            System.out.println("No books found in category: " + category);
        } else {
            System.out.println("Books in category '" + category + "':");
            System.out.println("==========================================");
            for (Book book : books) {
                System.out.println("Author: " + book.getAuthor());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Year: " + book.getYearPublished());
                System.out.println("Location: Shelf " + book.getShelfNumber() + ", Position " + book.getPosition());
                System.out.println("------------------------------------------");
            }
        }
    }
}