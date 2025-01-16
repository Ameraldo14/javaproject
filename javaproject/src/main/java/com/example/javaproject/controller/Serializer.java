package com.example.javaproject.controller;
// Importing the necessary model classes
import com.example.javaproject.model.Bill;
import com.example.javaproject.model.Item;
import com.example.javaproject.model.User;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Serializer {

    public static void serializeUser(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
            out.writeObject(user);  // Serialize the user object
            System.out.println("User serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error serializing user: " + e.getMessage());
        }
    }

    public static void serializeItem(Item item) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("item.ser"))) {
            out.writeObject(item);  // Serialize the item object
            System.out.println("Item serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error serializing item: " + e.getMessage());
        }
    }

    public static void serializeBill(Bill bill) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bill.ser"))) {
            out.writeObject(bill);  // Serialize the bill object
            System.out.println("Bill serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error serializing bill: " + e.getMessage());
        }
    }
}
