package com.example.javaproject.model;

import java.util.ArrayList;
import java.util.List;

public class Cashier extends User {
    // List to store daily bills (for simulation purposes)
    private List<String> dailyBills;

    // Constructor
    public Cashier(String username, String password,String role) {
        super(username, password,role);
        this.dailyBills = new ArrayList<>();
    }

    // Method to generate a bill
    public void generateBill() {
        // Simulated bill generation
        String bill = "Bill #" + (dailyBills.size() + 1) + ": Amount $100";
        dailyBills.add(bill);
        System.out.println("Bill generated: " + bill);
    }

    // Method to view daily bills
    public void viewDailyBills() {
        System.out.println("Daily Bills:");
        if (dailyBills.isEmpty()) {
            System.out.println("No bills generated today.");
        } else {
            for (String bill : dailyBills) {
                System.out.println(bill);
            }
        }
    }

}
