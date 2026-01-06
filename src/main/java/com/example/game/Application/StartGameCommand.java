package com.example.game.Application;

public class StartGameCommand implements Command {
    private final GameController controller;
    public StartGameCommand(GameController controller) {
        this.controller = controller;
    }
    @Override
    public void execute() {
        controller.startGame();
    }
}
