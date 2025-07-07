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

            // Year
            Year newYear = this.userIO.readYear("Year (" + bookToEdit.getYearPublished() + "): ");
            bookToEdit.setYearPublished(newYear); // This assumes you have a setYearPublished method

            bookService.updateBook(bookToEdit);
            System.out.println("[Success] Book updated.");

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
        String author = this.userIO.readRequiredString("Enter Author's name: ");
        String isbn = this.userIO.readRequiredString("Enter ISBN: ");
        int shelfNumber = this.userIO.readInt("Enter shelf number (1-250): ", 1, 250);
        int positionNumber = this.userIO.readInt("Enter position number (1-250): ", 1, 250);

        Year yearPublished;
        // This loop will continue until a valid year is entered.
        while (true) {
            try {
                yearPublished = this.userIO.readYear("Enter year published: ");
                // Immediately validate the year using our new service method
                this.bookService.validateYear(yearPublished);
                break; // If validation passes, exit the loop
            } catch (BookValidationException e) {
                System.out.println(e.getMessage()); // Print the specific error and loop again
            }
        }

        // Now that all data is valid, create and add the book
        Book newBook = new Book(categoryName, shelfNumber, positionNumber, yearPublished, author, isbn);
        try {
            this.bookService.addBook(newBook);
            System.out.println("[Success] Book " + categoryName + "-" + shelfNumber + "-" + positionNumber + " added.");
        } catch (BookValidationException | DataPersistenceException e) {
            // This catch is still needed for other potential errors like a duplicate ISBN
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

                System.out.println("Books in " + categoryName);
                System.out.printf("%-5s %-5s %-6s %-20s %s\n", "Shelf", "Pos", "Year", "Author", "ISBN");

                for (Book book : books) {
                    System.out.printf("%-5d %-5d %-6s %-20s %s\n",
                            book.getShelfNumber(),
                            book.getPosition(),
                            book.getYearPublished(),
                            book.getAuthor(),
                            book.getIsbn());
                }

            }
        } catch (DataPersistenceException e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }
}