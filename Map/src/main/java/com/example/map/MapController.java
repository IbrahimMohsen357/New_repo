//package com.example.map;
//
//import javafx.animation.FadeTransition;
//import javafx.animation.ScaleTransition;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import javafx.scene.control.Label;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.paint.Color;
//import javafx.scene.input.ScrollEvent;
//import javafx.util.Duration;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class MapController {
//
//    @FXML
//    private ImageView worldMap;
//    @FXML
//    private Pane pinContainer;
//    @FXML
//    private Button zoomInBtn, zoomOutBtn;
//
//    private double zoomFactor = 1.0;
//    private Label locationLabel; // لعرض الرسالة عند مرور الماوس
//
//    // متغيرات لتتبع السحب (Dragging)
//    private double dragStartX;
//    private double dragStartY;
//    private double initialTranslateX;
//    private double initialTranslateY;
//
//    @FXML
//    public void initialize() {
//        // تحميل صورة الخريطة
//        worldMap.setImage(new Image(getClass().getResource("/world_map.png").toExternalForm()));
//        worldMap.setOpacity(1.0);
//        worldMap.setStyle("-fx-background-color: linear-gradient(to bottom, white, blue);");
//
//        List<PinData> pins = Arrays.asList(
//                new PinData(75, 130, Color.RED, "65%", "Egypt", 0),
//                new PinData(150, 240, Color.GOLD, "48%", "Saudi Arabia", 15),
//                new PinData(330, 140, Color.MEDIUMPURPLE, "70%", "Germany", -20),
//                new PinData(470, 100, Color.BLUEVIOLET, "90%", "USA", 0),
//                new PinData(470, 100, Color.BLUEVIOLET, "90%", "USA", 0),
//                new PinData(550, 300, Color.DEEPSKYBLUE, "80%", "Japan", -45)
//        );
//
//
//        for (PinData pin : pins) {
//            addPin(pin);
//        }
//
//        // إعداد أزرار الزووم
//        zoomInBtn.setOnAction(e -> zoom(1.1));
//        zoomOutBtn.setOnAction(e -> zoom(0.9));
//
//        // إضافة خاصية السحب (Dragging)
//        setupDragging();
//
//        // إضافة خاصية الزووم باستخدام عجلة الماوس
//        setupMouseZoom();
//    }
//
//    private void addPin(PinData data) {
//        ImageView pin = new ImageView(new Image(getClass().getResource("/Location-pin.png").toExternalForm()));
//        pin.setFitHeight(30);
//        pin.setFitWidth(30);
//        pin.setTranslateX(data.getX());
//        pin.setTranslateY(data.getY());
//
//        // إضافة تأثير الظل للـ Pin
//        DropShadow shadow = new DropShadow();
//        shadow.setRadius(5);
//        shadow.setOffsetX(3);
//        shadow.setOffsetY(3);
//        pin.setEffect(shadow);
//
//        // إضافة أنيميشن Beat للـ Pin
//        ScaleTransition beat = new ScaleTransition(Duration.millis(500), pin);
//        beat.setFromX(1.0);
//        beat.setFromY(1.0);
//        beat.setToX(1.3);
//        beat.setToY(1.3);
//        beat.setCycleCount(ScaleTransition.INDEFINITE);
//        beat.setAutoReverse(true);
//        beat.play();
//
//        // إضافة الرسالة عند المرور بالماوس
//        pin.setOnMouseEntered(e -> showLocationMessage(data));
//        pin.setOnMouseExited(e -> hideLocationMessage());
//
//        pinContainer.getChildren().add(pin);
//    }
//
//    private void showLocationMessage(PinData data) {
//        if (locationLabel == null) {
//            locationLabel = new Label(data.getCountry() + " - " + data.getPercentage());
//            locationLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5; -fx-background-radius: 5; -fx-border-radius: 5;");
//            locationLabel.setTranslateX(data.getX());
//            locationLabel.setTranslateY(data.getY() - 30);
//            pinContainer.getChildren().add(locationLabel);
//
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), locationLabel);
//            fadeIn.setFromValue(0.0);
//            fadeIn.setToValue(1.0);
//            fadeIn.play();
//        }
//    }
//
//
//    private void hideLocationMessage() {
//        if (locationLabel != null) {
//            // تأثير Fade Out لإخفاء الرسالة ببطء
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), locationLabel);
//            fadeOut.setFromValue(1.0);
//            fadeOut.setToValue(0.0);
//            fadeOut.setOnFinished(e -> pinContainer.getChildren().remove(locationLabel)); // إزالة الرسالة بعد إخفائها
//            fadeOut.play();
//
//            locationLabel = null; // تدمير الرسالة
//        }
//    }
//
//    private void zoom(double factor) {
//        zoomFactor *= factor;
//        pinContainer.setScaleX(zoomFactor);
//        pinContainer.setScaleY(zoomFactor);
//        worldMap.setScaleX(zoomFactor);
//        worldMap.setScaleY(zoomFactor);
//    }
//
//    private void setupDragging() {
//        // عند الضغط بالماوس، احفظ نقطة البداية
//        pinContainer.setOnMousePressed(event -> {
//            dragStartX = event.getSceneX();
//            dragStartY = event.getSceneY();
//            initialTranslateX = pinContainer.getTranslateX();
//            initialTranslateY = pinContainer.getTranslateY();
//        });
//
//        // أثناء السحب، حرك الـ pinContainer والـ worldMap معًا
//        pinContainer.setOnMouseDragged(event -> {
//            double offsetX = event.getSceneX() - dragStartX;
//            double offsetY = event.getSceneY() - dragStartY;
//
//            // تحريك الـ pinContainer
//            pinContainer.setTranslateX(initialTranslateX + offsetX);
//            pinContainer.setTranslateY(initialTranslateY + offsetY);
//
//            // تحريك الـ worldMap بنفس المسافة
//            worldMap.setTranslateX(initialTranslateX + offsetX);
//            worldMap.setTranslateY(initialTranslateY + offsetY);
//        });
//    }
//
//    private void setupMouseZoom() {
//        // إضافة الزووم باستخدام عجلة الماوس
//        pinContainer.setOnScroll((ScrollEvent event) -> {
//            double delta = event.getDeltaY();
//            if (delta > 0) {
//                // Scroll Up: Zoom In
//                zoom(1.1); // نفس عامل الزووم بتاع زر Zoom In
//            } else if (delta < 0) {
//                // Scroll Down: Zoom Out
//                zoom(0.9); // نفس عامل الزووم بتاع زر Zoom Out
//            }
//        });
//
//        // نفس الحدث للـ worldMap عشان يشتغل لو كنتي بتعملي Scroll فوق الخريطة مباشرة
//        worldMap.setOnScroll((ScrollEvent event) -> {
//            double delta = event.getDeltaY();
//            if (delta > 0) {
//                zoom(1.1);
//            } else if (delta < 0) {
//                zoom(0.9);
//            }
//        });
//    }
//}