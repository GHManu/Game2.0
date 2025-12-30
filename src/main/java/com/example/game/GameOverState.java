package com.example.game;

import javafx.application.Platform;

public class GameOverState implements IGameLoopState{

    @Override
    public void start(GameUpdate context) {
        System.out.println("GameOver");
        context.kill_Character(context.getPlr());
        Platform.runLater(() -> { context.getGame_controller().showGameOver();});
    }

    @Override
    public void update(GameUpdate context, double deltatime) {

    }

    @Override
    public void stop(GameUpdate context) {

    }
}
