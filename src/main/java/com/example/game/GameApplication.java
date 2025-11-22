package com.example.game;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        GameScene gameScene = new GameScene(root,GameScene.screenWidth, GameScene.screenHeight, Color.BLACK);

        stage.setScene(gameScene);
        stage.show();
        GameUpdate gameUpdate = GameUpdate.getInstance(root);
        gameUpdate.startGameLoop(gameScene, root);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    public static void main(String[] args){
        launch();
    }


}
