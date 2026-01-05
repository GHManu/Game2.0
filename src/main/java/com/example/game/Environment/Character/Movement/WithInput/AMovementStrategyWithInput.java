package com.example.game.Environment.Character.Movement.WithInput;

import com.example.game.Environment.Character.Movement.IMovementStrategy;
import com.example.game.InputManager.InputManager;
import javafx.scene.input.KeyCode;

import java.util.Set;

public abstract class AMovementStrategyWithInput implements IMovementStrategy {
    private InputManager inputManager;

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
