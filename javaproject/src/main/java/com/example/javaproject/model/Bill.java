package com.example.javaproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill extends Cashier implements Serializable {
    private static final long serialVersionUID = 1L;  // Add serialVersionUID for version control

    // Attributes
    private String billNumber;
    private double totalAmount;
    private List<Item> items;

    // Constructor
    public Bill(String billNumber, String username, String password, String role) {
        super(username, password, role);  // Inherit from Cashier
        this.billNumber = billNumber;
        this.totalAmount = 0.0;
        this.items = new ArrayList<>();
    }

    // Method to add an item to the bill
    public void addItem(Item item, int quantity) {
        if (item.getQuantity() >= quantity) {
            item.updateStock(-quantity); // Deduct stock
            items.add(new Item(item.getItemName(), item.getCategory(), item.getPrice(), quantity));
            totalAmount += item.getPrice() * quantity; // Update total amount
        } else {
            System.out.println("Insufficient stock for item: " + item.getItemName());
        }
    }

    // Method to print the bill
    public void printBill() {
        System.out.println("Bill Number: " + billNumber);
        System.out.println("Items Purchased:");
        for (Item item : items) {
            System.out.println(item.getItemName() + " (" + item.getCategory() + ") - " +
                    item.getQuantity() + " x $" + item.getPrice() + " = $" +
                    (item.getPrice() * item.getQuantity()));
        }
        System.out.println("Total Amount: $" + totalAmount);
    }

    // Getters
    public String getBillNumber() {
        return billNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setter for Bill number, if needed
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
