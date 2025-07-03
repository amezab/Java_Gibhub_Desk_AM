package com.library.data.file;
import com.library.model.Book;
import java.time.Year;


public class BookMapper {
    private static final String DELIMITER = "::";

    public String marshall(Book book) {
        return book.getIsbn() + DELIMITER + book.getAuthor() + DELIMITER + book.getYearPublished()
                + DELIMITER + book.getShelfNumber() + DELIMITER + book.getPosition() + DELIMITER + book.getCategory();
    }

    public Book unmarshall(String line) {
        String[] part = line.split(DELIMITER);
        String isbn = part[0];
        String author = part[1];
        Year yearPublished = Year.parse(part[2]);
        int shelfNumber = Integer.parseInt(part[3]);
        int position = Integer.parseInt(part[4]);
        String category = part[5];

        return new Book(category, shelfNumber, position, yearPublished, author, isbn);
    }

}




