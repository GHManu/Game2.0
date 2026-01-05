package com.example.game.UI;

import com.example.game.Scene.GameOverScene;
import com.example.game.Scene.VictoryScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameView {
    private VBox menuLayout;
    private Button startButton;
    private Button quitButton;
    private Scene menuScene;


    private Scene victoryScene;
    private Scene gameOverScene;

    public GameView(double width, double height) {
        menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getStyleClass().add("vbox-menu");

        startButton = new Button("▶ Start Game");
        startButton.getStyleClass().add("button-start");

        quitButton = new Button("✖ Quit");
        quitButton.getStyleClass().add("button-quit");

        menuLayout.getChildren().addAll(startButton, quitButton);

        menuScene = new Scene(menuLayout, width, height);
        menuScene.getStylesheets().add(getClass().getResource("/com/example/game/Style/SceneStyle.css").toExternalForm());


        victoryScene = VictoryScene.create(
                () -> {},
                () -> {} );
        gameOverScene = GameOverScene.create(
                () -> {},
                () -> {} );
    }


    public Scene getMenuScene() {
        return menuScene;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() { return quitButton; }

    public Scene getVictoryScene() { return victoryScene; }

    public Scene getGameOverScene() { return gameOverScene; }

}
