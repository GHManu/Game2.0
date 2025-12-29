package com.example.game;

public interface IGameLoopState {
    void start(GameUpdate context);
    void update(GameUpdate context, double deltatime);
    void stop(GameUpdate context);

}
