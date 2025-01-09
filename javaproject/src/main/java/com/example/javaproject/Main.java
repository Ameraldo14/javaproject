package com.example.javaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(); // Initialize the user list
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Display Users");
            System.out.println("3. Delete User");
            System.out.println("4. Modify User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addUser(users, scanner);
                    break;
                case 2:
                    displayUsers(users);
                    break;
                case 3:
                    deleteUser(users, scanner);
                    break;
                case 4:
                    modifyUser(users, scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add a user
    private static void addUser(List<User> users, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Error: Username already exists.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (Admin, Cashier, Customer): ");
        String role = scanner.nextLine();

        // Validate role
        if (!isValidRole(role)) {
            System.out.println("Error: Invalid role. Please enter a valid role (Admin, Cashier, Customer).");
            return;
        }

        users.add(new User(username, password, role)); // Add new user
        System.out.println("User added successfully: " + username + " (Role: " + role + ")");
    }

    // Method to display users
    private static void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of Users:");
            for (User user : users) {
                System.out.println("- Username: " + user.getUsername() + ", Role: " + user.getRole());
            }
        }
    }

    // Method to delete a user
    private static void deleteUser(List<User> users, Scanner scanner) {
        System.out.print("Enter the username of the user to delete: ");
        String username = scanner.nextLine();

        User userToDelete = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete);
            System.out.println("User deleted successfully: " + username);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    // Method to modify a user
    private static void modifyUser(List<User> users, Scanner scanner) {
        System.out.print("Enter the username of the user to modify: ");
        String username = scanner.nextLine();

        User userToModify = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToModify = user;
                break;
            }
        }

        if (userToModify != null) {
            System.out.println("What do you want to modify?");
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. Role");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();

                    // Check if the new username already exists
                    for (User user : users) {
                        if (user.getUsername().equals(newUsername)) {
                            System.out.println("Error: Username already exists.");
                            return;
                        }
                    }
                    userToModify.setUsername(newUsername);
                    System.out.println("Username updated successfully.");
                    break;

                case 2:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    userToModify.setPassword(newPassword);
                    System.out.println("Password updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter new role (Admin, Cashier, Customer): ");
                    String newRole = scanner.nextLine();

                    // Validate new role
                    if (!isValidRole(newRole)) {
                        System.out.println("Error: Invalid role. Please enter a valid role (Admin, Cashier, Customer).");
                        return;
                    }
                    userToModify.setRole(newRole);
                    System.out.println("Role updated successfully.");
                    break;

                default:
                    System.out.println("Invalid option. No changes were made.");
            }
        } else {
            System.out.println("Error: User not found.");
        }
    }

    // Method to validate the role
    private static boolean isValidRole(String role) {
        return role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Cashier") || role.equalsIgnoreCase("Customer");
    }
}
