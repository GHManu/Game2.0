package com.example.game.Environment.Character.Movement.WithInput;


import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Movement.Special.Sprint;

import com.example.game.Environment.Map.MyMap;
import com.example.game.InputManager.InputManager;
import com.example.game.UI.EGameImages;
import javafx.application.Platform;


import java.util.List;

public class SixWaySmoothlyMovement extends AMovementStrategyWithInput {

    private final Sprint sprint = new Sprint();

    public SixWaySmoothlyMovement(InputManager inputManager) {
        setInputManager(inputManager);
    }

    @Override
    public void movement(double dt, ACharacter target, List<ACharacter> characters) {
        sprint.controlSprint(dt, getInputManager(), target);

        if (getInputManager().isMovingForward()) moveUp(dt, target, characters);
        if (getInputManager().isMovingBackward()) moveDown(dt, target, characters);
        if (getInputManager().isMovingLeft()) moveLeft(dt, target, characters);
        if (getInputManager().isMovingRight()) moveRight(dt, target, characters);
    }

    private void moveUp(double dt, ACharacter target, List<ACharacter> characters) {
        target.changeImage(EGameImages.Back_Pg.getImage());
        double nextY = target.getY() - target.getSpeed() * dt;
        if (target.getCld().canHit(Direction.UP) &&
                target.canMoveTo(target.getX(), nextY, MyMap.getWallColliders(), characters)) {
            updatePosition(target, target.getX(), nextY);
        }
    }

    private void moveDown(double dt, ACharacter target, List<ACharacter> characters) {
        target.changeImage(EGameImages.Front_Pg.getImage());
        double nextY = target.getY() + target.getSpeed() * dt;
        if (target.getCld().canHit(Direction.DOWN) &&
                target.canMoveTo(target.getX(), nextY, MyMap.getWallColliders(), characters)) {
            updatePosition(target, target.getX(), nextY);
        }
    }

    private void moveLeft(double dt, ACharacter target, List<ACharacter> characters) {
        target.changeImage(EGameImages.Left_Side_Pg.getImage());
        double nextX = target.getX() - target.getSpeed() * dt;
        if (target.getCld().canHit(Direction.LEFT) &&
                target.canMoveTo(nextX, target.getY(), MyMap.getWallColliders(), characters)) {
            updatePosition(target, nextX, target.getY());
        }
    }

    private void moveRight(double dt, ACharacter target, List<ACharacter> characters) {
        target.changeImage(EGameImages.Right_Side_Pg.getImage());
        double nextX = target.getX() + target.getSpeed() * dt;
        if (target.getCld().canHit(Direction.RIGHT) &&
                target.canMoveTo(nextX, target.getY(), MyMap.getWallColliders(), characters)) {
            updatePosition(target, nextX, target.getY());
        }
    }

    private void updatePosition(ACharacter target, double x, double y) {
        target.setX(x);
        target.setY(y);
        Platform.runLater(() -> {
            target.getImgView().setLayoutX(x);
            target.getImgView().setLayoutY(y);
            target.getCld().getShape().setX(x);
            target.getCld().getShape().setY(y);
            target.getvBox().setLayoutX(x);
            target.getvBox().setLayoutY(y - 20);
        });
    }
}