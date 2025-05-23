package com.example.test;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class OperationSucceeded implements Initializable {
    public Button ToHome;
    String sri1,sri2,sri3;

    @FXML
    private StackPane animationContainer;

    @FXML
    private Circle outerCircle;

    @FXML
    private Circle middleCircle;

    @FXML
    private SVGPath checkmark;

    @FXML
    private Rectangle particleContainer;

    private final Random random = new Random();

    public void getT(String s1,String s2,String s3){
        this.sri1=s1;
        this.sri2=s2;
        this.sri3=s3;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set initial states
        outerCircle.setScaleX(0);
        outerCircle.setScaleY(0);
        middleCircle.setScaleX(0);
        middleCircle.setScaleY(0);
        checkmark.setScaleX(0);
        checkmark.setScaleY(0);

        // Start the animation sequence
        playSuccessAnimation();
    }

    private void playSuccessAnimation() {
        // Animation for the outer circle
        ScaleTransition outerCircleAnim = new ScaleTransition(Duration.millis(500), outerCircle);
        outerCircleAnim.setFromX(0);
        outerCircleAnim.setFromY(0);
        outerCircleAnim.setToX(1);
        outerCircleAnim.setToY(1);
        outerCircleAnim.setInterpolator(Interpolator.EASE_OUT);

        // Animation for the middle circle
        ScaleTransition middleCircleAnim = new ScaleTransition(Duration.millis(400), middleCircle);
        middleCircleAnim.setFromX(0);
        middleCircleAnim.setFromY(0);
        middleCircleAnim.setToX(1);
        middleCircleAnim.setToY(1);
        middleCircleAnim.setInterpolator(Interpolator.EASE_OUT);

        // Animation for the checkmark
        ScaleTransition checkmarkScaleAnim = new ScaleTransition(Duration.millis(400), checkmark);
        checkmarkScaleAnim.setFromX(0);
        checkmarkScaleAnim.setFromY(0);
        checkmarkScaleAnim.setToX(1);
        checkmarkScaleAnim.setToY(1);
        checkmarkScaleAnim.setInterpolator(Interpolator.EASE_OUT);

        // Animation for drawing the checkmark
        StrokeTransition checkmarkDrawAnim = new StrokeTransition(Duration.millis(600), checkmark);
        checkmarkDrawAnim.setFromValue(Color.TRANSPARENT);
        checkmarkDrawAnim.setToValue(Color.WHITE);

        // Create a particle animation
        ParallelTransition particleAnimation = createParticleAnimation();

        // Sequence the animations
        SequentialTransition sequence = new SequentialTransition(
            outerCircleAnim,
            middleCircleAnim,
            new ParallelTransition(checkmarkScaleAnim, checkmarkDrawAnim),
            particleAnimation
        );

        // Start the animation sequence
        sequence.play();

        // Add some confetti particles after the main animation
        sequence.setOnFinished(e -> createConfettiEffect());
    }

    private ParallelTransition createParticleAnimation() {
        ParallelTransition parallelTransition = new ParallelTransition();

        for (int i = 0; i < 8; i++) {
            Circle particle = new Circle(3, Color.web("#008cff"));
            particle.setOpacity(0);
            animationContainer.getChildren().add(particle);

            // Position the particle in the center
            particle.setTranslateX(0);
            particle.setTranslateY(0);

            // Create fade in animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), particle);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Create movement animation
            double angle = i * (360.0 / 8);
            double distance = 80;
            double targetX = Math.cos(Math.toRadians(angle)) * distance;
            double targetY = Math.sin(Math.toRadians(angle)) * distance;

            TranslateTransition translate = new TranslateTransition(Duration.millis(700), particle);
            translate.setFromX(0);
            translate.setFromY(0);
            translate.setToX(targetX);
            translate.setToY(targetY);
            translate.setInterpolator(Interpolator.EASE_OUT);

            // Create fade out animation
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), particle);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setDelay(Duration.millis(400));

            // Combine animations for this particle
            SequentialTransition particleSequence = new SequentialTransition(
                new ParallelTransition(fadeIn, translate),
                fadeOut
            );

            parallelTransition.getChildren().add(particleSequence);
        }

        return parallelTransition;
    }

    private void createConfettiEffect() {
        // Get a reference to the AnchorPane (main container)
        // We need to get it safely in case the scene graph is not fully initialized
        if (animationContainer.getScene() == null || animationContainer.getScene().getRoot() == null) {
            return; // Skip confetti if scene is not ready
        }

        // Find the AnchorPane container (typically the root or close to it)
        AnchorPane anchorPane = (AnchorPane) animationContainer.getParent();

        for (int i = 0; i < 30; i++) {
            // Create random colored confetti
            Rectangle confetti = new Rectangle(
                    random.nextInt(5) + 3,
                    random.nextInt(5) + 3,
                    Color.rgb(
                            random.nextInt(100) + 155,  // More bright colors
                            random.nextInt(100) + 155,
                            random.nextInt(100) + 155
                    )
            );

            // Add to the AnchorPane
            anchorPane.getChildren().add(confetti);

            // Get center position of animation container
            double centerX = animationContainer.getLayoutX() + animationContainer.getWidth() / 2;
            double centerY = animationContainer.getLayoutY() + animationContainer.getHeight() / 2;

            // Random start position near the center
            double startX = centerX + random.nextInt(40) - 20;
            double startY = centerY + random.nextInt(40) - 20;

            confetti.setLayoutX(startX);
            confetti.setLayoutY(startY);

            // Create animation
            double endX = startX + random.nextInt(160) - 80;
            double endY = startY + random.nextInt(100) + 50;  // Fall down mostly

            // Transition for movement
            TranslateTransition translate = new TranslateTransition(Duration.millis(random.nextInt(1000) + 1000), confetti);
            translate.setToX(endX - startX);
            translate.setToY(endY - startY);
            translate.setInterpolator(Interpolator.SPLINE(0.1, 0.4, 0.7, 0.2));  // Custom curve for "floating" effect

            // Rotation animation
            RotateTransition rotate = new RotateTransition(Duration.millis(random.nextInt(500) + 500), confetti);
            rotate.setByAngle(random.nextInt(540) - 270);
            rotate.setCycleCount(Animation.INDEFINITE);
            rotate.setInterpolator(Interpolator.LINEAR);

            // Fade out at the end
            FadeTransition fade = new FadeTransition(Duration.millis(300), confetti);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setDelay(Duration.millis(random.nextInt(700) + 700));

            // Store the reference to the anchorPane for cleanup
            Rectangle finalConfetti = confetti;

            // Play animations
            ParallelTransition parallelTransition = new ParallelTransition(translate, rotate, fade);
            parallelTransition.setOnFinished(e -> {
                try {
                    anchorPane.getChildren().remove(finalConfetti);
                } catch (Exception ex) {
                    // Ignore if the node was already removed
                }
            });
            parallelTransition.play();
        }
    }

    public void Clicking6(ActionEvent actionEvent) throws IOException {
        // Load the FXML for Payment page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
        Parent root = fxmlLoader.load();
        Payment payment=fxmlLoader.getController();
        payment.addHistory(sri1,sri2,sri3);
        payment.setColors();
        // Loads the FXML file
        Stage stage = new Stage();
        stage.setTitle("Operation Succeeded");
        
        // Add background styling
        // Load background image
        Image backgroundImage;
        try {
            backgroundImage = new Image(getClass().getResourceAsStream("/back.jpg"));
            if (backgroundImage.isError()) {
                System.err.println("Error loading background image: " + backgroundImage.getException().getMessage());
                backgroundImage = null;
            }
        } catch (Exception e) {
            System.err.println("Failed to load background image: " + e.getMessage());
            backgroundImage = null;
        }
        
        // Get screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        
        // Create stack pane for layering content
        StackPane stackPane = new StackPane();
        
        // Add background and overlay if image loaded successfully
        if (backgroundImage != null) {
            ImageView backgroundView = new ImageView(backgroundImage);
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
            
            // Add background and overlay to stack
            stackPane.getChildren().addAll(backgroundView, blueOverlay);
        } else {
            // Create a fallback blue gradient background if image failed to load
            Region fallbackBackground = new Region();
            fallbackBackground.setBackground(new Background(new BackgroundFill(
                    Color.rgb(10, 60, 120, 1.0),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
            )));
            fallbackBackground.setPrefSize(screenWidth, screenHeight);
            stackPane.getChildren().add(fallbackBackground);
        }
        
        // Add the UI content on top
        stackPane.getChildren().add(root);
        
        // Create the scene
        Scene scene = new Scene(stackPane, 1200, 700);
        stage.setScene(scene);
        stage.setResizable(false);  // Optional: you can set it to false if you don't want resizing
        stage.show();  // Show the new stage
        
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
