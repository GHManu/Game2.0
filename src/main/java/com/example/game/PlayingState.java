package com.example.game;

public class PlayingState implements IGameState{
    @Override
    public void start(GameModel context) {
        System.out.println("Il gioco è già in corso.");
    }

    @Override
    public void update(GameModel context) {

    }

    @Override
    public void stop(GameModel context) {
        context.setGameState(new GameOverState());
        System.out.println("Game Over!");
    }
}
