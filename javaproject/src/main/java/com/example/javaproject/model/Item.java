package com.example.javaproject.model;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;  // Add serialVersionUID for version control

    // Attributes
    private String itemName;
    private String category;
    private double price;
    private int quantity;

    // Constructor
    public Item(String itemName, String category, double price, int quantity) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Method to update stock
    public void updateStock(int change) {
        this.quantity += change;
        if (this.quantity < 0) {
            this.quantity = 0; // Ensure stock doesn't go negative
        }
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity (if needed)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName + ", Category: " + category + ", Price: " + price + ", Quantity: " + quantity;
    }
}