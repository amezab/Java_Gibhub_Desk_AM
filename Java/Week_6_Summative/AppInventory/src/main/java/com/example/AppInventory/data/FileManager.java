package com.example.AppInventory.data;

import com.example.AppInventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileManager {

    @Autowired
    private CSVHandler csvHandler;

    public boolean saveInventory(List<Product> products) {
        return csvHandler.saveInventoryToCSV(products);
    }

    public List<Product> loadInventory() {
        return csvHandler.loadInventoryFromCSV();
    }

    public boolean inventoryFileExists() {
        return csvHandler.csvFileExists();
    }

    public String getInventoryFilePath() {
        return csvHandler.getCSVFilePath();
    }

    public boolean createBackup() {
        return csvHandler.createCSVBackup();
    }
}