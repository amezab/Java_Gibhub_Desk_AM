package org.example.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.Models.Item;

public class ProductCatalog {
    private final Map<String, Item> availableItems;
    private final List<Item> inventoryList;

    public ProductCatalog() {
        this.availableItems = new HashMap<>();
        this.inventoryList = new ArrayList<>();
        this.loadInitialItems();
    }

    private void addItemToCatalog(String id, Item item){
        this.availableItems.put(id, item);
        this.inventoryList.add(item);
    }

    private void loadInitialItems(){
        // Using the .put method to add items to our catalog
        addItemToCatalog("p101", new Item("p101", "Milk",1, 4.99));
        addItemToCatalog("p102", new Item("p102", "Bread",1, 2.99));
        addItemToCatalog("p103", new Item("p103", "Eggs",1, 5.99));
        addItemToCatalog("p104", new Item("p104", "Snacks",1, 8.99));
        addItemToCatalog("p105", new Item("p105", "Bacon",1, 6.99));
        addItemToCatalog("p106", new Item("p106", "Snacks",1, 8.99));
        addItemToCatalog("p107", new Item("p107", "Snacks",1, 8.99));
        addItemToCatalog("p108", new Item("p108", "Snacks",1, 8.99));

    }

    // Go through the map to find item and return it back
    public Item findItemById(String itemId){
        return availableItems.get(itemId);
    }

    public List<Item> getInventoryList(){
        return this.inventoryList;
    }
}