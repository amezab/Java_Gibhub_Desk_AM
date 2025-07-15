package com.example.AppInventory.service;

import com.example.AppInventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private InventoryManager inventoryManager;

    public Product searchByProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }

        try {
            return inventoryManager.getProduct(productId.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> searchByProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        List<Product> allProducts = inventoryManager.getAllProducts();
        List<Product> matchingProducts = new ArrayList<>();

        String searchTerm = productName.trim().toLowerCase();

        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().contains(searchTerm)) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public List<Product> searchLowStockProducts() {
        List<Product> allProducts = inventoryManager.getAllProducts();
        List<Product> lowStockProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.isLowStock()) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Prices cannot be negative");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price");
        }

        List<Product> allProducts = inventoryManager.getAllProducts();
        List<Product> productsInRange = new ArrayList<>();

        for (Product product : allProducts) {
            double price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                productsInRange.add(product);
            }
        }

        return productsInRange;
    }

    public List<Product> generalSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }

        List<Product> results = new ArrayList<>();

        Product productById = searchByProductId(searchTerm);
        if (productById != null) {
            results.add(productById);
            return results;
        }

        results = searchByProductName(searchTerm);
        return results;
    }

    public void displaySearchResults(List<Product> products, String searchType) {
        if (products == null || products.isEmpty()) {
            System.out.println("No products found matching your search criteria.");
            return;
        }

        System.out.println("\n=== " + searchType.toUpperCase() + " RESULTS ===");
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

        System.out.println("\nFound " + products.size() + " product(s).");
    }

    public void displaySearchResult(Product product, String searchType) {
        if (product == null) {
            System.out.println("No product found matching your search criteria.");
            return;
        }

        List<Product> singleProductList = new ArrayList<>();
        singleProductList.add(product);
        displaySearchResults(singleProductList, searchType);
    }
}