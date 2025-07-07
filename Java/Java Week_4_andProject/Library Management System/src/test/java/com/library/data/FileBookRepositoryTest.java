package com.library.data;

import com.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class FileBookRepositoryTest {

    private FileBookRepository repository;
    private final String TEST_FILE_PATH = "test_books.txt";

    @BeforeEach
    void setUp() throws Exception {
        // This deletes any "test_books.txt" file from a previous test run.
        new File(TEST_FILE_PATH).delete();

        // This creates a new repository instance that will write to our blank test file.
        repository = new FileBookRepository(TEST_FILE_PATH);
    }
    @Test
    void testAddAndFindBook() throws Exception {
        // ARRANGE: Create a book object to work with.
        Book newBook = new Book("Fantasy", 1, 1, Year.of(2001), "Neil Gaiman", "978-0380789030");

        // ACT: Perform the action we want to test.
        repository.addBook(newBook);

        // ASSERT: Check if the result is what we expected.
        Book retrievedBook = repository.findBookByIsbn("978-0380789030");

        assertNotNull(retrievedBook); // Asserts that the book we found is not null.
        assertEquals("Neil Gaiman", retrievedBook.getAuthor()); // Asserts that the author is correct.
    }

    @Test
    void testRemoveBook() throws Exception {
        // ARRANGE: Add a book so there is something to remove.
        Book newBook = new Book("Fantasy", 1, 1, Year.of(2001), "Neil Gaiman", "978-0380789030");
        repository.addBook(newBook);

        // ACT: Remove the book.
        repository.removeBook("978-0380789030");

        // ASSERT: Try to find the book again.
        Book retrievedBook = repository.findBookByIsbn("978-0380789030");

        assertNull(retrievedBook); // Asserts that the book is now null because it was deleted.
    }

}