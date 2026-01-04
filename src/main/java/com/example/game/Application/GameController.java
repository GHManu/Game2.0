package com.example.game.Application;

import com.example.game.GameModel;
import com.example.game.Scene.GameOverScene;
import com.example.game.Scene.GameScene;
import com.example.game.Scene.VictoryScene;
import com.example.game.UI.GameView;
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
        view.getQuitButton().setOnAction(e -> {this.model.stopGame(); Platform.exit();});

        stage.setTitle("Simple Game");
        stage.setScene(view.getMenuScene());
        stage.show();
    }

    private void startGame() {
        model.startGame();

        Group gameRoot = new Group();
        GameScene gameScene = new GameScene(gameRoot, GameScene.screenWidth, GameScene.screenHeight, Color.BLACK);

        stage.setScene(gameScene);
        GameUpdate gameUpdate = new GameUpdate(gameRoot);
        gameUpdate.startGameLoop(gameScene);
        gameUpdate.setGame_controller(this);
        stage.setResizable(false);
        stage.setOnCloseRequest(event ->{ this.model.stopGame(); System.exit(0);});

    }


    public void showVictory() { stage.setScene(VictoryScene.create(this::startGame, () -> System.exit(0) )); }
    public void showGameOver() { stage.setScene(GameOverScene.create(this::startGame, () -> System.exit(0) )); }
}
