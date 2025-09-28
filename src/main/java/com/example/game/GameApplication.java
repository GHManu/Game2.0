package com.example.game;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApplication extends Application {

//8 righe

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Prova.fxml"));
        Group root = new Group();
        GameScene gameScene = new GameScene(root,GameScene.screenWidth, GameScene.screenHeight, Color.BLACK);
        //stage.setFullScreen(true);
        stage.setScene(gameScene);
        stage.show();
        GameUpdate gameUpdate = new GameUpdate(root);
        gameUpdate.startGameLoop(gameScene, root); //corpo del giocod
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(0));  //quando chiudo con la x, termina anche l'esecuzione, lo stato !=0 indica un
        // uscita non normale
    }

    public static void main(String[] args){
        launch();   //lavora con i thread
    }


}
