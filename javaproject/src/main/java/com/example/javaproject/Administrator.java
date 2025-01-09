package com.example.javaproject;

public class Administrator extends User{
    public Administrator(String username, String password,String role) {
        super(username, password,role);
    }

    public void addUser() {
        // Logic to add a user
        System.out.println("Adding a new user...");
    }

    public void deleteUser() {
        // Logic to delete a user
        System.out.println("Deleting a user...");
    }

}
