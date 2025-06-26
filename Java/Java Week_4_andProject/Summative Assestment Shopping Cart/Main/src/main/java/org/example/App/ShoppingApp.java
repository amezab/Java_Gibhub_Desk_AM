package org.example.App;

import org.example.Cart.Cart;
import org.example.Services.CartService;
import org.example.Services.ProductCatalog;
import org.example.utils.UserInputHandler;

import java.util.Scanner;

public class ShoppingApp {
    private final UserInputHandler userInputHandler;
    private final ShoppingActionHandler actionHandler;

    public ShoppingApp() {
        Scanner scanner = new Scanner(System.in);
        this.userInputHandler = new UserInputHandler(scanner);

        ProductCatalog productCatalog = new ProductCatalog();

        Cart cart = new CartService();
        this.actionHandler = new ShoppingActionHandler(productCatalog, cart, this.userInputHandler);
    }

    public void run() {
        while (true) {

            displayMenu();
            int choice = userInputHandler.getIntegerInput();

            switch (choice) {
                case 1:
                    actionHandler.handleViewInventory();;
                    break;
                case 2:
                    actionHandler.handleAddItem();
                    break;
                case 3:
                    actionHandler.handleViewCart();
                    break;
                case 4:
                    actionHandler.handleRemoveItem();
                    break;
                case 5:
                    actionHandler.handleCheckout();
                    break;
                case 6:
                    System.out.println("Thank you for using the Shopping App system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
                    break;
            }
        }

    }

    private void displayMenu () {
          System.out.println("===========================");
          System.out.println("Welcome to The Shopping Cart App");
          System.out.println("===========================");
          System.out.println("1. View Shopping Cart Inventory");
          System.out.println("2. Add Item");
          System.out.println("3. View Cart");
          System.out.println("4. Remove");
          System.out.println("5. Check Out");
          System.out.println("6. Exit");
          System.out.print("Please select an option: ");
    }

}


