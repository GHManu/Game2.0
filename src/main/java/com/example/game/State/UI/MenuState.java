package com.example.game.State.UI;

import com.example.game.GameModel;

public class MenuState implements IGameState{
    @Override
    public void start(GameModel context) {
        context.setGameState(new StartState());
        System.out.println("Gioco avviato!");
    }

    @Override
    public void update(GameModel context) {
        // Nessun aggiornamento
    }

    @Override
    public void stop(GameModel context) {
        context.setGameState(new QuitState(context));
    }
}
