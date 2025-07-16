# Inventory Management System

A console-based inventory management system built in Java, demonstrating Object-Oriented Programming principles, file handling, and user input management.

## 📋 Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Key Java Concepts Demonstrated](#key-java-concepts-demonstrated)
- [How to Run](#how-to-run)
- [User Guide](#user-guide)
- [Architecture Explanation](#architecture-explanation)
- [Code Examples](#code-examples)

## 🚀 Features

- **Product Management**: Add, view, update, and delete products
- **Search Functionality**: Find products by ID or name
- **Inventory Tracking**: Monitor stock levels and low-stock alerts
- **Perishable Products**: Special handling for products with expiration dates
- **Data Persistence**: Save/load inventory data to/from CSV files
- **Input Validation**: Robust error handling and user input validation
- **Console Interface**: User-friendly menu-driven interface

## 📁 Project Structure

```
com.example.AppInventory/
├── AppInventory.java              # Main class (application entry point)
├── data/
│   ├── FileManager.java           # Interface for file operations
│   ├── CSVFileManagerImpl.java    # CSV implementation of FileManager
│   └── CSVHandler.java            # Utility class for CSV processing
├── exception/
│   └── ProductNotFoundException.java  # Custom exception class
├── model/
│   ├── Product.java               # Base product class
│   └── PerishableProduct.java     # Specialized product with expiration
├── service/
│   ├── InventoryManager.java      # Core inventory business logic
│   ├── ProductService.java        # Interface for product operations
│   ├── ProductServiceImpl.java    # Implementation of product operations
│   └── SearchService.java         # Search functionality
├── ui/
│   ├── MenuController.java        # Console menu management
│   └── UserInput.java             # Input validation utilities
└── service/Inventory/
    └── InventoryOperations.java   # UI operations coordinator
```

## 🎯 Key Java Concepts Demonstrated

### 1. Object-Oriented Programming (OOP)

#### **Encapsulation**
```java
public class Product {
    private final String productId;     // Private fields
    private final String productName;   // Data hiding
    private int quantity;
    
    public String getProductId() {      // Public getters/setters
        return productId;               // Controlled access
    }
}
```

#### **Inheritance**
```java
public class PerishableProduct extends Product {
    private LocalDate expirationDate;  // Additional attributes
    
    public PerishableProduct(String productId, String productName, ...) {
        super(productId, productName, ...);  // Call parent constructor
    }
}
```

#### **Polymorphism**
```java
@Override
public boolean isLowStock() {
    // Child class overrides parent method with specialized behavior
    return super.isLowStock() || isNearExpiration() || isExpired();
}
```

### 2. Interfaces and Abstract Concepts

#### **Interface Definition**
```java
public interface ProductService {
    void addStock(Product product, int amount);
    void removeStock(Product product, int amount);
    // Contract that implementations must follow
}
```

#### **Interface Implementation**
```java
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public void addStock(Product product, int amount) {
        // Concrete implementation of interface method
    }
}
```

### 3. Data Structures

#### **HashMap for Efficient Storage**
```java
private Map<String, Product> products = new HashMap<>();

// Why HashMap?
// - O(1) average lookup time by product ID
// - Prevents duplicate product IDs automatically
// - Efficient for inventory management operations
```

### 4. Exception Handling

#### **Custom Exceptions**
```java
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
```

#### **Try-Catch Blocks**
```java
try {
    Product product = inventoryManager.getProduct(productId);
    // Operations that might fail
} catch (ProductNotFoundException e) {
    System.out.println("✗ Product not found!");
} catch (Exception e) {
    System.out.println("✗ Error: " + e.getMessage());
}
```

### 5. File I/O Operations

#### **Saving Data**
```java
try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
    writer.println(HEADER);
    for (Product product : products) {
        writer.println(productToCSV(product));
    }
} catch (IOException e) {
    System.err.println("Error saving: " + e.getMessage());
}
```

## 🏃‍♂️ How to Run

### Prerequisites
- Java 17 or higher
- Maven (for dependency management)
- Spring Boot framework

### Running the Application
1. Clone or download the project
2. Open terminal in project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   Or compile and run:
   ```bash
   mvn clean compile
   java -cp target/classes com.example.AppInventory.AppInventory
   ```

### First Time Setup
- The application will create a `data/` folder automatically
- Inventory data is saved to `data/inventory.csv`
- Sample data will be loaded if available

## 📖 User Guide

### Main Menu Options

```
===== Inventory Manager =====
1. Add Product
2. View Products  
3. Search Product
4. Update Product
5. Delete Product
6. Save Inventory to File
7. Load Inventory from File
8. Exit
```

### 1. Adding Products
- Enter unique Product ID
- Provide product name, quantity, and price
- System validates all inputs
- Product is automatically saved

### 2. Viewing Inventory
- Displays all products in formatted table
- Shows ID, name, quantity, and price
- Empty inventory message if no products

### 3. Searching Products
- Search by Product ID (exact match)
- Search by Product Name (partial match)
- Case-insensitive name searches
- Displays detailed product information

### 4. Updating Products
- Find product by ID
- Update quantity and/or price
- Optional fields (press Enter to skip)
- Changes saved automatically

### 5. Deleting Products
- Find product by ID
- Confirmation prompt before deletion
- Product removed from inventory
- Changes saved automatically

### 6. File Operations
- **Save**: Manually save current inventory to CSV
- **Load**: Reload inventory from CSV file
- **Auto-save**: Automatic save on every change
- **Auto-load**: Automatic load on application startup

## 🏗️ Architecture Explanation

### Design Principles Applied

#### **Single Responsibility Principle (SRP)**
Each class has one specific job:
- `Product`: Data model and basic validation
- `ProductService`: Business logic operations
- `MenuController`: User interface logic
- `InventoryManager`: Data persistence and collection management
- `CSVHandler`: File processing operations

#### **Dependency Inversion Principle**
High-level classes depend on interfaces, not concrete implementations:
```java
@Autowired
private FileManager fileManager;        // Interface, not CSVFileManagerImpl
@Autowired  
private ProductService productService;  // Interface, not ProductServiceImpl
```

#### **Interface Segregation**
Specific interfaces for different responsibilities:
- `FileManager`: File operations only
- `ProductService`: Product business logic only

### Spring Framework Integration

#### **Dependency Injection**
```java
@Autowired
private InventoryManager inventoryManager;  // Spring injects automatically
```

#### **Component Scanning**
```java
@Service        // Business logic components
@Component      // General Spring components
@SpringBootApplication  // Main application class
```

#### **Lifecycle Management**
```java
@PostConstruct  // Run after Spring creates the object
@PreDestroy     // Run before Spring destroys the object
```

## 💻 Code Examples

### Creating a Product
```java
// Standard product
Product laptop = new Product("TECH001", "Gaming Laptop", 5, 1299.99, 2);

// Perishable product
PerishableProduct milk = new PerishableProduct(
    "FOOD001", "Organic Milk", 20, 4.99, 5,
    LocalDate.of(2025, 7, 25), 3
);
```

### Polymorphism in Action
```java
List<Product> products = Arrays.asList(laptop, milk);

for (Product product : products) {
    if (product.isLowStock()) {  // Different behavior for each type
        System.out.println(product.getProductName() + " needs restocking!");
    }
}
```

### Search Operations
```java
// Search by ID
Product found = searchService.searchByProductId("TECH001");

// Search by name (partial match)
List<Product> results = searchService.searchByProductName("laptop");
```

### File Persistence
```java
// Save inventory
List<Product> allProducts = inventoryManager.getAllProducts();
boolean success = fileManager.saveInventory(allProducts);

// Load inventory  
List<Product> loadedProducts = fileManager.loadInventory();
```

## 🔧 Error Handling Strategy

### Input Validation
- **Type checking**: Ensures integers and doubles are valid
- **Range validation**: Positive numbers only for quantities/prices
- **Required fields**: Non-empty strings for names and IDs
- **Business rules**: No duplicate product IDs

### Exception Management
- **Custom exceptions**: `ProductNotFoundException` for missing products
- **Graceful degradation**: Application continues after errors
- **User-friendly messages**: Clear error descriptions with ✗ symbols
- **Resource cleanup**: Try-with-resources for file operations

## 📚 Study Notes

### Key Concepts to Remember

1. **HashMap vs ArrayList**: HashMap chosen for O(1) lookup by product ID
2. **Inheritance vs Composition**: PerishableProduct extends Product (IS-A relationship)
3. **Interface vs Abstract Class**: Interfaces define contracts, no implementation
4. **Method Overriding**: Child class provides specific implementation
5. **Exception Hierarchy**: RuntimeException vs checked exceptions
6. **File I/O**: Try-with-resources ensures proper file closure
7. **Spring DI**: Framework manages object creation and dependencies

### Common Interview Questions

**Q: Why use HashMap for products?**
A: HashMap provides O(1) average lookup time by product ID, which is efficient for inventory operations. ArrayList would be O(n) for searches.

**Q: Explain polymorphism in your code.**
A: PerishableProduct overrides `isLowStock()` to include expiration logic. At runtime, the correct method is called based on the actual object type.

**Q: Why separate interfaces from implementations?**
A: Follows dependency inversion principle. Makes code testable, flexible, and allows multiple implementations without changing client code.

**Q: How does Spring dependency injection work?**
A: Spring creates objects and injects dependencies automatically using `@Autowired`. This reduces coupling and improves testability.

---

## 🎓 Learning Outcomes

After studying this project, you should understand:
- ✅ Object-oriented programming principles
- ✅ Interface design and implementation
- ✅ Exception handling strategies
- ✅ File I/O operations
- ✅ Data structure selection (HashMap)
- ✅ Spring Framework basics
- ✅ Software architecture principles
- ✅ Input validation techniques

This project demonstrates **enterprise-level Java development** while maintaining clarity for learning purposes.