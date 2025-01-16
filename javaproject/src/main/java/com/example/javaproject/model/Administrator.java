package com.example.javaproject.model;

import java.io.*;
import java.util.List;

public class Administrator extends User {

    public Administrator(String username, String password, String role) {
        super(username, password, role);
    }

    // Method to save the list of users to a serialized file
    public void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the list of users from the serialized file
    @SuppressWarnings("unchecked")
    public List<User> loadUsers() {
        File file = new File("users.ser");
        if (!file.exists()) {
            return new java.util.ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>(); // Return an empty list in case of an error
        }
    }
}
