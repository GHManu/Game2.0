package com.example.game.State.UI;

import com.example.game.Application.GameUpdate;
import com.example.game.State.GameLoop.GameOverState;
import com.example.game.State.GameLoop.IGameLoopState;
import com.example.game.State.GameLoop.WinState;
import javafx.application.Platform;

public class PlayingState implements IGameLoopState {

    @Override
    public void start(GameUpdate context) {
        System.out.println("Il gioco è in corso");
    }

    @Override
    public void update(GameUpdate context, double deltatime) {
        Platform.runLater(context::updateCamera);
        if(context.getPlr().getProgressBar().getProgress() > 0.1)
            context.gameMethodMovementHandler(deltatime, context.getKeys_pressed());

        if(context.getPlr().getProgressBar().getProgress() > 0.00000000000001)
            context.gameMethodAttackHandler(deltatime);
        if(context.getEnemy() != null && context.getEnemy().getProgressBar().getProgress() <= 0.1){
            context.kill_Character(context.getEnemy());
            context.setState(new WinState());
        }
        if(context.getPlr() != null && context.getPlr().getProgressBar().getProgress() <= 0.1)
            context.setState(new GameOverState());
        if(context.getEnemy() != null)
            context.getEnemy().select_attack(deltatime, context.getPlr(), context.getEnemy());
    }

    @Override
    public void stop(GameUpdate context) {

    }
}
