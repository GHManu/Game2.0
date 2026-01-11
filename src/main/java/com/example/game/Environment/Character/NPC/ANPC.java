package com.example.game.Environment.Character.NPC;

import com.example.game.Environment.AEntity;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.IMovementStrategy;

public abstract class ANPC extends AEntity {
    private boolean talking;
    private Direction direction = Direction.DOWN;
    private IMovementStrategy movementStrategy;



    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public IMovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public boolean isTalking() {
        return talking;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }
}
