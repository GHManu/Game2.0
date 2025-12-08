package com.example.game;

public class GameOverState implements IGameState{
    @Override
    public void start(GameModel context) {
        context.setGameState(new PlayingState());
        System.out.println("Nuova partita iniziata!");
    }

    @Override
    public void update(GameModel context) {

    }

    @Override
    public void stop(GameModel context) {
        System.out.println("Il gioco è già terminato.");
    }
}
