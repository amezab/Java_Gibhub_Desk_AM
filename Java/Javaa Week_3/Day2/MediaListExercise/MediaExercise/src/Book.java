public class Book extends Media{
    private  String bookAuthor;
    private int numberOfPages;

    public Book(String name, String bookAuthor, int numberOfPages) {
        super(name);
        this.bookAuthor = bookAuthor;
        this.numberOfPages = numberOfPages;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public void play() {

    }

    @Override
    public String getDescription() {
        return "";
    }
}