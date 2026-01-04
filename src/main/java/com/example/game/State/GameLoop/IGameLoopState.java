package com.example.game.State.GameLoop;

import com.example.game.Application.GameUpdate;

public interface IGameLoopState {
    void start(GameUpdate context);
    void update(GameUpdate context, double deltatime);
    void stop(GameUpdate context);

}
