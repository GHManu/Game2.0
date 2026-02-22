package com.example.game.Environment.Character.NPC;

import com.example.game.Environment.AEntity;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.IMovementStrategy;

import java.util.Map;

public abstract class ANPC extends AEntity {
    private boolean talking;
    private Direction direction = Direction.DOWN;
    private IMovementStrategy movementStrategy;
    private Map<String, Dialogue> dialogues;
    public final String PATH = "src/main/resources/com/example/game/Dialogues/";

    public void setDialogues(Map<String, Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public Map<String, Dialogue> getDialogues() {
        return dialogues;
    }

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
