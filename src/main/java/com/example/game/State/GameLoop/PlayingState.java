package com.example.game.State.GameLoop;

import com.example.game.Application.GameUpdate;
import javafx.application.Platform;

public class PlayingState implements IGameLoopState {
    private static final double MIN_PROGRESS = 1e-5;

    @Override
    public void start(GameUpdate context) {
        System.out.println("Il gioco è in corso");
    }

    @Override
    public void update(GameUpdate context, double deltatime) {
        Platform.runLater(context::updateCamera);
        if(context.getPlr().getProgressBar().getProgress() > MIN_PROGRESS)
            context.gameMethodMovementHandler(deltatime);

        if(context.getPlr().getProgressBar().getProgress() > MIN_PROGRESS)
            context.gameMethodAttackHandler(deltatime);
        if(context.getEnemy() != null && context.getEnemy().getProgressBar().getProgress() <= MIN_PROGRESS){
            context.kill_Character(context.getEnemy());
            context.setState(new WinState());
        }
        if(context.getPlr() != null && context.getPlr().getProgressBar().getProgress() <= MIN_PROGRESS)
            context.setState(new GameOverState());
        if(context.getEnemy() != null)
            context.getEnemy().select_attack(deltatime, context.getPlr(), context.getEnemy());
    }

    @Override
    public void stop(GameUpdate context) {

    }
}
