package com.example.game.State.GameLoop;

import com.example.game.Application.GameUpdate;
import javafx.application.Platform;

public class PlayingState implements IGameLoopState {

    @Override
    public void start(GameUpdate context) {
        System.out.println("Il gioco è in corso");
    }

    @Override
    public void update(GameUpdate context, double deltatime) {
        Platform.runLater(() -> context.getCamera().update());

        if(context.getPlr().isAlive()){
            context.gameMethodMovementHandler(deltatime);
            context.gameMethodAttackHandler(deltatime);

        }

        if (!context.getEnemy().isAlive() && context.getPlr().isAlive()) {
            context.getDestroyer().destroyCharacter(context.getEnemy());
            context.setState(new WinState());
        }
        if (!context.getPlr().isAlive()) {
            context.getDestroyer().destroyCharacter(context.getPlr());
            context.setState(new GameOverState());
        }

        if(context.getEnemy().isAlive())
            context.getEnemy().select_attack(deltatime, context.getPlr(), context.getEnemy());
    }

    @Override
    public void stop(GameUpdate context) {

    }
}
