package com.example.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // تحميل محتوى FXML
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Gauge.fxml"));
        Parent root = fxmlLoader.load();

        // تحميل صورة الخلفية
        Image backgroundImage = new Image(getClass().getResourceAsStream("/back.jpg"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1920); // عرض مناسب حسب الشاشة
        backgroundView.setFitHeight(1080); // ارتفاع مناسب
        backgroundView.setPreserveRatio(false);
        backgroundView.setEffect(new GaussianBlur(20)); // تطبيق البلور

        // إنشاء طبقة زرقاء شفافة
        Region blueOverlay = new Region();
        blueOverlay.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 120, 255, 0.2), // أزرق شفاف
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
        blueOverlay.setEffect(new GaussianBlur(20));
        blueOverlay.setPrefSize(800, 600); // تأكد إنها بنفس حجم الخلفية

        // ترتيب المكونات داخل StackPane (الخلفية تحت، FXML فوق)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView,blueOverlay , root);

        // إنشاء المشهد
        Scene scene = new Scene(stackPane);
        stage.setTitle("Bank User Map");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
