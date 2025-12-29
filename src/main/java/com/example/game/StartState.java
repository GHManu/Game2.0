package com.example.game;

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
        context.setGameState(new QuitState(context));
    }
}
