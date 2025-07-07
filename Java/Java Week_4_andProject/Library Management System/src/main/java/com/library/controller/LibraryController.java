package com.library.controller;

import com.library.service.BookService;
import com.library.ui.BookOpsUI;
import com.library.ui.UserIO;

public class LibraryController {
    private final BookOpsUI bookOperationsUI;
    private final UserIO userIO;

    public LibraryController(BookService bookService, UserIO userIO) {
        this.bookOperationsUI = new BookOpsUI(bookService, userIO);
        this.userIO = userIO;
    }

    public void run() {
        while (true) {
            bookOperationsUI.displayMenu();
            int choice = userIO.readInt("Select [0-4]: ", 0, 4);
            //Maybe a choice to display whats currently on file?
            switch (choice) {
                case 1:
                    bookOperationsUI.bookToFind();
                    break;
                case 2:
                    bookOperationsUI.bookToAdd();
                    break;
                case 3:
                    bookOperationsUI.bookToUpdate();
                    break;
                case 4:
                    bookOperationsUI.bookToRemove();
                    break;
                case 0:
                    System.out.println(" Goodbye!");
                    return;
            }
        }
    }
}
