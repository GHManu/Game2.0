package com.example.game;

public interface IGameState {
    void start(GameModel context);
    void update(GameModel context);
    void stop(GameModel context);
}
