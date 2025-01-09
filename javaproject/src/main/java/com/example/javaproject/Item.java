package com.example.javaproject;

public class Item extends Bill {
    // Attributes
    private String itemName;
    private String category;
    private double price;
    private int quantity;

    // Constructor
    public Item(String itemName, String category, double price, int quantity) {
        super("DefaultBill"); // Inherit from Bill
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
}
