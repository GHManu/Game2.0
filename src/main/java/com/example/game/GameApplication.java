package com.example.game;


import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new GameController(stage);
    }

    public static void main(String[] args){
        launch();
    }


}
