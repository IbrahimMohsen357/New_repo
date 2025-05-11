package com.example.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
// تحميل صورة الخلفية داخل ImageView
        Image backgroundImage = new Image("img.jpg"); // تأكد من مسار الصورة
        ImageView bgImageView = new ImageView(backgroundImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.setFitWidth(800);  // أو استخدم scene.widthProperty().bind لاحقًا
        bgImageView.setFitHeight(600); // أو استخدم scene.heightProperty().bind
        bgImageView.setEffect(new GaussianBlur(20));

        // إنشاء طبقة زرقاء شفافة
        Region blueOverlay = new Region();
        blueOverlay.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 120, 255, 0.2), // أزرق شفاف
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
        blueOverlay.setEffect(new GaussianBlur(20));
        blueOverlay.setPrefSize(800, 600); // تأكد إنها بنفس حجم الخلفية

        // إعداد الحاوية الرئيسية (StackPane)
        StackPane root = new StackPane();
        root.getChildren().addAll(bgImageView, blueOverlay);

        // إعداد المشهد
        Scene scene = new Scene(root, 800, 600);

        // ربط الأحجام ديناميكيًا مع تغير حجم النافذة
        bgImageView.fitWidthProperty().bind(scene.widthProperty());
        bgImageView.fitHeightProperty().bind(scene.heightProperty());
        blueOverlay.prefWidthProperty().bind(scene.widthProperty());
        blueOverlay.prefHeightProperty().bind(scene.heightProperty());

        // إعداد الطبقة الأساسية
        BorderPane borderPane = null; // تأكد من المسار الصحيح للملف FXML
        borderPane = FXMLLoader.load(getClass().getResource("hello-view.fxml"));



        // إضافة BorderPane إلى StackPane (background image + overlay + FXML content)
        root.getChildren().add(borderPane);

        // عرض النافذة
        primaryStage.setTitle("خلفية مزرقة مع Gaussian Blur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}