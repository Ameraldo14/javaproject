package com.example.javaproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends User implements Serializable {
    private static final String FILE_NAME = "users.dat"; // File to store serialized user data
    private List<User> users = new ArrayList<>(); // List to hold users

    public Administrator(String username, String password, String role) {
        super(username, password, role);
    }

    // Add a user and save the updated list to the file
    public void addUser(User user) {
        users.add(user); // Add the user to the list
        saveUsersToFile(); // Save the updated list to the file
        System.out.println("User added successfully: " + user.getUsername());
    }

    // Delete a user and save the updated list to the file
    public void deleteUser(String username) {
        User userToDelete = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete); // Remove the user from the list
            saveUsersToFile(); // Save the updated list to the file
            System.out.println("User deleted successfully: " + username);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    // Save the user list to a serialized file
    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users); // Write the user list to the file
            System.out.println("User data saved successfully to " + FILE_NAME);
        } catch (Exception e) {
            System.out.println("Error while saving user data: " + e.getMessage());
        }
    }

    // Load users from the file (Optional)
    @SuppressWarnings("unchecked")
    public void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject(); // Load the user list
            System.out.println("User data loaded successfully from " + FILE_NAME);
        } catch (Exception e) {
            System.out.println("Error while loading user data: " + e.getMessage());
        }
    }
}
