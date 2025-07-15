package com.example.AppInventory.ui;

import com.example.AppInventory.model.Product;
import com.example.AppInventory.service.InventoryManager;
import com.example.AppInventory.service.ProductService;
import com.example.AppInventory.exception.ProductNotFoundException;
import com.example.AppInventory.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class InventoryOperations {

    @Autowired
    private InventoryManager inventoryManager;

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    private Scanner scanner = new Scanner(System.in);

    public void addNewProduct() {
        System.out.println("\n=== ADD NEW PRODUCT ===");

        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine().trim();

            System.out.print("Enter Initial Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Price: $");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter Minimum Stock Level: ");
            int minimumStock = Integer.parseInt(scanner.nextLine());

            Product newProduct = new Product(productId, productName, quantity, price, minimumStock);
            inventoryManager.addProduct(newProduct);

            System.out.println("✓ Product added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter valid numbers for quantity, price, and minimum stock.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    // NEW FILE OPERATIONS
    public void fileOperations() {
        System.out.println("\n=== FILE OPERATIONS ===");
        System.out.println("1. Save Inventory to File");
        System.out.println("2. Load Inventory from File");
        System.out.println("3. Create Backup");
        System.out.println("4. Show File Info");
        System.out.print("Choose operation (1-4): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    saveInventoryToFile();
                    break;
                case 2:
                    loadInventoryFromFile();
                    break;
                case 3:
                    createInventoryBackup();
                    break;
                case 4:
                    showFileInfo();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter a valid number.");
        }
    }

    private void saveInventoryToFile() {
        try {
            boolean success = inventoryManager.saveInventory();
            if (success) {
                System.out.println("✓ Inventory saved successfully!");
            } else {
                System.out.println("✗ Failed to save inventory.");
            }
        } catch (Exception e) {
            System.out.println("✗ Error saving inventory: " + e.getMessage());
        }
    }

    private void loadInventoryFromFile() {
        try {
            System.out.print("This will replace current inventory. Continue? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                boolean success = inventoryManager.loadInventory();
                if (success) {
                    System.out.println("✓ Inventory loaded successfully!");
                } else {
                    System.out.println("✗ Failed to load inventory.");
                }
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (Exception e) {
            System.out.println("✗ Error loading inventory: " + e.getMessage());
        }
    }

    private void createInventoryBackup() {
        try {
            boolean success = inventoryManager.createBackup();
            if (success) {
                System.out.println("✓ Backup created successfully!");
            } else {
                System.out.println("✗ Failed to create backup.");
            }
        } catch (Exception e) {
            System.out.println("✗ Error creating backup: " + e.getMessage());
        }
    }

    private void showFileInfo() {
        try {
            System.out.println("\n=== FILE INFORMATION ===");
            System.out.println("File Path: " + inventoryManager.getInventoryFilePath());
            System.out.println("File Exists: " + (inventoryManager.inventoryFileExists() ? "YES" : "NO"));
            System.out.println("Current Products in Memory: " + inventoryManager.getTotalProducts());
            System.out.println("==========================");
        } catch (Exception e) {
            System.out.println("✗ Error getting file info: " + e.getMessage());
        }
    }

    // NEW SEARCH METHODS
    public void searchProducts() {
        System.out.println("\n=== SEARCH PRODUCTS ===");
        System.out.println("1. Search by Product ID");
        System.out.println("2. Search by Product Name");
        System.out.println("3. General Search (ID or Name)");
        System.out.println("4. Show Low Stock Products");
        System.out.println("5. Search by Price Range");
        System.out.print("Choose search type (1-5): ");

        try {
            int searchType = Integer.parseInt(scanner.nextLine());

            switch (searchType) {
                case 1:
                    searchByProductId();
                    break;
                case 2:
                    searchByProductName();
                    break;
                case 3:
                    generalSearch();
                    break;
                case 4:
                    showLowStockProducts();
                    break;
                case 5:
                    searchByPriceRange();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter a valid number.");
        }
    }

    private void searchByProductId() {
        System.out.print("Enter Product ID to search: ");
        String productId = scanner.nextLine().trim();

        try {
            Product product = searchService.searchByProductId(productId);
            searchService.displaySearchResult(product, "ID Search");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void searchByProductName() {
        System.out.print("Enter Product Name (or part of name) to search: ");
        String productName = scanner.nextLine().trim();

        try {
            List<Product> products = searchService.searchByProductName(productName);
            searchService.displaySearchResults(products, "Name Search");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void generalSearch() {
        System.out.print("Enter search term (Product ID or Name): ");
        String searchTerm = scanner.nextLine().trim();

        try {
            List<Product> products = searchService.generalSearch(searchTerm);
            searchService.displaySearchResults(products, "General Search");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void showLowStockProducts() {
        try {
            List<Product> lowStockProducts = searchService.searchLowStockProducts();
            searchService.displaySearchResults(lowStockProducts, "Low Stock Alert");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void searchByPriceRange() {
        try {
            System.out.print("Enter minimum price: $");
            double minPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter maximum price: $");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Product> products = searchService.searchByPriceRange(minPrice, maxPrice);
            searchService.displaySearchResults(products, "Price Range Search");
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter valid numbers for prices.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void viewAllProducts() {
        System.out.println("\n=== ALL PRODUCTS ===");

        List<Product> products = inventoryManager.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found in inventory.");
            return;
        }

        System.out.printf("%-10s %-25s %-10s %-10s %-10s %-10s%n",
                "ID", "Name", "Quantity", "Price", "Min Stock", "Low Stock");
        System.out.println("--------------------------------------------------------------------------------");

        for (Product product : products) {
            System.out.printf("%-10s %-25s %-10d $%-9.2f %-10d %-10s%n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getMinimumStock(),
                    product.isLowStock() ? "YES" : "NO");
        }

        System.out.println("\nTotal products: " + products.size());
    }

    public void addStockToProduct() {
        System.out.println("\n=== ADD STOCK ===");

        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryManager.getProduct(productId);

            System.out.print("Enter amount to add: ");
            int amount = Integer.parseInt(scanner.nextLine());

            productService.addStock(product, amount);
            System.out.println("✓ Stock added successfully!");

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter a valid number for amount.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void removeStockFromProduct() {
        System.out.println("\n=== REMOVE STOCK ===");

        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryManager.getProduct(productId);

            System.out.println("Current quantity: " + product.getQuantity());
            System.out.print("Enter amount to remove: ");
            int amount = Integer.parseInt(scanner.nextLine());

            productService.removeStock(product, amount);
            System.out.println("✓ Stock removed successfully!");

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter a valid number for amount.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void updateProductPrice() {
        System.out.println("\n=== UPDATE PRICE ===");

        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryManager.getProduct(productId);

            System.out.println("Current price: $" + String.format("%.2f", product.getPrice()));
            System.out.print("Enter new price: $");
            double newPrice = Double.parseDouble(scanner.nextLine());

            productService.updatePrice(product, newPrice);
            System.out.println("✓ Price updated successfully!");

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter a valid number for price.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void displayProductDetails() {
        System.out.println("\n=== PRODUCT DETAILS ===");

        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryManager.getProduct(productId);
            productService.displayProductInfo(product);

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void removeProduct() {
        System.out.println("\n=== REMOVE PRODUCT ===");

        try {
            System.out.print("Enter Product ID to remove: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryManager.getProduct(productId);
            System.out.println("Product to remove: " + product.getProductName());

            System.out.print("Are you sure? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                inventoryManager.removeProduct(productId);
                System.out.println("✓ Product removed successfully!");
            } else {
                System.out.println("Operation cancelled.");
            }

        } catch (ProductNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}