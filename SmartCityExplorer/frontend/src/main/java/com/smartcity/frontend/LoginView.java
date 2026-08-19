package com.smartcity.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    public static void show(Stage stage) {
        Label title = new Label("Smart City Explorer");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label message = new Label();

        Button login = new Button("Login");
        Button register = new Button("Create Account");

        login.setOnAction(e -> {
            try {
                String response = ApiClient.login(email.getText(), password.getText());
                if (response.contains("\"success\":true")) {
                    HomeView.show(stage);
                } else {
                    message.setText("Invalid email or password");
                }
            } catch (Exception ex) {
                message.setText("Backend is not running.");
            }
        });

        register.setOnAction(e -> RegisterView.show(stage));

        VBox root = new VBox(12, title, email, password, login, register, message);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setPrefWidth(400);

        stage.setTitle("Smart City Explorer - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
