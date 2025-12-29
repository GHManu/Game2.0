package com.example.game;

public abstract class AMovementStrategyWithoutInput implements IMovementStrategy{
    private ACharacter target;

    public ACharacter getTarget() {
        return target;
    }

    public void setTarget(ACharacter target) {
        this.target = target;
    }
}
