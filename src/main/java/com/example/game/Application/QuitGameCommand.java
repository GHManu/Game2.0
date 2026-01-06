package com.example.game.Application;

import javafx.application.Platform;

public class QuitGameCommand implements Command {
    private final GameModel model;

    public QuitGameCommand(GameModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.stopGame();
        Platform.exit();
    }
}

