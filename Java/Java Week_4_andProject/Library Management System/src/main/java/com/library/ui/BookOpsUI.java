package com.library.ui;

import com.library.data.DataPersistenceException;
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
        try {
            String isbnToRemove = this.userIO.readString("Enter ISBN of book to remove: ");
            Book foundBook = this.bookService.findBookByIsbn(isbnToRemove);

            if (foundBook == null) {
                System.out.println("[Err] No book with that ISBN exists.");
            } else {
                System.out.println("Found Book:");
                System.out.println("  ISBN: " + foundBook.getIsbn());
                System.out.println("  Author: " + foundBook.getAuthor());
                String confirm = this.userIO.readString("Are you sure you want to delete this book? (y/n)");

                if (confirm.equalsIgnoreCase("y")) {
                    boolean removed = this.bookService.removeBookByIsbn(isbnToRemove);
                    if (removed) {
                        System.out.println("[Success] Book has been deleted.");
                    } else {
                        // This case is unlikely if the book was just found, but it's good practice.
                        System.out.println("[Err] Book could not be deleted.");
                    }
                } else {
                    System.out.println("Delete operation cancelled.");
                }
            }
        } catch (DataPersistenceException e) {
            // This will catch file-related errors from the repository.
            System.out.println("[Err] A data storage error occurred: " + e.getMessage());
        }
    }

    public void bookToUpdate() {
        try {
            String isbnToUpdate = this.userIO.readRequiredString("Enter the ISBN of the book to edit: ");
            Book bookToEdit = bookService.findBookByIsbn(isbnToUpdate);

            if (bookToEdit == null) {
                System.out.println("[Err] No book with that ISBN exists.");
                return; // Exit the method
            }

            System.out.println("Editing: " + bookToEdit.getIsbn() + " - " + bookToEdit.getAuthor());
            System.out.println("Press [Enter] to keep the current value. ");

            // Category
            String newCategory = this.userIO.readString("Category (" + bookToEdit.getCategory() + "): ");
            if (!newCategory.trim().isEmpty()) {
                bookToEdit.setCategory(newCategory);
            }

            // Author
            String newAuthor = this.userIO.readString("Author (" + bookToEdit.getAuthor() + "): ");
            if (!newAuthor.trim().isEmpty()) {
                bookToEdit.setAuthor(newAuthor);
            }

            // Add setters for Shelf Number, Position, and Year to your Book model if they are missing.
            // We will need to create a readYear method in UserIO for proper validation.
            // For now, let's focus on text fields.

            bookService.updateBook(bookToEdit);

// Create the specific identifier from the book's data
            String bookIdentifier = bookToEdit.getCategory() + "-" + bookToEdit.getShelfNumber() + "-" + bookToEdit.getPosition();

// Use the identifier to create the new, specific message
            System.out.print("[Success] ");
            System.out.printf(" \nBook %s has been updated.\n", bookIdentifier);


        } catch (DataPersistenceException | BookValidationException e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }

    public void bookToAdd() {
        String categoryName = this.userIO.readRequiredString("Enter category name: ");
        String author = this.userIO.readString("Enter Authors name: ");
        String isbn = this.userIO.readString("Enter isbn: ");
        int shelfNumber = this.userIO.readInt("Enter shelf number between: 1-250: ", 1, 250);
        int positionNumber = this.userIO.readInt("Enter position number between: 1-250: ", 1, 250);
        Year yearPublished = Year.parse(this.userIO.readString("Enter year published: "));
        Book newBook = new Book(categoryName, shelfNumber, positionNumber, yearPublished, author, isbn);

        try {
            this.bookService.addBook(newBook);
            System.out.println("[Success] ");
            System.out.println("Book "+ categoryName + "-" + shelfNumber + "-" + positionNumber  + " added.");
        } catch (BookValidationException | DataPersistenceException e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }

    public void bookToFind() {
        List<Book> books = null;
        try {
            String categoryName = this.userIO.readRequiredString("Enter category name: ");
            books = this.bookService.findBooksByCategory(categoryName);
            if (books.isEmpty()) {
                System.out.println("No books found in category: " + categoryName);
            } else {
                // --- Start of Changes ---

                // 1. Print the simpler category header.
                System.out.println("Books in " + categoryName);

                // 2. Print the table column headers.
                System.out.printf("%-5s %-5s %-6s %-20s %s\n", "Shelf", "Pos", "Year", "Author", "ISBN");

                // 3. Loop through the books and print each one as a formatted row.
                for (Book book : books) {
                    System.out.printf("%-5d %-5d %-6s %-20s %s\n",
                            book.getShelfNumber(),
                            book.getPosition(),
                            book.getYearPublished(),
                            book.getAuthor(),
                            book.getIsbn());
                }
                // --- End of Changes ---
            }
        } catch (DataPersistenceException e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }
}