package com.example.AppInventory.service;

import com.example.AppInventory.data.FileManager;
import com.example.AppInventory.model.Product;
import com.example.AppInventory.exception.ProductNotFoundException;
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

    @PostConstruct
    public void loadInventoryOnStartup() {
        System.out.println("Loading inventory from file...");
        List<Product> loadedProducts = fileManager.loadInventory();

        for (Product product : loadedProducts) {
            products.put(product.getProductId(), product);
        }

        System.out.println("Inventory loaded: " + products.size() + " products");
    }

    @PreDestroy
    public void saveInventoryOnShutdown() {
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
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        String productId = product.getProductId();

        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (products.containsKey(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " already exists");
        }

        products.put(productId, product);
        System.out.println("Product added successfully: " + product);
        saveInventory();
    }

    public Product getProduct(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean removeProduct(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        products.remove(productId);
        System.out.println("Product removed successfully: " + productId);
        saveInventory();
        return true;
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

    public boolean createBackup() {
        return fileManager.createBackup();
    }

    public String getInventoryFilePath() {
        return fileManager.getInventoryFilePath();
    }

    public boolean inventoryFileExists() {
        return fileManager.inventoryFileExists();
    }
}
