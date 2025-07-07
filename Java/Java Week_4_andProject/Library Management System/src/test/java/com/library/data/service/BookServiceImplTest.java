package com.library.service;

import com.library.data.BookRepository;
import com.library.data.BookRepositoryStub;
import com.library.model.Book;
import com.library.service.exception.BookValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private BookService service;

    // Before each test, we create a new service instance
    // that uses our FAKE repository stub, not the real one.
    @BeforeEach
    void setUp() {
        BookRepository repositoryStub = new BookRepositoryStub();
        service = new BookServiceImpl(repositoryStub);
    }

    @Test
    void testAddBookHappyPath() throws Exception {
        // Arrange: Create a perfectly valid book.
        Book validBook = new Book("Valid Category", 10, 10, Year.of(2020), "Valid Author", "12345");

        // Act: Add the book through the service.
        Book result = service.addBook(validBook);

        // Assert: Check that the result is not null and the ISBN matches.
        assertNotNull(result);
        assertEquals("12345", result.getIsbn());
    }
    @Test
    void testAddBookFailsWithInvalidYear() {
        // ARRANGE: Create a book with an invalid (future) year.
        Book invalidBook = new Book("Category", 1, 1, Year.now().plusYears(1), "Author", "54321");

        // ACT & ASSERT: Verify that calling addBook with this invalid book
        // throws a BookValidationException.
        assertThrows(BookValidationException.class, () -> {
            // This is the code that we expect to throw the exception.
            service.addBook(invalidBook);
        });
    }
}