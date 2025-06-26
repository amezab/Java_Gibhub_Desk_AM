package org.example.Models;

public class Item {
    private final String id;
    private final String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int newQuantity) {
        // Security check to make sure an item is not 0
        if (newQuantity >= 0){
            this.quantity = newQuantity;
        }
    }
}