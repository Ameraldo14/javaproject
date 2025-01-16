package com.example.javaproject.view;

import com.example.javaproject.model.User;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);

    public int displayMenuAndGetChoice() {
        System.out.println("\n1. Add User");
        System.out.println("2. Display Users");
        System.out.println("3. Delete User");
        System.out.println("4. Modify User");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    public User getUserInput() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Admin, Cashier, Customer): ");
        String role = scanner.nextLine();
        return new User(username, password, role);
    }

    public String getUsernameInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of Users:");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getModificationChoice() {
        System.out.println("What do you want to modify?");
        System.out.println("1. Username");
        System.out.println("2. Password");
        System.out.println("3. Role");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }
}
