package com.example.javaproject.main;

import com.example.javaproject.view.UserView;

public class Main {
    public static void main(String[] args) {
        UserView view = new UserView();
        UserController controller = new UserController(view);
        controller.handleMenu();
    }
}
