package com.example.javaproject.ui;

import com.example.javaproject.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ElectronicsStoreUI {

    private Administrator administrator;
    private Manager manager;
    private Cashier cashier;
    private Stock stock;

    public ElectronicsStoreUI() {
        initializeModules();
    }

    private void initializeModules() {
        stock = new Stock();
        administrator = new Administrator("admin", "admin123", "Administrator");
        manager = new Manager("manager", "manager123", "Manager");
        cashier = new Cashier("cashier", "cashier123", "Cashier");
    }

    public Scene createLoginScene(Stage primaryStage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Welcome to Electronics Store");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane formLayout = new GridPane();
        formLayout.setHgap(15);
        formLayout.setVgap(10);
        formLayout.setAlignment(Pos.CENTER);

        Label roleLabel = new Label("Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Cashier", "Manager", "Administrator");
        roleComboBox.setPrefWidth(200);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        formLayout.add(roleLabel, 0, 0);
        formLayout.add(roleComboBox, 1, 0);
        formLayout.add(usernameLabel, 0, 1);
        formLayout.add(usernameField, 1, 1);
        formLayout.add(passwordLabel, 0, 2);
        formLayout.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(100);
        loginButton.setOnAction(e -> handleLogin(primaryStage, roleComboBox, usernameField, passwordField));

        layout.getChildren().addAll(titleLabel, formLayout, loginButton);

        return new Scene(layout, 600, 400);
    }

    private void handleLogin(Stage primaryStage, ComboBox<String> roleComboBox, TextField usernameField, PasswordField passwordField) {
        String role = roleComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (role == null || username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "All fields are required.");
            return;
        }

        switch (role) {
            case "Cashier":
                if (authenticate(cashier, username, password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, Cashier!");
                    loadCashierDashboard(primaryStage);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid credentials.");
                }
                break;

            case "Manager":
                if (authenticate(manager, username, password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, Manager!");
                    loadManagerDashboard(primaryStage);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid credentials.");
                }
                break;

            case "Administrator":
                if (authenticate(administrator, username, password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, Administrator!");
                    loadAdministratorDashboard(primaryStage);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid credentials.");
                }
                break;

            default:
                showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid role selected.");
        }
    }

    private boolean authenticate(User user, String username, String password) {
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadCashierDashboard(Stage primaryStage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Cashier Dashboard");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button createBill = new Button("Create Bill");
        createBill.setPrefWidth(200);
        createBill.setOnAction(e -> handleCreateBill());

        Button viewBills = new Button("View Today's Bills");
        viewBills.setPrefWidth(200);
        viewBills.setOnAction(e -> handleViewBills());

        Button logout = new Button("Logout");
        logout.setPrefWidth(200);
        logout.setOnAction(e -> primaryStage.setScene(createLoginScene(primaryStage)));

        layout.getChildren().addAll(title, createBill, viewBills, logout);
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void handleCreateBill() {
        try {
            String billNumber = UUID.randomUUID().toString().substring(0, 8);
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            File billFile = new File("Bill_" + billNumber + "_" + date + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(billFile))) {
                writer.write("Bill Number: " + billNumber);
                writer.newLine();
                writer.write("Date: " + date);
                writer.newLine();
                writer.write("Items: Sample Item");
                writer.newLine();
                writer.write("Total Amount: $100");
            }
            showAlert(Alert.AlertType.INFORMATION, "Bill Created", "Bill has been created: " + billFile.getName());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to create bill: " + e.getMessage());
        }
    }

    private void handleViewBills() {
        File currentDirectory = new File(".");
        File[] billFiles = currentDirectory.listFiles((dir, name) -> name.startsWith("Bill_"));

        if (billFiles == null || billFiles.length == 0) {
            showAlert(Alert.AlertType.INFORMATION, "View Bills", "No bills found for today.");
            return;
        }

        StringBuilder billsList = new StringBuilder("Today's Bills:\n");
        for (File bill : billFiles) {
            billsList.append(bill.getName()).append("\n");
        }
        showAlert(Alert.AlertType.INFORMATION, "Today's Bills", billsList.toString());
    }

    private void loadManagerDashboard(Stage primaryStage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Manager Dashboard");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button manageStock = new Button("Manage Stock");
        manageStock.setPrefWidth(200);
        manageStock.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "Manage Stock", "Stock management not implemented yet."));

        Button viewStatistics = new Button("View Sales Statistics");
        viewStatistics.setPrefWidth(200);
        viewStatistics.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "View Statistics", "Sales statistics not implemented yet."));

        Button logout = new Button("Logout");
        logout.setPrefWidth(200);
        logout.setOnAction(e -> primaryStage.setScene(createLoginScene(primaryStage)));

        layout.getChildren().addAll(title, manageStock, viewStatistics, logout);
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void loadAdministratorDashboard(Stage primaryStage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Administrator Dashboard");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button manageEmployees = new Button("Manage Employees");
        manageEmployees.setPrefWidth(200);
        manageEmployees.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "Manage Employees", "Employee management not implemented yet."));

        Button logout = new Button("Logout");
        logout.setPrefWidth(200);
        logout.setOnAction(e -> primaryStage.setScene(createLoginScene(primaryStage)));

        layout.getChildren().addAll(title, manageEmployees, logout);
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }
}
