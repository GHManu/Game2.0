package com.example.game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController {
    private GameModel model;
    private GameView view;
    private Stage stage;

    public GameController(Stage stage) {
        this.stage = stage;
        this.model = new GameModel();
        this.view = new GameView(GameScene.screenWidth, GameScene.screenHeight);

        initController();
    }

    private void initController() {
        view.getStartButton().setOnAction(e -> startGame());
        view.getQuitButton().setOnAction(e -> Platform.exit());

        stage.setTitle("Simple Game");
        stage.setScene(view.getMenuScene());
        stage.show();
    }

    private void startGame() {
        model.startGame();

        Group gameRoot = new Group();
        GameScene gameScene = new GameScene(gameRoot, GameScene.screenWidth, GameScene.screenHeight, Color.BLACK);

        stage.setScene(gameScene);
        GameUpdate gameUpdate = GameUpdate.getInstance(gameRoot);
        gameUpdate.startGameLoop(gameScene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(0));
    }
}
