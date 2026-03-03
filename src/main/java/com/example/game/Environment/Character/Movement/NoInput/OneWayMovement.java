package com.example.game.Environment.Character.Movement.NoInput;

import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Map.MyMap;
import com.example.game.UI.EGameImages;
import javafx.application.Platform;

import java.util.List;

public class OneWayMovement extends AMovementStrategyWithoutInput {

    private boolean moveUp(double dt, ACharacter enemy, List<ACharacter> characters) {
        enemy.changeImage(EGameImages.Back_Enemy_c.getImage());
        double nextY = enemy.getY() - enemy.getSpeed() * dt;
        if (enemy.canMoveTo(enemy.getX(), nextY, MyMap.getWallColliders(), characters)) {
            enemy.setY(nextY);
            return true;
        }
        enemy.setDirection(Direction.DOWN);
        return false;
    }

    private boolean moveLeft(double dt, ACharacter enemy, List<ACharacter> characters) {
        enemy.changeImage(EGameImages.Left_Side_Enemy_c.getImage());
        double nextX = enemy.getX() - enemy.getSpeed() * dt;
        if (enemy.canMoveTo(nextX, enemy.getY(), MyMap.getWallColliders(), characters)) {
            enemy.setX(nextX);
            return true;
        }
        enemy.setDirection(Direction.RIGHT);
        return false;
    }

    private boolean moveRight(double dt, ACharacter enemy, List<ACharacter> characters) {
        enemy.changeImage(EGameImages.Right_Side_Pg.getImage());
        double nextX = enemy.getX() + enemy.getSpeed() * dt;
        if (enemy.canMoveTo(nextX, enemy.getY(), MyMap.getWallColliders(), characters)) {
            enemy.setX(nextX);
            return true;
        }
        enemy.setDirection(Direction.LEFT);
        return false;
    }

    @Override
    public void movement(double dt, ACharacter enemy, List<ACharacter> characters) {
        boolean moved = switch (enemy.getDirection()) {
            case DOWN  -> moveDown(dt, enemy, characters);
            case UP    -> moveUp(dt, enemy, characters);
            case LEFT  -> moveLeft(dt, enemy, characters);
            case RIGHT -> moveRight(dt, enemy, characters);
        };

        applyPosition(enemy);
    }

    private boolean moveDown(double dt, ACharacter enemy, List<ACharacter> characters) {
        enemy.changeImage(EGameImages.Front_Enemy_c.getImage());
        double nextY = enemy.getY() + enemy.getSpeed() * dt;
        if (enemy.canMoveTo(enemy.getX(), nextY, MyMap.getWallColliders(), characters)) {
            enemy.setY(nextY);
            return true;
        }
        enemy.setDirection(Direction.UP);
        return false;
    }


    private void applyPosition(ACharacter enemy) {
        double y = enemy.getY();
        Platform.runLater(() -> {
            enemy.getImgView().setLayoutY(y);
            enemy.getCld().getShape().setY(y);
            enemy.getvBox().setLayoutY(y - 20);
        });

    }


}
