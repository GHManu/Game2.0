package com.example.game.State.UI;

import com.example.game.GameModel;

public class QuitState implements IGameState{

    public QuitState(GameModel context){
        this.start(context);
    }

    @Override
    public void start(GameModel context) {
        System.out.println("QUIT");
    }

    @Override
    public void update(GameModel context) {

    }

    @Override
    public void stop(GameModel context) {

    }
}
