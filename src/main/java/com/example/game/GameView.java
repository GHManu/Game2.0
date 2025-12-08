package com.example.game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameView {
    private VBox menuLayout;
    private Button startButton;
    private Button quitButton;
    private Scene menuScene;

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
        menuScene.getStylesheets().add(getClass().getResource("Style/SceneStyle.css").toExternalForm());
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
