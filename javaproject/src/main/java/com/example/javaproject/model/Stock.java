package com.example.javaproject.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable {
    private List<Item> items;

    // Constructor
    public Stock() {
        this.items = new ArrayList<>();
    }

    // Method to add a new item to stock
    public void addNewItem(String itemName, String category, double price, int quantity) {
        Item newItem = new Item(itemName, category, price, quantity);
        items.add(newItem);
        System.out.println("Item added successfully: " + newItem.getItemName());
    }

    // Method to display all items in stock
    public void displayStock() {
        if (items.isEmpty()) {
            System.out.println("No items in stock.");
            return;
        }

        System.out.println("Current Stock:");
        for (Item item : items) {
            System.out.println("- " + item.getItemName() + " | Category: " + item.getCategory() +
                    " | Price: $" + item.getPrice() + " | Quantity: " + item.getQuantity());
        }
    }

    // Method to update the stock of an existing item
    public void updateStock(String itemName, int quantityChange) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                item.updateStock(quantityChange);
                System.out.println("Stock updated for item: " + item.getItemName() +
                        " | New Quantity: " + item.getQuantity());
                return;
            }
        }
        System.out.println("Item not found in stock: " + itemName);
    }

    // Method to remove an item from stock
    public void removeItem(String itemName) {
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            items.remove(itemToRemove);
            System.out.println("Item removed successfully: " + itemToRemove.getItemName());
        } else {
            System.out.println("Item not found in stock: " + itemName);
        }
    }

    // Method to check for low stock items
    public void lowStockAlert(int threshold) {
        System.out.println("Low Stock Items (Threshold: " + threshold + "):");
        boolean hasLowStock = false;

        for (Item item : items) {
            if (item.getQuantity() <= threshold) {
                System.out.println("- " + item.getItemName() + " | Quantity: " + item.getQuantity());
                hasLowStock = true;
            }
        }

        if (!hasLowStock) {
            System.out.println("All items have sufficient stock.");
        }
    }

    // Method to serialize stock data to a file
    public void saveStockToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Stock saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving stock to file: " + e.getMessage());
        }
    }

    // Method to load stock data from a file
    public static Stock loadStockFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Stock) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading stock from file: " + e.getMessage());
            return new Stock();
        }
    }
}
