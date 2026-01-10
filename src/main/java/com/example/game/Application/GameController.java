package com.example.game.Application;

import com.example.game.Scene.GameOverScene;
import com.example.game.Scene.GameScene;
import com.example.game.Scene.VictoryScene;
import com.example.game.UI.GameView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController {
    private GameModel model;
    private GameView view;
    private Stage stage;
    private CommandRegistry registry;

    public GameController(Stage stage) {
        this.stage = stage;
        this.model = new GameModel();
        this.view = new GameView(GameScene.screenWidth, GameScene.screenHeight);
        this.registry = new CommandRegistry();

        initController();
    }

    private void initController() {
        registry.register("start", new StartGameCommand(this));
        registry.register("quit", new QuitGameCommand(model));

        view.getStartButton().setOnAction(e -> registry.get("start").execute());
        view.getQuitButton().setOnAction(e -> registry.get("quit").execute());


        stage.setTitle("Simple Game");
        stage.setScene(view.getMenuScene());
        stage.show();
    }

    public void startGame() {
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


    public void showVictory() {
        Scene scene = VictoryScene.create( new RetryCommand(this), new QuitGameCommand(model) );
        stage.setScene(scene);
    }

    public void showGameOver() {
        Scene scene = GameOverScene.create( new RetryCommand(this), new QuitGameCommand(model) );
        stage.setScene(scene);
    }
}
