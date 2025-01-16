package com.example.javaproject.main;

import com.example.javaproject.model.ui.ElectronicsStoreUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElectronicsStoreApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ElectronicsStoreUI ui = new ElectronicsStoreUI();
        Scene loginScene = ui.createLoginScene(primaryStage);

        primaryStage.setTitle("Electronics Store Management System");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
