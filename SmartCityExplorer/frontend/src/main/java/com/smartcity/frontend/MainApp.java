package com.smartcity.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        LoginView.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
