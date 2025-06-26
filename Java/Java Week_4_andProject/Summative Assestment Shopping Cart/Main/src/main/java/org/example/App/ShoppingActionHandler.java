package org.example.App;

import org.example.Cart.Cart;
import org.example.Models.Item;
import org.example.Services.ProductCatalog;
import org.example.utils.UserInputHandler;

import java.util.List;

public class ShoppingActionHandler {
    private final ProductCatalog productCatalog;
    private final Cart cart;
    private final UserInputHandler userInputHandler;

    public ShoppingActionHandler(ProductCatalog productCatalog, Cart cart, UserInputHandler userInputHandler){
        this.productCatalog = productCatalog;
        this.cart = cart;
        this.userInputHandler = userInputHandler;
    }

    public void handleViewInventory(){
        System.out.println("--- Store Inventory ---");
        List<Item> inventory = productCatalog.getInventoryList();
        for (Item currentItem : inventory) {
            System.out.printf("ID: %s | Name: %s | Price: $%.2f%n",currentItem.getId(), currentItem.getName(), currentItem.getPrice());
        }
    }

    public void handleViewCart() {
        System.out.println("--- Your Shopping Cart ---");
        if (cart.isCartEmpty()) {
            System.out.println("Your cart is currently empty.");
            return;
        }
        for (Item item : cart.getCartItems()) { // Assuming getCartItems() exists
            System.out.printf("ID: %s | Name: %s | Price: $%.2f | Quantity: %d%n",
                    item.getId(), item.getName(), item.getPrice(), item.getQuantity());
        }
        System.out.printf("Grand Total: $%.2f%n", cart.getGrandTotal());

    }

    public void handleAddItem () {
        System.out.println("Please Enter Product ID: ");
        String productId = userInputHandler.getStringInput();
        Item itemFound = productCatalog.findItemById(productId);

        if (itemFound == null) {
            System.out.println("Product ID not found. Please try again.");
        } else {
            System.out.print("Enter quantity: ");
            int quantity = userInputHandler.getIntegerInput();

            // Separating the cart item from the catalog Item
            Item itemForCart = new Item( itemFound.getId(),itemFound.getName(),quantity,itemFound.getPrice());
            // Add that new item to the cart.
            cart.addItem(itemForCart);
            System.out.println("Item Added Successfully!");
        }
    }

    public void handleRemoveItem () {
        if (cart.isCartEmpty()) {
            System.out.println("Cannot remove items from an empty cart.");
        }else {
            handleViewCart();

            System.out.println("Enter product Id to remove: ");
            String removedId = userInputHandler.getStringInput();

            System.out.println("Enter quantity to remove: ");
            int quantityToRemove = userInputHandler.getIntegerInput();

            cart.removeItem(removedId, quantityToRemove);
        }
    }

    public void handleCheckout () {
        if(cart.isCartEmpty()){
            System.out.println("There are no items in the cart to check out.");
        }else {
            double grandTotal = cart.getGrandTotal();
            System.out.printf("Your grand total is: $%.2f%n", grandTotal);
            cart.clearCart();
            System.out.println("Thanks for shopping: ");
        }
    }
}
