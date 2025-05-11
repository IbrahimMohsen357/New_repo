package com.example.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int c1=0;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Payment.fxml"));
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));

        // Load the FXML UI into a Parent node
        Parent root = fxmlLoader.load();

        // Load background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/back.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);

        // Get screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Fit background to screen
        backgroundView.setFitWidth(screenWidth);
        backgroundView.setFitHeight(screenHeight);
        backgroundView.setPreserveRatio(false);
        backgroundView.setEffect(new GaussianBlur(20));

        // Create a transparent blue overlay
        Region blueOverlay = new Region();
        blueOverlay.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 120, 255, 0.2),
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
        blueOverlay.setEffect(new GaussianBlur(20));
        blueOverlay.setPrefSize(screenWidth, screenHeight);

        // Stack background, overlay, and FXML UI
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView, blueOverlay, root);

        // Create the scene
        Scene scene = new Scene(stackPane, 1200, 700);
        scene.getStylesheets().add(UserSession.getInstance().isDarkMode()
                ? "/com/example/maged/DarkMode.css"
                : "/com/example/maged/LightMode.css");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Bank");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/bank-icon.jpeg")));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }

    public static void main(String[] args) {
        launch();
    }
}