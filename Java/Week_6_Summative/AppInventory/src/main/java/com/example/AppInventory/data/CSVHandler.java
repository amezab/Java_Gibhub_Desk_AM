package com.example.AppInventory.data;

import com.example.AppInventory.model.Product;
import com.example.AppInventory.model.PerishableProduct;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHandler {

    private static final String DATA_FOLDER = "data";
    private static final String CSV_FILE = DATA_FOLDER + File.separator + "inventory.csv";
    private static final String SEPARATOR = ",";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String HEADER = "Type,ID,Name,Quantity,Price,MinStock,ExpirationDate,DaysBeforeExp";

    public CSVHandler() {
        createDataFolderIfNotExists();
    }

    private void createDataFolderIfNotExists() {
        File dataDir = new File(DATA_FOLDER);
        if (!dataDir.exists()) {
            if (dataDir.mkdirs()) {
                System.out.println("✓ Created data folder: " + dataDir.getAbsolutePath());
            } else {
                System.err.println("✗ Failed to create data folder: " + dataDir.getAbsolutePath());
            }
        }
    }

    public boolean saveInventoryToCSV(List<Product> products) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            writer.println(HEADER);

            for (Product product : products) {
                String csvLine = productToCSV(product);
                writer.println(csvLine);
            }

            System.out.println("✓ Inventory saved successfully to " + CSV_FILE);
            return true;

        } catch (IOException e) {
            System.err.println("✗ Error saving inventory to CSV: " + e.getMessage());
            return false;
        }
    }

    public List<Product> loadInventoryFromCSV() {
        List<Product> products = new ArrayList<>();

        File file = new File(CSV_FILE);
        if (!file.exists()) {
            System.out.println("No CSV inventory file found. Starting with empty inventory.");
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            int lineNumber = 0;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Product product = csvToProduct(line);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (Exception e) {
                    System.err.println("✗ Error parsing CSV line " + lineNumber + ": " + e.getMessage());
                    System.err.println("  Line content: " + line);
                }
            }

            System.out.println("✓ Loaded " + products.size() + " products from " + CSV_FILE);
            return products;

        } catch (IOException e) {
            System.err.println("✗ Error loading inventory from CSV: " + e.getMessage());
            return products;
        }
    }

    private String productToCSV(Product product) {
        StringBuilder sb = new StringBuilder();

        if (product instanceof PerishableProduct) {
            PerishableProduct pp = (PerishableProduct) product;
            sb.append("PERISHABLE").append(SEPARATOR);
            sb.append(escapeCSVField(pp.getProductId())).append(SEPARATOR);
            sb.append(escapeCSVField(pp.getProductName())).append(SEPARATOR);
            sb.append(pp.getQuantity()).append(SEPARATOR);
            sb.append(pp.getPrice()).append(SEPARATOR);
            sb.append(pp.getMinimumStock()).append(SEPARATOR);
            sb.append(pp.getExpirationDate().format(DATE_FORMAT)).append(SEPARATOR);
            sb.append(pp.getDaysBeforeExpiration());
        } else {
            sb.append("STANDARD").append(SEPARATOR);
            sb.append(escapeCSVField(product.getProductId())).append(SEPARATOR);
            sb.append(escapeCSVField(product.getProductName())).append(SEPARATOR);
            sb.append(product.getQuantity()).append(SEPARATOR);
            sb.append(product.getPrice()).append(SEPARATOR);
            sb.append(product.getMinimumStock()).append(SEPARATOR);
            sb.append(SEPARATOR);
            sb.append("");
        }

        return sb.toString();
    }

    private Product csvToProduct(String csvLine) {
        String[] fields = parseCSVLine(csvLine);

        if (fields.length < 6) {
            throw new IllegalArgumentException("Invalid CSV format - not enough fields");
        }

        String type = fields[0].trim();
        String productId = fields[1].trim();
        String productName = fields[2].trim();
        int quantity = Integer.parseInt(fields[3].trim());
        double price = Double.parseDouble(fields[4].trim());
        int minimumStock = Integer.parseInt(fields[5].trim());

        if (type.equals("PERISHABLE")) {
            if (fields.length < 8) {
                throw new IllegalArgumentException("Invalid perishable product CSV format");
            }

            String expirationDateStr = fields[6].trim();
            String daysBeforeExpStr = fields[7].trim();

            if (expirationDateStr.isEmpty() || daysBeforeExpStr.isEmpty()) {
                throw new IllegalArgumentException("Missing expiration data for perishable product");
            }

            LocalDate expirationDate = LocalDate.parse(expirationDateStr, DATE_FORMAT);
            int daysBeforeExpiration = Integer.parseInt(daysBeforeExpStr);

            return new PerishableProduct(productId, productName, quantity, price,
                    minimumStock, expirationDate, daysBeforeExpiration);
        } else {
            return new Product(productId, productName, quantity, price, minimumStock);
        }
    }

    private String escapeCSVField(String field) {
        if (field == null) {
            return "";
        }

        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }

        return field;
    }

    private String[] parseCSVLine(String csvLine) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < csvLine.length(); i++) {
            char c = csvLine.charAt(i);

            if (c == '"') {
                if (inQuotes && i + 1 < csvLine.length() && csvLine.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString());
        return fields.toArray(new String[0]);
    }

    public boolean csvFileExists() {
        return new File(CSV_FILE).exists();
    }

    public String getCSVFilePath() {
        return new File(CSV_FILE).getAbsolutePath();
    }

    public boolean createCSVBackup() {
        if (!csvFileExists()) {
            System.out.println("No CSV file to backup.");
            return false;
        }

        String backupFileName = DATA_FOLDER + File.separator + "inventory_backup_" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                ".csv";

        try {
            File originalFile = new File(CSV_FILE);
            File backupFile = new File(backupFileName);

            try (FileInputStream fis = new FileInputStream(originalFile);
                 FileOutputStream fos = new FileOutputStream(backupFile)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            }

            System.out.println("✓ CSV backup created: " + backupFileName);
            return true;

        } catch (IOException e) {
            System.err.println("✗ Error creating CSV backup: " + e.getMessage());
            return false;
        }
    }
}