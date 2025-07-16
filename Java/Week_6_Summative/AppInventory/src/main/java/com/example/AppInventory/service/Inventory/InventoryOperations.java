package com.example.AppInventory.service.Inventory;

import com.example.AppInventory.model.PerishableProduct;
import com.example.AppInventory.model.Product;
import com.example.AppInventory.service.InventoryManager;
import com.example.AppInventory.service.ProductService;
import com.example.AppInventory.service.SearchService;
import com.example.AppInventory.exception.ProductNotFoundException;
import com.example.AppInventory.ui.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InventoryOperations {

    @Autowired
    private InventoryManager inventoryManager;

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserInput userInput;

    // Helper method to replace MenuController dependency
    private void pressEnterToContinue() {
        System.out.println("Press Enter to return to the main menu...");
        userInput.getString("");
    }

    // 1. Add Product
    // 1. Add Product
    public void addProduct() {
        System.out.println("\n===== Add Product =====");

        try {
            String productId = userInput.getNonEmptyString("Enter Product ID: ");

            // Check if product ID already exists
            if (inventoryManager.hasProduct(productId)) {
                System.out.println("✗ Error: Product with ID " + productId + " already exists!");
                return;
            }

            String productName = userInput.getNonEmptyString("Enter Product Name: ");
            int quantity = userInput.getPositiveInt("Enter Quantity: ");
            double price = userInput.getPositiveDouble("Enter Price: ");

            // NEW: Ask if it's a perishable product
            boolean isPerishable = userInput.getConfirmation("Is this a perishable product?");

            Product newProduct;

            if (isPerishable) {
                // Collect expiration data
                System.out.println("Enter expiration date (YYYY-MM-DD format):");
                String dateInput = userInput.getNonEmptyString("Expiration Date: ");

                int daysBeforeExp = userInput.getPositiveInt("Days before expiration warning: ");

                try {
                    LocalDate expirationDate = LocalDate.parse(dateInput);

                    // Create perishable product
                    newProduct = new PerishableProduct(productId, productName, quantity,
                            price, 5, expirationDate, daysBeforeExp);

                } catch (Exception e) {
                    System.out.println("✗ Invalid date format! Creating as regular product instead.");
                    newProduct = new Product(productId, productName, quantity, price, 5);
                }
            } else {
                // Create regular product
                newProduct = new Product(productId, productName, quantity, price, 5);
            }

            inventoryManager.addProduct(newProduct);
            System.out.println("Product added successfully!");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        pressEnterToContinue();
    }


    // 2. View Products
    public void viewProducts() {
        System.out.println("\n===== Inventory List =====");

        List<Product> products = inventoryManager.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found in inventory.");
        } else {
            // Updated header to include expiration info
            System.out.printf("%-10s | %-20s | %-10s | %-10s | %-15s%n",
                    "ID", "Name", "Quantity", "Price", "Expiration");
            System.out.println("-".repeat(75));

            for (Product product : products) {
                String expirationInfo = "";

                // Check if it's a perishable product
                if (product instanceof PerishableProduct) {
                    PerishableProduct perishable = (PerishableProduct) product;
                    expirationInfo = perishable.getExpirationDate().toString() +
                            " (" + perishable.getExpirationStatus() + ")";
                } else {
                    expirationInfo = "N/A";
                }

                System.out.printf("%-10s | %-20s | %-10d | $%-9.2f | %-15s%n",
                        product.getProductId(),
                        product.getProductName(),
                        product.getQuantity(),
                        product.getPrice(),
                        expirationInfo);
            }
            System.out.println("-".repeat(75));
        }
    }

    // 3. Search Product
    public void searchProduct() {
        System.out.println("\n===== Search Product =====");

        String searchTerm = userInput.getNonEmptyString("Enter Product ID or Name: ");

        try {
            // Try searching by ID first
            Product product = searchService.searchByProductId(searchTerm);

            if (product != null) {
                displayProductDetails(product);
            } else {
                // Search by name
                List<Product> products = searchService.searchByProductName(searchTerm);
                if (products.isEmpty()) {
                    System.out.println("Product not found!");
                } else if (products.size() == 1) {
                    displayProductDetails(products.get(0));
                } else {
                    System.out.println("Multiple products found:");
                    for (Product p : products) {
                        displayProductDetails(p);
                        System.out.println();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Product not found!");
        }

        pressEnterToContinue();
    }

    private void displayProductDetails(Product product) {
        System.out.println("Product Found:");
        System.out.println("ID: " + product.getProductId());
        System.out.println("Name: " + product.getProductName());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Price: $" + String.format("%.2f", product.getPrice()));
    }

    // 4. Update Product
    // 4. Update Product
    public void updateProduct() {
        System.out.println("\n===== Update Product =====");

        String productId = userInput.getNonEmptyString("Enter Product ID: ");

        try {
            Product product = inventoryManager.getProduct(productId);

            System.out.println("Current Details:");
            System.out.println("Name: " + product.getProductName());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Price: $" + String.format("%.2f", product.getPrice()));

            // Show expiration info if it's a perishable product
            if (product instanceof PerishableProduct) {
                PerishableProduct perishable = (PerishableProduct) product;
                System.out.println("Expiration Date: " + perishable.getExpirationDate());
                System.out.println("Days Before Expiration Warning: " + perishable.getDaysBeforeExpiration());
                System.out.println("Current Status: " + perishable.getExpirationStatus());
            }

            // Get updates for basic fields
            Integer newQuantity = userInput.getOptionalInt("Enter New Quantity (or press Enter to skip): ");
            Double newPrice = userInput.getOptionalDouble("Enter New Price (or press Enter to skip): ");

            boolean updated = false;

            // Update basic fields
            if (newQuantity != null && newQuantity >= 0) {
                product.setQuantity(newQuantity);
                updated = true;
            }

            if (newPrice != null && newPrice >= 0) {
                product.setPrice(newPrice);
                updated = true;
            }

            // Handle expiration updates for perishable products
            if (product instanceof PerishableProduct) {
                PerishableProduct perishable = (PerishableProduct) product;

                String newExpirationDate = userInput.getOptionalString("Enter New Expiration Date (YYYY-MM-DD) or press Enter to skip: ");

                if (!newExpirationDate.isEmpty()) {
                    try {
                        LocalDate newDate = LocalDate.parse(newExpirationDate);
                        perishable.setExpirationDate(newDate);
                        updated = true;
                        System.out.println("✓ Expiration date updated successfully!");
                    } catch (Exception e) {
                        System.out.println("✗ Invalid date format. Expiration date not updated.");
                    }
                }

                Integer newDaysBeforeExp = userInput.getOptionalInt("Enter New Days Before Expiration Warning (or press Enter to skip): ");

                if (newDaysBeforeExp != null && newDaysBeforeExp >= 0) {
                    perishable.setDaysBeforeExpiration(newDaysBeforeExp);
                    updated = true;
                    System.out.println("✓ Days before expiration warning updated successfully!");
                }
            }

            if (updated) {
                inventoryManager.saveInventory(); // Save changes
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("No changes made.");
            }

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Product not found!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    // 5. Delete Product
    public void deleteProduct() {
        System.out.println("\n===== Delete Product =====");

        String productId = userInput.getNonEmptyString("Enter Product ID: ");

        try {
            Product product = inventoryManager.getProduct(productId);

            System.out.println("Product to delete:");
            displayProductDetails(product);

            boolean confirm = userInput.getConfirmation("Are you sure you want to delete this product?");

            if (confirm) {
                inventoryManager.removeProduct(productId);
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Delete operation cancelled.");
            }

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Product not found!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    // 6. Save Inventory to File
    public void saveInventoryToFile() {
        System.out.println("\n===== Save Inventory =====");
        System.out.println("Saving inventory data...");

        try {
            boolean success = inventoryManager.saveInventory();
            if (success) {
                System.out.println("Inventory successfully saved to inventory.csv!");
            } else {
                System.out.println("✗ Error saving data!");
            }
        } catch (Exception e) {
            System.out.println("✗ Error saving data: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    // 7. Load Inventory from File
    public void loadInventoryFromFile() {
        System.out.println("\n===== Load Inventory =====");
        System.out.println("Loading inventory data...");

        try {
            if (!inventoryManager.inventoryFileExists()) {
                System.out.println("✗ Inventory file does not exist!");
                pressEnterToContinue();
                return;
            }

            boolean success = inventoryManager.loadInventory();
            if (success) {
                System.out.println("Inventory successfully loaded from inventory.csv!");
            } else {
                System.out.println("✗ Error loading inventory!");
            }
        } catch (Exception e) {
            System.out.println("✗ Error loading inventory: " + e.getMessage());
        }

        pressEnterToContinue();
    }
}