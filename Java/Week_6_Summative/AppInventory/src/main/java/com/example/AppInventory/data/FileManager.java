package com.example.AppInventory.data;

import com.example.AppInventory.model.Product;
import java.util.List;


 //Interface for managing inventory file operations

public interface FileManager {

    boolean saveInventory(List<Product> products);
    List<Product> loadInventory();
    boolean inventoryFileExists();
    String getInventoryFilePath();
    boolean createBackup();
}