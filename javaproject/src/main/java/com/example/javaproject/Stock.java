package com.example.javaproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable {
    private static final String FILE_NAME = "stock.dat"; // File to store serialized stock data
    private List<Item> items = new ArrayList<>(); // List to hold stock items

    // Alert for low stock items
    public void lowStockAlert() {
        System.out.println("Checking for low stock...");
        for (Item item : items) {
            if (item.getQuantity() < 5) { // Example threshold for low stock
                System.out.println("Low stock alert for item: " + item.getItemName() + " (Quantity: " + item.getQuantity() + ")");
            }
        }
    }

    // Add a new item to the stock
    public void addNewItem(Item newItem) {
        items.add(newItem); // Add the item to the list
        saveStockToFile(); // Save the updated stock list to the file
        System.out.println("Item added successfully: " + newItem.getItemName());
    }

    // Save the stock list to a serialized file
    private void saveStockToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(items); // Write the items list to the file
            System.out.println("Stock data saved successfully to " + FILE_NAME);
        } catch (Exception e) {
            System.out.println("Error while saving stock data: " + e.getMessage());
        }
    }

    // Load stock items from the file (Optional)
    @SuppressWarnings("unchecked")
    public void loadStockFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            items = (List<Item>) ois.readObject(); // Load the items list
            System.out.println("Stock data loaded successfully from " + FILE_NAME);
        } catch (Exception e) {
            System.out.println("Error while loading stock data: " + e.getMessage());
        }
    }
}

