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
            target.getCld().ret.setX(x);
            target.getCld().ret.setY(y);
            target.getvBox().setLayoutX(x);
            target.getvBox().setLayoutY(y - 20); });
    }



    public void init(){
        dir_settings = Map.of(
                Direction.UP, new DirectionSetting( Set.of(AInputCommands.forward, AInputCommands.forwardArrow),
                        t -> t.getY() > IScreenSettings.sizeTile,
                        t -> { t.setDir_backward(true);t.setDir_forward(false); },
                        t -> t.changeImage(EGameImages.Back_Pg.getImage()),
                        (dt, t) -> { if (t.getCld().fr) updatePosition(t, t.getX(), t.getY() - t.getSpeed() * dt); } ),

                Direction.DOWN, new DirectionSetting( Set.of(AInputCommands.backward, AInputCommands.backwardArrow),
                        t -> t.getY() < IScreenSettings.screenHeight - IScreenSettings.sizeTile * 2,
                        t -> { t.setDir_forward(true); t.setDir_backward(false); },
                        t -> t.changeImage(EGameImages.Front_Pg.getImage()),
                        (dt, t) -> { if (t.getCld().br) updatePosition(t, t.getX(), t.getY() + t.getSpeed() * dt); } ),

                Direction.LEFT, new DirectionSetting( Set.of(AInputCommands.leftward, AInputCommands.leftwardArrow),
                        t -> t.getX() > IScreenSettings.sizeTile,
                        t -> { t.setDir_leftward(true); t.setDir_rightward(false); },
                        t -> t.changeImage(EGameImages.Left_Side_Pg.getImage()),
                        (dt, t) -> { if (t.getCld().dx) updatePosition(t, t.getX() - t.getSpeed() * dt, t.getY()); } ),

                Direction.RIGHT, new DirectionSetting( Set.of(AInputCommands.rightward, AInputCommands.rightwardArrow),
                        t -> t.getX() < IScreenSettings.screenWidth - IScreenSettings.sizeTile * 2,
                        t -> { t.setDir_rightward(true); t.setDir_leftward(false); },
                        t -> t.changeImage(EGameImages.Right_Side_Pg.getImage()),
                        (dt, t) -> { if (t.getCld().sx) updatePosition(t, t.getX() + t.getSpeed() * dt, t.getY()); } )
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
