package com.smartcity.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HomeView {

    public static void show(Stage stage) {

        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to bottom right, #07152f, #123b70, #155e91);"
        );

        // =====================================================
        // TOP NAVIGATION
        // =====================================================

        HBox navBar = new HBox(20);

        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setPadding(
                new Insets(20, 35, 20, 35)
        );

        Label logo = new Label(
                "🌆  Smart City Explorer"
        );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        logo.setTextFill(Color.WHITE);

        Region navSpacer = new Region();

        HBox.setHgrow(
                navSpacer,
                Priority.ALWAYS
        );

        Button homeButton = navButton("Home");
        Button aboutButton = navButton("About");
        Button logoutButton = navButton("Logout");

        navBar.getChildren().addAll(
                logo,
                navSpacer,
                homeButton,
                aboutButton,
                logoutButton
        );

        root.setTop(navBar);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent = new VBox(18);

        mainContent.setAlignment(Pos.TOP_CENTER);

        mainContent.setPadding(
                new Insets(30, 50, 40, 50)
        );

        Label welcome = new Label(
                "WELCOME TO"
        );

        welcome.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        welcome.setTextFill(
                Color.web("#60a5fa")
        );

        Label title = new Label(
                "Smart City Explorer"
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        42
                )
        );

        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Discover places, restaurants and hotels around your city"
        );

        subtitle.setFont(
                Font.font("Arial", 16)
        );

        subtitle.setTextFill(
                Color.web("#dbeafe")
        );

        // =====================================================
        // SEARCH AREA
        // =====================================================

        HBox searchArea = new HBox(10);

        searchArea.setAlignment(Pos.CENTER);

        TextField search = new TextField();

        search.setPromptText(
                "Search places, restaurants, hotels..."
        );

        search.setPrefWidth(420);
        search.setPrefHeight(45);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-font-size: 14;" +
                "-fx-padding: 0 15;"
        );

        ComboBox<String> category =
                new ComboBox<>();

        category.getItems().addAll(
                "All",
                "Places",
                "Restaurants",
                "Hotels"
        );

        category.setValue("All");

        category.setPrefWidth(130);
        category.setPrefHeight(45);

        Button searchButton =
                mainButton("🔍 Search", "#2563eb");

        Button clearButton =
                mainButton("Clear", "#64748b");

        Button load =
                mainButton("Load All", "#16a34a");

        searchArea.getChildren().addAll(
                search,
                category,
                searchButton,
                clearButton,
                load
        );

        // =====================================================
        // RESULT LIST
        // =====================================================

        ListView<String> resultsList =
                new ListView<>();

        resultsList.setPrefHeight(270);

        resultsList.setStyle(
                "-fx-background-color: rgba(255,255,255,0.96);" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );

        Label message = new Label();

        message.setTextFill(
                Color.web("#dbeafe")
        );

        message.setFont(
                Font.font("Arial", 13)
        );

        // =====================================================
        // LOAD ALL
        // =====================================================

        load.setOnAction(e -> {

            try {

                List<String> allResults =
                        new ArrayList<>();

                // Places
                List<String> places =
                        ApiClient.getPlaces();

                for (String place : places) {

                    allResults.add(
                            "📍  PLACE\n" + place
                    );
                }

                // Restaurants
                List<String> restaurants =
                        ApiClient.getRestaurants();

                for (String restaurant : restaurants) {

                    allResults.add(
                            "🍴  RESTAURANT\n" + restaurant
                    );
                }

                // Hotels
                List<String> hotels =
                        ApiClient.getHotels();

                for (String hotel : hotels) {

                    allResults.add(
                            "🏨  HOTEL\n" + hotel
                    );
                }

                resultsList.getItems()
                        .setAll(allResults);

                message.setText(
                        allResults.size()
                                + " result(s) loaded."
                );

            } catch (Exception ex) {

                message.setText(
                        "Could not connect to backend."
                );

                ex.printStackTrace();
            }
        });

        // =====================================================
        // SEARCH
        // =====================================================

        searchButton.setOnAction(e -> {

            try {

                String keyword =
                        search.getText()
                                .trim()
                                .toLowerCase();

                String selectedCategory =
                        category.getValue();

                List<String> allResults =
                        new ArrayList<>();

                // =================================================
                // PLACES
                // =================================================

                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Places")) {

                    List<String> places =
                            ApiClient.getPlaces();

                    for (String place : places) {

                        if (keyword.isEmpty()
                                || place.toLowerCase()
                                .contains(keyword)) {

                            allResults.add(
                                    "📍  PLACE\n" + place
                            );
                        }
                    }
                }

                // =================================================
                // RESTAURANTS
                // =================================================

                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Restaurants")) {

                    List<String> restaurants =
                            ApiClient.getRestaurants();

                    for (String restaurant : restaurants) {

                        if (keyword.isEmpty()
                                || restaurant.toLowerCase()
                                .contains(keyword)) {

                            allResults.add(
                                    "🍴  RESTAURANT\n"
                                            + restaurant
                            );
                        }
                    }
                }

                // =================================================
                // HOTELS
                // =================================================

                if (selectedCategory.equals("All")
                        || selectedCategory.equals("Hotels")) {

                    List<String> hotels =
                            ApiClient.getHotels();

                    for (String hotel : hotels) {

                        if (keyword.isEmpty()
                                || hotel.toLowerCase()
                                .contains(keyword)) {

                            allResults.add(
                                    "🏨  HOTEL\n" + hotel
                            );
                        }
                    }
                }

                // =================================================
                // SHOW RESULTS
                // =================================================

                resultsList.getItems()
                        .setAll(allResults);

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

        // =====================================================
        // CLEAR
        // =====================================================

        clearButton.setOnAction(e -> {

            search.clear();

            category.setValue("All");

            resultsList.getItems().clear();

            message.setText(
                    "Search cleared."
            );
        });

        // =====================================================
        // VIEW DETAILS
        // =====================================================

        Button detailsButton =
                mainButton(
                        "View Details",
                        "#0f2f5f"
                );

        detailsButton.setOnAction(e -> {

            String selected =
                    resultsList
                            .getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message.setText(
                        "Please select a result first."
                );

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setTitle("Details");
            alert.setHeaderText(
                    "Selected Information"
            );

            alert.setContentText(selected);

            alert.showAndWait();
        });

        // =====================================================
        // CATEGORY CARDS
        // =====================================================

        HBox cards = new HBox(15);

        cards.setAlignment(Pos.CENTER);

        cards.getChildren().addAll(

                categoryCard(
                        "📍",
                        "Places",
                        "Explore city places"
                ),

                categoryCard(
                        "🍴",
                        "Restaurants",
                        "Find great food"
                ),

                categoryCard(
                        "🏨",
                        "Hotels",
                        "Find accommodation"
                ),

                categoryCard(
                        "🏙",
                        "City Explorer",
                        "Discover your city"
                )
        );

        // =====================================================
        // ADD MAIN CONTENT
        // =====================================================

        mainContent.getChildren().addAll(
                welcome,
                title,
                subtitle,
                searchArea,
                cards,
                resultsList,
                detailsButton,
                message
        );

        root.setCenter(mainContent);

        // =====================================================
        // LOGOUT
        // =====================================================

        logoutButton.setOnAction(e ->
                LoginView.show(stage)
        );

        // =====================================================
        // WINDOW
        // =====================================================

        stage.setTitle(
                "Smart City Explorer"
        );

        stage.setScene(
                new Scene(
                        root,
                        1200,
                        750
                )
        );

        stage.show();
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private static Button navButton(
            String text) {

        Button button =
                new Button(text);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #dbeafe;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================================
    // MAIN BUTTON
    // =========================================================

    private static Button mainButton(
            String text,
            String color) {

        Button button =
                new Button(text);

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(0, 17, 0, 17)
        );

        button.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================================
    // CATEGORY CARD
    // =========================================================

    private static VBox categoryCard(
            String icon,
            String title,
            String subtitle) {

        VBox card =
                new VBox(6);

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefWidth(180);
        card.setPrefHeight(100);

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.10);" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: rgba(255,255,255,0.18);" +
                "-fx-border-radius: 14;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(25)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        titleLabel.setTextFill(
                Color.WHITE
        );

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setFont(
                Font.font("Arial", 11)
        );

        subtitleLabel.setTextFill(
                Color.web("#bfdbfe")
        );

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                subtitleLabel
        );

        return card;
    }
}