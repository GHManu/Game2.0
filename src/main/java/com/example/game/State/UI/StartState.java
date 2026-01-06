package com.example.game.State.UI;

import com.example.game.Application.GameModel;

public class StartState implements IGameState{
    @Override
    public void start(GameModel context) {
        System.out.println("Il gioco è iniziato.");
    }

    @Override
    public void update(GameModel context) {

    }

    @Override
    public void stop(GameModel context) {
        context.setGame_state(new QuitState(context));
    }
}
