package com.example.page3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
// تحميل صورة الخلفية داخل ImageView
        Image backgroundImage = new Image("img.png"); // تأكد من مسار الصورة
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
        try {
            borderPane = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // إضافة BorderPane إلى StackPane (background image + overlay + FXML content)
        root.getChildren().add(borderPane);

        // عرض النافذة
        primaryStage.setTitle("خلفية مزرقة مع Gaussian Blur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
