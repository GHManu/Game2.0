package com.example.game.Application;

import com.example.game.State.UI.IGameState;
import com.example.game.State.UI.MenuState;

public class GameModel {
    private IGameState game_state;


    public GameModel() {
        this.game_state = new MenuState();
    }

    public IGameState getGame_state() {
        return game_state;
    }


    public void setGame_state(IGameState game_state) {
        this.game_state = game_state;
    }

    public void startGame() {
        game_state.start(this);
    }

    public void updateGame() {
        game_state.update(this);
    }

    public void stopGame() {
        game_state.stop(this);
    }


}
