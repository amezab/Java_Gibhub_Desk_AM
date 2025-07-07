package com.library.data;
import com.library.data.file.BookMapper;
import com.library.model.Book;

import java.io.*;
import java.util.*;


public class FileBookRepository implements BookRepository {
    // Initialize the map right here where it's declared
    private final Map<String, Book> books = new HashMap<>();
    private final String filePath;

    // In FileBookRepository.java
    public FileBookRepository(String filePath) throws DataPersistenceException {
        this.filePath = filePath;
        this.loadFromFile();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> findAllBooks() {
        return  new ArrayList<>(books.values());
    }

    @Override
    public Book addBook(Book addBook) {
        String key = addBook.getIsbn();
        books.put(key, addBook);
        writeToFile();
        return addBook;
    }

    @Override
    public Book updateBook(Book updateBook) {
        String key = updateBook.getIsbn();
        books.put(key, updateBook);
        writeToFile();
        return updateBook;
    }

    @Override
    public Book removeBook(String isbn) throws DataPersistenceException {
        // Step 1: Remove the book from the in-memory map.
        // The .remove() method on a Map returns the value that was removed, or null if the key didn't exist.
        Book removedBook = this.books.remove(isbn);

        // Step 2: If a book was actually removed (i.e., it wasn't null), update the file.
        if (removedBook != null) {
            // We will update this method later to throw the exception properly.
            this.writeToFile();
        }

        // Step 3: Return the book that was removed, or null if no book was found.
        return removedBook;
    }

    private void writeToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Create an instance of our mapper tool
            BookMapper bookMapper = new BookMapper();

            for (Book book : books.values()) {
                // Use the mapper to convert the Book object to a String
                String bookAsText = bookMapper.marshall(book);

                // Use the writer to print that String as a new line in the file
                writer.println(bookAsText);
            }

        } catch (IOException e) {
            // For now, we'll just print an error if something goes wrong
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Create an instance of our mapper tool
            BookMapper mapper = new BookMapper();

            // Loop as long as the file has more lines to read
            while (scanner.hasNextLine()) {
                // Read the next line from the file
                String line = scanner.nextLine();

                // Use the mapper to convert the line of text into a Book object
                Book book = mapper.unmarshall(line);

                // Add the newly created Book object to our in-memory map
                books.put(book.getIsbn(), book);
            }
        } catch (FileNotFoundException e) {
             //we'll start with an empty library.
        }
    }
}
