package com.example.game;

public class GameModel {
    private IGameState gameState;


    public GameModel() {
        this.gameState = new MenuState();
    }

    public IGameState getGameState() {
        return gameState;
    }

    public void setGameState(IGameState gameState) {
        this.gameState = gameState;
    }

    public void startGame() {
        gameState.start(this);
    }

    public void updateGame() {
        gameState.update(this);
    }

    public void stopGame() {
        gameState.stop(this);
    }

    public void win(GameController controller) { controller.showVictory(); }
    public void lose(GameController controller) { controller.showGameOver(); }
}
