package com.example.game.UI;

import com.example.game.Scene.GameOverScene;
import com.example.game.Scene.VictoryScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameView {
    private VBox menu_layout;
    private Button start_button;
    private Button quit_button;
    private Scene menu_scene;


    private Scene victory_scene;
    private Scene gameover_scene;

    public GameView(double width, double height) {
        menu_layout = new VBox(20);
        menu_layout.setAlignment(Pos.CENTER);
        menu_layout.getStyleClass().add("vbox-menu");

        start_button = new Button("▶ Start Game");
        start_button.getStyleClass().add("button-start");

        quit_button = new Button("✖ Quit");
        quit_button.getStyleClass().add("button-quit");

        menu_layout.getChildren().addAll(start_button, quit_button);

        menu_scene = new Scene(menu_layout, width, height);
        menu_scene.getStylesheets().add(getClass().getResource("/com/example/game/Style/SceneStyle.css").toExternalForm());


        victory_scene = VictoryScene.create(
                () -> {},
                () -> {} );
        gameover_scene = GameOverScene.create(
                () -> {},
                () -> {} );
    }


    public Scene getMenu_scene() {
        return menu_scene;
    }

    public Button getStart_button() {
        return start_button;
    }

    public Button getQuit_button() { return quit_button; }

    public Scene getVictory_scene() { return victory_scene; }

    public Scene getGameover_scene() { return gameover_scene; }

}
