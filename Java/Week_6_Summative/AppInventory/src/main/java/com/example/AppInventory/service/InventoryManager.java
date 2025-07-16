package com.example.AppInventory.service;

import com.example.AppInventory.model.Product;
import com.example.AppInventory.exception.ProductNotFoundException;
import com.example.AppInventory.data.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryManager {
    private Map<String, Product> products = new HashMap<>();

    @Autowired
    private FileManager fileManager;

    // Runs when Spring creates this object
    @PostConstruct
    public void loadInventoryOnStartup() { // Automatically loads data from file when app starts
        System.out.println("Loading inventory from file...");
        List<Product> loadedProducts = fileManager.loadInventory();

        for (Product product : loadedProducts) {
            products.put(product.getProductId(), product);
        }

        System.out.println("Inventory loaded: " + products.size() + " products");
    }

    @PreDestroy // Runs when Spring shuts down this object
    public void saveInventoryOnShutdown() { // Automatically saves data to file when app closes
        System.out.println("Saving inventory to file...");
        saveInventory();
    }

    public boolean saveInventory() {
        List<Product> productList = new ArrayList<>(products.values());
        return fileManager.saveInventory(productList);
    }

    public boolean loadInventory() {
        try {
            List<Product> loadedProducts = fileManager.loadInventory();

            products.clear();
            for (Product product : loadedProducts) {
                products.put(product.getProductId(), product);
            }

            System.out.println("✓ Inventory reloaded: " + products.size() + " products");
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error reloading inventory: " + e.getMessage());
            return false;
        }
    }

    public void addProduct(Product product) {
        // 1. Validate product isn't null
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        String productId = product.getProductId();
        // 2. Check ID isn't empty
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        // 3. Check ID doesn't already exist
        if (products.containsKey(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " already exists");
        }
        // 4. Add to HashMap
        products.put(productId, product);
        System.out.println("Product added successfully: " + product);
        // 5. Auto-save to file
        saveInventory();
    }

    public Product getProduct(String productId) {
        // 1. Validate ID isn't empty
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        // 2. Check if exists in HashMap
        if (!products.containsKey(productId)) {
            //3. If not found throw ProductNotFoundException
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        //4. Return the product
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void removeProduct(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        products.remove(productId);
        System.out.println("Product removed successfully: " + productId);
        saveInventory();
    }

    public boolean hasProduct(String productId) {
        return productId != null && !productId.trim().isEmpty() && products.containsKey(productId);
    }

    public int getTotalProducts() {
        return products.size();
    }

    public boolean updateProductQuantity(String productId, int newQuantity) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        Product product = products.get(productId);
        product.setQuantity(newQuantity);

        System.out.println("Product quantity updated successfully: " + product);
        saveInventory();
        return true;
    }

    public boolean inventoryFileExists() {
        return fileManager.inventoryFileExists();
    }
}
