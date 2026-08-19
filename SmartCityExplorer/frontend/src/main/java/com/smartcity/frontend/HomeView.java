package com.smartcity.frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HomeView {

    public static void show(Stage stage) {

        // =========================
        // TITLE
        // =========================
        Label title = new Label("Smart City Explorer");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        // =========================
        // SEARCH
        // =========================
        TextField search = new TextField();
        search.setPromptText("Search places, restaurants, hotels...");
        search.setPrefWidth(300);

        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear");

        // =========================
        // CATEGORY FILTER
        // =========================
        ComboBox<String> category = new ComboBox<>();

        category.getItems().addAll(
                "All",
                "Places",
                "Restaurants",
                "Hotels"
        );

        category.setValue("All");

        // =========================
        // OTHER BUTTONS
        // =========================
        Button load = new Button("Load All");
        Button detailsButton = new Button("View Details");
        Button logout = new Button("Logout");

        // =========================
        // RESULT LIST
        // =========================
        ListView<String> resultsList = new ListView<>();

        Label message = new Label();

        // =========================
        // LOAD ALL
        // =========================
        load.setOnAction(e -> {

            try {

                List<String> allResults = new ArrayList<>();

                // Places
                List<String> places = ApiClient.getPlaces();

                for (String place : places) {
                    allResults.add("PLACE\n" + place);
                }

                // Restaurants
                List<String> restaurants = ApiClient.getRestaurants();

                for (String restaurant : restaurants) {
                    allResults.add("RESTAURANT\n" + restaurant);
                }

                // Hotels
                List<String> hotels = ApiClient.getHotels();

                for (String hotel : hotels) {
                    allResults.add("HOTEL\n" + hotel);
                }

                resultsList.getItems().setAll(allResults);

                message.setText(
                        allResults.size() + " result(s) loaded."
                );

            } catch (Exception ex) {

                message.setText(
                        "Could not connect to backend."
                );

                ex.printStackTrace();
            }
        });

        // =========================
        // SEARCH
        // =========================
        searchButton.setOnAction(e -> {

            try {

                String keyword = search.getText()
                        .trim()
                        .toLowerCase();

                String selectedCategory = category.getValue();

                List<String> allResults = new ArrayList<>();

                // =========================
                // PLACES
                // =========================
                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Places")) {

                    List<String> places = ApiClient.getPlaces();

                    for (String place : places) {

                        if (keyword.isEmpty()
                                || place.toLowerCase().contains(keyword)) {

                            allResults.add("PLACE\n" + place);
                        }
                    }
                }

                // =========================
                // RESTAURANTS
                // =========================
                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Restaurants")) {

                    List<String> restaurants =
                            ApiClient.getRestaurants();

                    for (String restaurant : restaurants) {

                        if (keyword.isEmpty()
                                || restaurant.toLowerCase().contains(keyword)) {

                            allResults.add(
                                    "RESTAURANT\n" + restaurant
                            );
                        }
                    }
                }

                // =========================
                // HOTELS
                // =========================
                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Hotels")) {

                    List<String> hotels = ApiClient.getHotels();

                    for (String hotel : hotels) {

                        if (keyword.isEmpty()
                                || hotel.toLowerCase().contains(keyword)) {

                            allResults.add("HOTEL\n" + hotel);
                        }
                    }
                }

                // =========================
                // SHOW RESULTS
                // =========================
                resultsList.getItems().setAll(allResults);

                if (allResults.isEmpty()) {

                    message.setText(
                            "No results found."
                    );

                } else {

                    message.setText(
                            allResults.size()
                                    + " result(s) found."
                    );
                }

            } catch (Exception ex) {

                message.setText(
                        "Could not connect to backend."
                );

                ex.printStackTrace();
            }
        });

        // =========================
        // CLEAR
        // =========================
        clearButton.setOnAction(e -> {

            search.clear();

            category.setValue("All");

            resultsList.getItems().clear();

            message.setText(
                    "Search cleared."
            );
        });

        // =========================
        // VIEW DETAILS
        // =========================
        detailsButton.setOnAction(e -> {

            String selected =
                    resultsList.getSelectionModel().getSelectedItem();

            if (selected == null) {

                message.setText(
                        "Please select a result first."
                );

                return;
            }

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("Details");
            alert.setHeaderText("Selected Information");
            alert.setContentText(selected);

            alert.showAndWait();
        });

        // =========================
        // LOGOUT
        // =========================
        logout.setOnAction(e ->
                LoginView.show(stage)
        );

        // =========================
        // TOP BAR
        // =========================
        HBox top = new HBox(
                10,
                search,
                searchButton,
                clearButton,
                category,
                load,
                logout
        );

        top.setPadding(new Insets(10));

        // =========================
        // ROOT
        // =========================
        VBox root = new VBox(
                15,
                title,
                top,
                resultsList,
                detailsButton,
                message
        );

        root.setPadding(new Insets(20));

        // =========================
        // WINDOW
        // =========================
        stage.setTitle("Smart City Explorer");

        stage.setScene(
                new Scene(root, 1000, 650)
        );

        stage.show();
    }
}