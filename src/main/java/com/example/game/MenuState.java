package com.example.game;

public class MenuState implements IGameState{
    @Override
    public void start(GameModel context) {
        context.setGameState(new PlayingState());
        System.out.println("Gioco avviato!");
    }

    @Override
    public void update(GameModel context) {
        // Nessun aggiornamento in menu
    }

    @Override
    public void stop(GameModel context) {
        System.out.println("Il gioco è già fermo.");
    }
}
