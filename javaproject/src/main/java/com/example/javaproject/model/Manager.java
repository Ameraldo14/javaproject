package com.example.javaproject.model;

public class Manager extends User {
    public Manager(String username, String password,String role) {
        super(username, password,role);
    }

    public void manageStock() {
        // Logic to manage stock
        System.out.println("Managing stock...");
    }

    public void viewPerformance() {
        // Logic to view performance
        System.out.println("Viewing performance...");
    }
}
