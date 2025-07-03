package com.library.model;


import java.time.Year;
//POJO(Plain Old Java Object)
public class Book {
    //Encapsulation.
    private String category;
    private int shelfNumber;
    private int position;
    private Year yearPublished;
    private String author;
    private String isbn;
    //private String title;

    public Book(String category, int shelfNumber, int position, Year yearPublished, String author, String isbn) {
        //Ensuring object is always created in a valid state.
        this.category = category;
        this.shelfNumber = shelfNumber;
        this.position = position;
        this.yearPublished = yearPublished;
        this.author = author;
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Year getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Year yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    //Immutability
    public String getIsbn() {
        return isbn;
    }
}
