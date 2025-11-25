package com.example.game;

import javafx.application.Platform;

public class OneWayMovementWithoutInput extends IMovementStrategyWithoutInput {

    @Override
    public void movement(double deltatime, ACharacter enemy) {
        double maxDestY = IScreenSettings.screenHeight - IScreenSettings.sizeTile*2;
        double minDestY = IScreenSettings.sizeTile;

        if (enemy.isGoingDown()) {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Front_Enemy_c.getImage());
            });
            double y = enemy.getY();
            y += enemy.getSpeed() * deltatime;
            enemy.setY(y);
            if (enemy.getY() >= maxDestY) {
                enemy.setY(maxDestY);
                enemy.setGoingDown(false);
            }
        } else {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Back_Enemy_c.getImage());
            });
            double y = enemy.getY();
            y -= enemy.getSpeed() * deltatime;
            enemy.setY(y);
            if (enemy.getY() <= minDestY) {
                enemy.setY(minDestY);
                enemy.setGoingDown(true);
            }
        }

        double finalY = enemy.getY();
        Platform.runLater(() -> {
            enemy.getImgView().setLayoutY(finalY);
            enemy.getCld().ret.setY(finalY);
            enemy.getvBox().setLayoutY(finalY - 20);
        });
    }
}
