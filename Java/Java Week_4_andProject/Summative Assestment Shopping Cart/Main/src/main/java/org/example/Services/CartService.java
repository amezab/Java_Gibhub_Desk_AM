package org.example.Services;
import org.example.Cart.Cart;
import org.example.Models.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CartService implements Cart {

    // Instant lookups using the Item's unique ID.
    private final Map<String, Item> itemsInCart;

    // Constructor for CartService.
    public CartService() {
        this.itemsInCart = new HashMap<>();
    }

@Override
    public void addItem(Item itemToAdd) {
        String productId = itemToAdd.getId();

        // Check if the cart ALREADY contains this item using its ID.
        if (itemsInCart.containsKey(productId)) {
            // If yes, don't add a new entry. Just update the quantity.
            Item existingItem = itemsInCart.get(productId);
            int newQuantity = existingItem.getQuantity() + itemToAdd.getQuantity();
            existingItem.setQuantity(newQuantity);
        } else {
            // If no, this is a new product for the cart, so add it.
            itemsInCart.put(productId, itemToAdd);
        }
    }

@Override
    public void removeItem(String productId, int quantityToRemove) {
        if (!this.itemsInCart.containsKey(productId)) {
            //System.out.println("Error: Item with ID '" + productId + "' not found in cart.");
            return;
        }

        Item itemInCart = this.itemsInCart.get(productId);
        int newQuantity = itemInCart.getQuantity() - quantityToRemove;

        if (newQuantity > 0) {
            // There's still stock left, so just update the quantity.
            itemInCart.setQuantity(newQuantity);
            //System.out.println("--> Removed " + quantityToRemove + " of '" + itemInCart.getName() + "'. New quantity: " + newQuantity);
        } else {
            // The new quantity is 0 or less, so remove the item from the cart entirely.
            this.itemsInCart.remove(productId);
            //System.out.println("--> Removed all units of '" + itemInCart.getName() + "' from the cart.");
        }
    }

    @Override
    public void clearCart() {
        this.itemsInCart.clear();
    }


    @Override
    // Calculates and returns the total price of all items in the cart.
    public double getGrandTotal() {
        double runningTotal = 0;
        for (Item item : itemsInCart.values()) {
            runningTotal += item.getPrice() * item.getQuantity();
        }
        return runningTotal;
    }

    @Override
    public List<Item> getCartItems() {
        return new ArrayList<>(this.itemsInCart.values());
    }

    @Override
    public boolean isCartEmpty() {
        return this.itemsInCart.isEmpty();
    }


}