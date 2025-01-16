package com.example.javaproject.model;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Manager extends User {
    private List<Item> items; // List to store the items (stock)

    public Manager(String username, String password, String role) {
        super(username, password, role);
        this.items = new ArrayList<>();
    }

    // Method to add a new item to stock
    public void addItemToStock(String itemName, String category, double price, int quantity) {
        Item newItem = new Item(itemName, category, price, quantity);
        items.add(newItem);
        System.out.println("Added new item: " + itemName);
    }

    // Method to update an item's stock quantity
    public void updateItemQuantity(String itemName, int newQuantity) {
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                item.setQuantity(newQuantity);
                System.out.println("Updated " + itemName + " stock to " + newQuantity);
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }

    // Method to view current stock
    public void viewStock() {
        System.out.println("Current stock:");
        if (items.isEmpty()) {
            System.out.println("No items in stock.");
        } else {
            for (Item item : items) {
                System.out.println(item.getItemName() + " (" + item.getCategory() + ") - " +
                        item.getQuantity() + " units at $" + item.getPrice());
            }
        }
    }

    // Method to remove an item from stock
    public void removeItemFromStock(String itemName) {
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                items.remove(item);
                System.out.println("Removed item: " + itemName);
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }

    // Method to check stock of a specific item
    public void checkItemStock(String itemName) {
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                System.out.println("Stock for " + itemName + ": " + item.getQuantity() + " units");
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }

    // Method to manage stock (interactive menu for stock management)
    public void manageStock() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display stock management menu
            System.out.println("\n-- Stock Management --");
            System.out.println("1. Add Item to Stock");
            System.out.println("2. Update Item Quantity");
            System.out.println("3. View Current Stock");
            System.out.println("4. Remove Item from Stock");
            System.out.println("5. Check Item Stock");
            System.out.println("6. Exit");

            // Get user's choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add item to stock
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter item category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter item quantity: ");
                    int quantity = scanner.nextInt();
                    addItemToStock(itemName, category, price, quantity);
                    break;
                case 2:
                    // Update item quantity
                    System.out.print("Enter item name: ");
                    String itemToUpdate = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    updateItemQuantity(itemToUpdate, newQuantity);
                    break;
                case 3:
                    // View current stock
                    viewStock();
                    break;
                case 4:
                    // Remove item from stock
                    System.out.print("Enter item name to remove: ");
                    String itemToRemove = scanner.nextLine();
                    removeItemFromStock(itemToRemove);
                    break;
                case 5:
                    // Check stock of specific item
                    System.out.print("Enter item name to check: ");
                    String itemToCheck = scanner.nextLine();
                    checkItemStock(itemToCheck);
                    break;
                case 6:
                    // Exit
                    running = false;
                    System.out.println("Exiting stock management...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
