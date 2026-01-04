package com.example.game.State.GameLoop;

import com.example.game.Application.GameUpdate;
import javafx.application.Platform;

public class WinState implements IGameLoopState {

    @Override
    public void start(GameUpdate context) {
        System.out.println("Nemico sconfitto, hai vinto!");
        Platform.runLater(() -> { context.getGame_controller().showVictory();});
    }

    @Override
    public void update(GameUpdate context, double deltatime) {

    }

    @Override
    public void stop(GameUpdate context) {

    }
}
