package com.example.game.Application;

public class RetryCommand implements Command {
    private final GameController controller;

    public RetryCommand(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.startGame();
    }
}

