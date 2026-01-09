package com.example.game.Application;

public class QuitGameCommand implements Command {
    private final GameModel model;

    public QuitGameCommand(GameModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.stopGame();
        System.exit(0);
    }
}

