package com.example.game.Environment.Character.Movement.WithInput;

import com.example.game.Environment.Character.Movement.IMovementStrategy;
import javafx.scene.input.KeyCode;

import java.util.Set;

public abstract class AMovementStrategyWithInput implements IMovementStrategy {
    private Set<KeyCode> keysPressed;

    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }

    public void setKeysPressed(Set<KeyCode> keysPressed) {
        this.keysPressed = keysPressed;
    }
}
