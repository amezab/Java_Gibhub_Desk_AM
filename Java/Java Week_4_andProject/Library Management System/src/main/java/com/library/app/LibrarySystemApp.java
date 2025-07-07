package com.library.app;

import com.library.controller.LibraryController;
import com.library.data.BookRepository;
import com.library.data.DataPersistenceException;
import com.library.data.FileBookRepository;
import com.library.service.BookService;
import com.library.service.BookServiceImpl;
import com.library.ui.UserIO;

public class LibrarySystemApp {
    // In LibrarySystemApp.java
    public void run() {
        UserIO userIO = new UserIO();
        try {
            BookRepository bookRepository = new FileBookRepository("books.txt");
            BookService bookService = new BookServiceImpl(bookRepository);
            LibraryController libraryController = new LibraryController(bookService, userIO);
            libraryController.run();
        } catch (DataPersistenceException e) {
            System.out.println("FATAL ERROR: Could not start application.");
            System.out.println(e.getMessage());
        }
    }
}
