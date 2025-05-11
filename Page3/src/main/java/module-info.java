module com.example.page3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.page3 to javafx.fxml;
    exports com.example.page3;
}