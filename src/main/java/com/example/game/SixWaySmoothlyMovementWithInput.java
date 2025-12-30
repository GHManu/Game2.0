package com.example.game;


import javafx.application.Platform;

import java.util.Map;
import java.util.Set;

public class SixWaySmoothlyMovementWithInput extends AMovementStrategyWithInput {
    Sprint sprint = new Sprint();
    private Map<Direction, DirectionSetting> dir_settings;

    public SixWaySmoothlyMovementWithInput(){
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



    public void init(){
        dir_settings = Map.of(
                Direction.UP, new DirectionSetting( Set.of(AInputCommands.forward, AInputCommands.forwardArrow),
                        plr -> plr.getY() > IScreenSettings.sizeTile,
                        plr -> { plr.setDirection(Direction.DOWN); },
                        plr -> plr.changeImage(EGameImages.Back_Pg.getImage()),
                        (dt, plr) -> { if (plr.getCld().canHit(Direction.UP)) updatePosition(plr, plr.getX(), plr.getY() - plr.getSpeed() * dt); } ),

                Direction.DOWN, new DirectionSetting( Set.of(AInputCommands.backward, AInputCommands.backwardArrow),
                        plr -> plr.getY() < IScreenSettings.screenHeight - IScreenSettings.sizeTile * 2,
                        plr -> { plr.setDirection(Direction.UP); },
                        plr -> plr.changeImage(EGameImages.Front_Pg.getImage()),
                        (dt, plr) -> { if (plr.getCld().canHit(Direction.DOWN)) updatePosition(plr, plr.getX(), plr.getY() + plr.getSpeed() * dt); } ),

                Direction.LEFT, new DirectionSetting( Set.of(AInputCommands.leftward, AInputCommands.leftwardArrow),
                        plr -> plr.getX() > IScreenSettings.sizeTile,
                        plr -> { plr.setDirection(Direction.RIGHT); },
                        plr -> plr.changeImage(EGameImages.Left_Side_Pg.getImage()),
                        (dt, plr) -> { if (plr.getCld().canHit(Direction.LEFT)) updatePosition(plr, plr.getX() - plr.getSpeed() * dt, plr.getY()); } ),

                Direction.RIGHT, new DirectionSetting( Set.of(AInputCommands.rightward, AInputCommands.rightwardArrow),
                        plr -> plr.getX() < IScreenSettings.screenWidth - IScreenSettings.sizeTile * 2,
                        plr -> {plr.setDirection(Direction.RIGHT); },
                        plr -> plr.changeImage(EGameImages.Right_Side_Pg.getImage()),
                        (dt, plr) -> { if (plr.getCld().canHit(Direction.RIGHT)) updatePosition(plr, plr.getX() + plr.getSpeed() * dt, plr.getY()); } )
        );
    }




    @Override
    public void movement(double deltatime,  ACharacter target) {
        Platform.runLater(() -> sprint.controlSprint(deltatime, getKeysPressed(), target));
        for (var entry : dir_settings.entrySet()) {
            DirectionSetting dir_set = entry.getValue();
            boolean pressed = getKeysPressed().stream().anyMatch(dir_set.inputs()::contains);
            if (pressed && dir_set.boundary_check().test(target)) {
                dir_set.set_direction_flag().accept(target);
                dir_set.set_image().accept(target);
                dir_set.move_if_allowed().accept(deltatime, target);
            }
        }
    }

}
