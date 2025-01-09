package com.example.javaproject;

public class Supplier {
    private String supplierName;
    private String contact;

    public Supplier(String supplierName, String contact) {
        this.supplierName = supplierName;
        this.contact = contact;
    }

    public void addSupplier() {
        // Logic to add supplier
        System.out.println("Adding a supplier...");
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
