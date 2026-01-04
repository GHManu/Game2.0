module com.example.gamedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires java.desktop;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
    exports com.example.game.Weapon;
    opens com.example.game.Weapon to javafx.fxml;
    exports com.example.game.Scene;
    opens com.example.game.Scene to javafx.fxml;
    exports com.example.game.State.GameLoop;
    opens com.example.game.State.GameLoop to javafx.fxml;
    exports com.example.game.State.UI;
    opens com.example.game.State.UI to javafx.fxml;
    exports com.example.game.UI;
    opens com.example.game.UI to javafx.fxml;
    exports com.example.game.Application;
    opens com.example.game.Application to javafx.fxml;
    exports com.example.game.Environment;
    opens com.example.game.Environment to javafx.fxml;
    exports com.example.game.Weapon.Ranged;
    opens com.example.game.Weapon.Ranged to javafx.fxml;
    exports com.example.game.Weapon.CloseRange;
    opens com.example.game.Weapon.CloseRange to javafx.fxml;
    exports com.example.game.Environment.Interactable;
    opens com.example.game.Environment.Interactable to javafx.fxml;
    exports com.example.game.Environment.NonInteractable;
    opens com.example.game.Environment.NonInteractable to javafx.fxml;
}