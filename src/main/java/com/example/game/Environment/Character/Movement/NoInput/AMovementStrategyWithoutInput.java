package com.example.game.Environment.Character.Movement.NoInput;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Movement.IMovementStrategy;

public abstract class AMovementStrategyWithoutInput implements IMovementStrategy {
    private ACharacter target;

    public ACharacter getTarget() {
        return target;
    }

    public void setTarget(ACharacter target) {
        this.target = target;
    }
}
