package com.example.game;

import javafx.application.Platform;

public class OneWayMovementWithoutInput implements IMovementStrategyWithoutInput {
    @Override
    public void movement(double deltatime, ACharacterPlayable target, ACharacter enemy) {
        double maxDestY = IScreenSettings.screenHeight - IScreenSettings.sizeTile;
        double minDestY = 0; // oppure un valore di partenza

        if (enemy.goingDown) {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Front_Enemy_c.getImage());
            });
            enemy.y += enemy.speed * deltatime;
            if (enemy.y >= maxDestY) {
                enemy.y = maxDestY;
                enemy.goingDown = false;
            }
        } else {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Back_Enemy_c.getImage());
            });
            enemy.y -= enemy.speed * deltatime;
            if (enemy.y <= minDestY) {
                enemy.y = minDestY;
                enemy.goingDown = true;
            }
        }

        double finalY = enemy.y;
        Platform.runLater(() -> {
            enemy.imgView.setLayoutY(finalY);
            enemy.cld.ret.setY(finalY);
            enemy.vBox.setLayoutY(finalY - 20);
        });
    }
}
