package com.example.game;

import javafx.scene.input.KeyCode;

import java.util.Set;

public interface IMovementStrategyWithInput {
    void movement(double deltatime, ACharacter target, Set<KeyCode> keysPressed);
}
