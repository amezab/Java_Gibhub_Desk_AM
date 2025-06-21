package org.example.Services;
import org.example.Models.Item;

import java.util.ArrayList;

public class CartService {
    //private field to hold the data (the list of items)
    private ArrayList<Item> items;

    //constructor to guarantee the class starts in a valid state.
    public CartService() {
        this.items = new ArrayList<>();
    }


    public void addItem(Item product){
        items.add(product);
        return;

    }

    public void displayCart() {
        for (Item item : items) {
            item.getName();
            item.getPrice();
            item.getQuantity();
            System.out.printf("%d x %s @ $%.2f each. Subtotal: $%.2f%n",
                    item.getQuantity(),
                    item.getName(),
                    item.getPrice(),
                    item.getPrice() * item.getQuantity());

        }
    }

    //Looping through the shopping cart to get the total
    public double getGrandTotal(){
        double runningTotal = 0;
        for (Item item : items){
            runningTotal += item.getPrice() * item.getQuantity();

        }
        return runningTotal;
    }



}
