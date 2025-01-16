package com.example.javaproject.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String supplierName;
    private String contact;
    private static List<Supplier> supplierList = new ArrayList<>();

    // Constructor
    public Supplier(String supplierName, String contact) {
        this.supplierName = supplierName;
        this.contact = contact;
    }

    // Method to add a supplier
    public void addSupplier() {
        supplierList.add(this);
        System.out.println("Supplier added successfully: " + this.supplierName);
    }

    // Method to remove a supplier
    public static boolean removeSupplier(String supplierName) {
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierName().equalsIgnoreCase(supplierName)) {
                supplierList.remove(supplier);
                System.out.println("Supplier removed successfully: " + supplierName);
                return true;
            }
        }
        System.out.println("Supplier not found: " + supplierName);
        return false;
    }

    // Method to update supplier details
    public void updateSupplier(String newName, String newContact) {
        this.supplierName = newName;
        this.contact = newContact;
        System.out.println("Supplier details updated successfully.");
    }

    // Method to display all suppliers
    public static void displayAllSuppliers() {
        if (supplierList.isEmpty()) {
            System.out.println("No suppliers available.");
        } else {
            System.out.println("List of Suppliers:");
            for (Supplier supplier : supplierList) {
                System.out.println("Name: " + supplier.getSupplierName() + ", Contact: " + supplier.getContact());
            }
        }
    }

    // Getters and setters
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
