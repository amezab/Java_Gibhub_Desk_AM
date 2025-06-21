package org.example.Models;

// Declare the private fields to hold the data.
//Applying Encapsulation
public class Item {
    private final String name;
    private int quantity;
    private final double price;

    //The constructor that runs when we use 'new Item(...)
    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // QUESTIONS (GETTERS)
    //public method used to retrieve or "get" the value of a private instance variable within a class
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    //Safely changing a private field, including validation logic
    public void setQuantity(int newQuantity) {
        /* Security check to make sure an item is not 0 */
        if (newQuantity >= 0){
            this.quantity = newQuantity;
        }
    }
}