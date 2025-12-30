package com.example.game;

import javafx.application.Platform;
import java.util.Set;

public class OneWayMovementWithoutInput extends AMovementStrategyWithoutInput {

    private static final DirectionSetting MOVE_DOWN = new DirectionSetting(
            Set.of(), // nessun input
            enemy -> enemy.getY() < IScreenSettings.screenHeight - IScreenSettings.sizeTile * 2,
            enemy -> enemy.setDirection(Direction.UP),
            enemy -> enemy.changeImage(EGameImages.Front_Enemy_c.getImage()),
            (dt, enemy) -> enemy.setY(enemy.getY() + enemy.getSpeed() * dt)
    );

    private static final DirectionSetting MOVE_UP = new DirectionSetting(
            Set.of(),
            enemy -> enemy.getY() > IScreenSettings.sizeTile,
            enemy -> enemy.setDirection(Direction.DOWN),
            enemy -> enemy.changeImage(EGameImages.Back_Enemy_c.getImage()),
            (dt, enemy) -> enemy.setY(enemy.getY() - enemy.getSpeed() * dt)
    );


    @Override
    public void movement(double deltatime, ACharacter enemy) {
        DirectionSetting setting = enemy.getDirection() == Direction.DOWN ? MOVE_DOWN : MOVE_UP;
        Platform.runLater(() -> setting.set_image().accept(enemy));
        if (setting.boundary_check().test(enemy)) {
            setting.move_if_allowed().accept(deltatime, enemy);
        } else {
            setting.set_direction_flag().accept(enemy);
        } double finalY = enemy.getY();
        Platform.runLater(() -> {
            enemy.getImgView().setLayoutY(finalY);
            enemy.getCld().getShape().setY(finalY);
            enemy.getvBox().setLayoutY(finalY - 20); });
    }


}
