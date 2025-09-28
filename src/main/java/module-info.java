module com.example.gamedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires java.desktop;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
}