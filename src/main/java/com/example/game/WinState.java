package com.example.game;

public class WinState implements IGameLoopState{

    @Override
    public void start(GameUpdate context) {
        System.out.println("Nemico sconfitto, hai vinto!");
    }

    @Override
    public void update(GameUpdate context, double deltatime) {

    }

    @Override
    public void stop(GameUpdate context) {

    }
}
