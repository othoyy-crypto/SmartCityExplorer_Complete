package com.smartcity.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    public static void show(Stage stage) {
        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label message = new Label();

        Button create = new Button("Register");
        Button back = new Button("Back to Login");

       create.setOnAction(e -> {
    try {
        String response = ApiClient.register(
                name.getText(),
                email.getText(),
                password.getText()
        );

        if (response.contains("\"id\"")) {
            message.setText("Registration successful!");
        } else {
            message.setText("Registration failed: " + response);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        message.setText("Error: " + ex.getMessage());
    }
});

        back.setOnAction(e -> LoginView.show(stage));

        VBox root = new VBox(12, title, name, email, password, create, back, message);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setPrefWidth(400);

        stage.setTitle("Smart City Explorer - Register");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
