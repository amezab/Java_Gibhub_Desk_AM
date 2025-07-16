package com.example.AppInventory.ui;

import com.example.AppInventory.service.Inventory.InventoryOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuController {

    @Autowired
    private InventoryOperations inventoryOperations;

    @Autowired
    private UserInput userInput;

    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = userInput.getIntInRange("Enter your choice: ", 1, 8);

            try {
                switch (choice) {
                    case 1:
                        inventoryOperations.addProduct();
                        break;
                    case 2:
                        inventoryOperations.viewProducts();
                        break;
                    case 3:
                        inventoryOperations.searchProduct();
                        break;
                    case 4:
                        inventoryOperations.updateProduct();
                        break;
                    case 5:
                        inventoryOperations.deleteProduct();
                        break;
                    case 6:
                        inventoryOperations.saveInventoryToFile();
                        break;
                    case 7:
                        inventoryOperations.loadInventoryFromFile();
                        break;
                    case 8:
                        if (confirmExit()) {
                            System.out.println("Thank you for using Inventory Manager. Goodbye!");
                            running = false;
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("✗ An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
                pressEnterToContinue();
            }
        }
        userInput.closeScanner();
    }

    private void displayMainMenu() {
        System.out.println("\n===== Inventory Manager =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Save Inventory to File");
        System.out.println("7. Load Inventory from File");
        System.out.println("8. Exit");
    }

    private boolean confirmExit() {
        return userInput.getConfirmation("Are you sure you want to exit?");
    }

    public void pressEnterToContinue() {
        System.out.println("Press Enter to return to the main menu...");
        userInput.getString("");
    }
}