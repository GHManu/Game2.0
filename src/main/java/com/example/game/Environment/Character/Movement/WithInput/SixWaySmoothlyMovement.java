package com.example.game.Environment.Character.Movement.WithInput;

import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.DirectionSetting;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Movement.Special.Sprint;

import com.example.game.Environment.Map.MyMap;
import com.example.game.InputManager.InputManager;
import com.example.game.UI.EGameImages;
import javafx.application.Platform;

import java.util.Map;


public class SixWaySmoothlyMovement extends AMovementStrategyWithInput {
    Sprint sprint = new Sprint();
    private Map<Direction, DirectionSetting> dir_settings;

    public SixWaySmoothlyMovement(InputManager inputManager){
        setInputManager(inputManager);

        init();
    }


    private void updatePosition(ACharacter target, double x, double y) {
        target.setX(x); target.setY(y);
        Platform.runLater(() -> {
            target.getImgView().setLayoutX(x);
            target.getImgView().setLayoutY(y);
            target.getCld().getShape().setX(x);
            target.getCld().getShape().setY(y);
            target.getvBox().setLayoutX(x);
            target.getvBox().setLayoutY(y - 20); });
    }



    public void init() {
        dir_settings = Map.of(
                Direction.UP, new DirectionSetting(
                        InputManager::isMovingForward,
                        plr -> { plr.setDirection(Direction.DOWN); },
                        plr -> plr.changeImage(EGameImages.Back_Pg.getImage()),
                        (dt, plr) -> {
                            double nextX = plr.getX();
                            double nextY = plr.getY() - plr.getSpeed() * dt;

                            if (plr.getCld().canHit(Direction.UP) &&
                                    plr.canMoveTo(nextX, nextY, MyMap.getWallColliders())) {

                                updatePosition(plr, nextX, nextY);
                                return true;
                            }
                            return false;
                        }
                ),

                Direction.DOWN, new DirectionSetting(
                        InputManager::isMovingBackward,
                        plr -> { plr.setDirection(Direction.UP); },
                        plr -> plr.changeImage(EGameImages.Front_Pg.getImage()),
                        (dt, plr) -> {
                            double nextX = plr.getX();
                            double nextY = plr.getY() + plr.getSpeed() * dt;

                            if (plr.getCld().canHit(Direction.DOWN) &&
                                    plr.canMoveTo(nextX, nextY, MyMap.getWallColliders())) {

                                updatePosition(plr, nextX, nextY);
                                return true;
                            }
                            return false;
                        }
                ),

                Direction.LEFT, new DirectionSetting(
                        InputManager::isMovingLeft,
                        plr -> { plr.setDirection(Direction.RIGHT); },
                        plr -> plr.changeImage(EGameImages.Left_Side_Pg.getImage()),
                        (dt, plr) -> {
                            double nextX = plr.getX() - plr.getSpeed() * dt;
                            double nextY = plr.getY();

                            if (plr.getCld().canHit(Direction.LEFT) &&
                                    plr.canMoveTo(nextX, nextY, MyMap.getWallColliders())) {

                                updatePosition(plr, nextX, nextY);
                                return true;
                            }
                            return false;
                        }
                ),

                Direction.RIGHT, new DirectionSetting(
                        InputManager::isMovingRight,
                        plr -> { plr.setDirection(Direction.RIGHT); },
                        plr -> plr.changeImage(EGameImages.Right_Side_Pg.getImage()),
                        (dt, plr) -> {
                            double nextX = plr.getX() + plr.getSpeed() * dt;
                            double nextY = plr.getY();

                            if (plr.getCld().canHit(Direction.RIGHT) &&
                                    plr.canMoveTo(nextX, nextY, MyMap.getWallColliders())) {

                                updatePosition(plr, nextX, nextY);
                                return true;
                            }
                            return false;
                        }
                )
        );
    }




    @Override
    public void movement(double dt, ACharacter target) {
        sprint.controlSprint(dt, getInputManager(), target);
        for (var entry : dir_settings.entrySet()) {
            DirectionSetting dir = entry.getValue();
            if (dir.input_check().test(getInputManager())) {
                 Platform.runLater(() -> {
                     dir.set_direction_flag().accept(target);
                     dir.set_image().accept(target);
                     dir.move_if_allowed().apply(dt, target);
                 });


            }
        }
    }


}
