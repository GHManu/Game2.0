package com.example.game.Environment.Character.Movement.NoInput;

import com.example.game.Application.GameUpdate;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.DirectionSetting;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Map.MyMap;
import com.example.game.UI.EGameImages;

public class OneWayMovement extends AMovementStrategyWithoutInput {

    private static final DirectionSetting MOVE_DOWN = new DirectionSetting(
            im -> true,
            enemy -> enemy.setDirection(Direction.UP),
            enemy -> enemy.changeImage(EGameImages.Front_Enemy_c.getImage()),
            (dt, enemy) -> {
                double nextY = enemy.getY() + enemy.getSpeed() * dt;
                double nextX = enemy.getX();
                if (enemy.canMoveTo(nextX, nextY, MyMap.getWallColliders(), GameUpdate.getCharacters())) {
                    enemy.setY(nextY);
                    return true;
                }
                return false;
            }
    );

    private static final DirectionSetting MOVE_UP = new DirectionSetting(
            im -> true,
            enemy -> enemy.setDirection(Direction.DOWN),
            enemy -> enemy.changeImage(EGameImages.Back_Enemy_c.getImage()),
            (dt, enemy) -> {
                double nextY = enemy.getY() - enemy.getSpeed() * dt;
                double nextX = enemy.getX();
                if (enemy.canMoveTo(nextX, nextY, MyMap.getWallColliders(), GameUpdate.getCharacters())) {
                    enemy.setY(nextY);
                    return true;
                }
                return false;
            }
    );

    private static final DirectionSetting MOVE_RIGHT = new DirectionSetting(
            im -> true,
            enemy -> enemy.setDirection(Direction.LEFT),
            enemy -> enemy.changeImage(EGameImages.Right_Side_Pg.getImage()),
            (dt, enemy) -> {
                double nextX = enemy.getX() + enemy.getSpeed() * dt;
                double nextY = enemy.getY();
                if (enemy.canMoveTo(nextX, nextY, MyMap.getWallColliders(), GameUpdate.getCharacters())) {
                    enemy.setX(nextX); return true;
                }
                return false;
            }
            );

    private static final DirectionSetting MOVE_LEFT = new DirectionSetting(
            im -> true,
            enemy -> enemy.setDirection(Direction.RIGHT),
            enemy -> enemy.changeImage(EGameImages.Left_Side_Enemy_c.getImage()),
            (dt, enemy) -> {
                double nextX = enemy.getX() - enemy.getSpeed() * dt;
                double nextY = enemy.getY();
                if (enemy.canMoveTo(nextX, nextY, MyMap.getWallColliders(), GameUpdate.getCharacters())) {
                    enemy.setX(nextX);
                    return true;
                }
                return false;
            }
            );

    @Override
    public void movement(double dt, ACharacter enemy) {

        DirectionSetting setting = switch (enemy.getDirection()) {
            case DOWN -> MOVE_DOWN;
            case UP -> MOVE_UP;
            case LEFT -> MOVE_LEFT;
            case RIGHT -> MOVE_RIGHT;
        };

        setting.set_image().accept(enemy);

        boolean moved = setting.move_if_allowed().apply(dt, enemy);

        if (!moved) {
            setting.set_direction_flag().accept(enemy);
        }

        double y = enemy.getY();
        enemy.getImgView().setLayoutY(y);
        enemy.getCld().getShape().setY(y);
        enemy.getvBox().setLayoutY(y - 20);
    }


}
