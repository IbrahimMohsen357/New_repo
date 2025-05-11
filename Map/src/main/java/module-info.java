module com.example.map {
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires eu.hansolo.medusa;
    requires java.desktop;
    requires de.jensd.fx.glyphs.fontawesome;

    opens com.example.map to javafx.fxml;
    exports com.example.map;
}