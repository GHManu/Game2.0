package com.example.game.State.UI;

import com.example.game.Application.GameModel;

public interface IGameState {
    void start(GameModel context);
    void update(GameModel context);
    void stop(GameModel context);
}
