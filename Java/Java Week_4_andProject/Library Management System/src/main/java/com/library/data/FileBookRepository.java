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
    public Book addBook(Book addBook) throws DataPersistenceException {
        String key = addBook.getIsbn();
        this.books.put(key, addBook);
        this.writeToFile();
        return addBook;
    }

    @Override
    public Book updateBook(Book updateBook) throws DataPersistenceException {
        String key = updateBook.getIsbn();
        this.books.put(key, updateBook);
        this.writeToFile();
        return updateBook;
    }

    @Override
    public Book removeBook(String isbn) throws DataPersistenceException {
        Book removedBook = this.books.remove(isbn);
        if (removedBook != null) {
            this.writeToFile();
        }
        return removedBook;
    }

    private void writeToFile() throws DataPersistenceException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            BookMapper bookMapper = new BookMapper();
            for (Book book : this.books.values()) {
                String bookAsText = bookMapper.marshall(book);
                writer.println(bookAsText);
            }
        } catch (IOException e) {
            // Catch the specific IO exception and wrap it in custom one
            throw new DataPersistenceException("Could not save book data.", e);
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
