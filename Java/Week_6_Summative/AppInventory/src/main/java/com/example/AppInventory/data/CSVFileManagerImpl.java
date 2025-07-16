package com.example.AppInventory.data;

import com.example.AppInventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CSVFileManagerImpl implements FileManager {

    @Autowired
    private CSVHandler csvHandler;

    @Override
    public boolean saveInventory(List<Product> products) {
        return csvHandler.saveInventoryToCSV(products);
    }

    @Override
    public List<Product> loadInventory() {
        return csvHandler.loadInventoryFromCSV();
    }

    @Override
    public boolean inventoryFileExists() {
        return csvHandler.csvFileExists();
    }

    @Override
    public String getInventoryFilePath() {
        return csvHandler.getCSVFilePath();
    }

    @Override
    public boolean createBackup() {
        return csvHandler.createCSVBackup();
    }
}