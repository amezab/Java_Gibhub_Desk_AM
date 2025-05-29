

public class Book {
    String title;
    String author;
    boolean isAvailable;



    //Creates the Book object, often times we would pass in started data
    public Book (String title, String author, String isAvailable){
        this.title = title;
        this.author = author;
        this.isAvailable = true;

    }

    //A method displayInfo() that prints the car’s details.

    public void borrowBook() {
        System.out.println("Title" + title + "Author " + author + "Its Available " + isAvailable);
        System.out.println(("Borrowing the book.. "));
        System.out.println("Title" + title + "Author " + author + "Its Available " + (isAvailable = false));

    }



}
