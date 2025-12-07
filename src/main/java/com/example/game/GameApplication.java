package com.example.game;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Layout verticale per il menu
        VBox menuLayout = new VBox(20); // spaziatura 20px tra i bottoni
        menuLayout.setAlignment(Pos.CENTER); // centra i bottoni
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #1a1a1a, #333333);");

        Scene begin_menu = new Scene(menuLayout, GameScene.screenWidth, GameScene.screenHeight);

        Group gameRoot = new Group();
        GameScene gameScene = new GameScene(gameRoot, GameScene.screenWidth, GameScene.screenHeight, Color.BLACK);


        Button start = new Button("▶ Start Game");
        start.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20;");
        start.setOnAction(actionEvent -> {
            stage.setScene(gameScene);
            GameUpdate gameUpdate = GameUpdate.getInstance(gameRoot);
            gameUpdate.startGameLoop(gameScene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> System.exit(0));
        });

        Button quit = new Button("✖ Quit");
        quit.setStyle("-fx-font-size: 18px; -fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10 20;");
        quit.setOnAction(actionEvent -> {
            javafx.application.Platform.exit();
        });


        menuLayout.getChildren().addAll(start, quit);

        stage.setTitle("Simple Game");
        stage.setScene(begin_menu);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }


}
