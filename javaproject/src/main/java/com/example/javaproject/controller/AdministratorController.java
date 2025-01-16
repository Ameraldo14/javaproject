package com.example.javaproject.controller;

import com.example.javaproject.model.Administrator;
import com.example.javaproject.model.User;

import java.util.List;
import java.util.Scanner;

public class AdministratorController {

    private Administrator admin;

    public AdministratorController() {
        this.admin = new Administrator("admin1", "adminPass", "Admin"); // Example admin credentials
    }

    // Method to add a user
    public void addUser(String username, String password, String role) {
        List<User> users = admin.loadUsers(); // Load existing users
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Error: Username already exists.");
                return;
            }
        }

        User newUser = new User(username, password, role); // Create new user
        users.add(newUser); // Add to the list
        admin.saveUsers(users); // Save updated list to file
        System.out.println("User added successfully: " + username);
    }

    // Method to delete a user
    public void deleteUser(String username) {
        List<User> users = admin.loadUsers(); // Load existing users
        User userToDelete = null;

        // Find the user by username
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete); // Remove user
            admin.saveUsers(users); // Save updated list to file
            System.out.println("User deleted successfully: " + username);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    // Method to validate a user during login
    public boolean validateUser(String username, String password) {
        List<User> users = admin.loadUsers(); // Load existing users

        // Find user and validate password
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false; // User not found or incorrect password
    }

    // Menu for admin actions
    public void displayAdminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (Admin, Cashier, Customer): ");
                    String role = scanner.nextLine();
                    addUser(username, password, role);
                    break;

                case 2:
                    System.out.print("Enter username to delete: ");
                    String deleteUsername = scanner.nextLine();
                    deleteUser(deleteUsername);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
