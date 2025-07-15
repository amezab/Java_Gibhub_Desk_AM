package com.example.AppInventory.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuController {

    @Autowired
    private com.example.AppInventory.ui.InventoryOperations inventoryOperations;

    @Autowired
    private UserInput userInput;

    public void startMenu() {
        System.out.println("\n=== Welcome to General Store Inventory System ===");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = userInput.getIntInRange("Enter your choice (1-10): ", 1, 10);

            try {
                switch (choice) {
                    case 1:
                        inventoryOperations.addNewProduct();
                        break;
                    case 2:
                        inventoryOperations.viewAllProducts();
                        break;
                    case 3:
                        inventoryOperations.addStockToProduct();
                        break;
                    case 4:
                        inventoryOperations.removeStockFromProduct();
                        break;
                    case 5:
                        inventoryOperations.updateProductPrice();
                        break;
                    case 6:
                        inventoryOperations.displayProductDetails();
                        break;
                    case 7:
                        inventoryOperations.removeProduct();
                        break;
                    case 8:
                        inventoryOperations.searchProducts();
                        break;
                    case 9:
                        inventoryOperations.fileOperations();
                        break;
                    case 10:
                        System.out.println("Thank you for using the Inventory System. Goodbye!");
                        running = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("✗ An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        userInput.closeScanner();
    }

    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add New Product");
        System.out.println("2. View All Products");
        System.out.println("3. Add Stock to Product");
        System.out.println("4. Remove Stock from Product");
        System.out.println("5. Update Product Price");
        System.out.println("6. Display Product Details");
        System.out.println("7. Remove Product");
        System.out.println("8. Search Products");
        System.out.println("9. File Operations");
        System.out.println("10. Exit");
    }
}