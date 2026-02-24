package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class CollisionResolver {

    public void applyRebound(ACharacterPlayable target, Direction dir, double speed, double rebound) {
        double dx = 0, dy = 0;

        switch (dir) {
            case RIGHT -> dx = -speed;
            case LEFT  -> dx = speed;
            case DOWN  -> dy = speed;
            case UP    -> dy = -speed;
        }

        applyCollision(target, dx, dy, rebound);
    }

    private void applyCollision(ACharacterPlayable target, double dx, double dy, double rebound) {
        Platform.runLater(() -> {
            target.getImgView().setX(target.getImgView().getX() + dx * rebound);
            target.getImgView().setY(target.getImgView().getY() + dy * rebound);

            Rectangle shape = target.getCld().getShape();
            shape.setLayoutX(shape.getLayoutX() - dx * rebound);
            shape.setLayoutY(shape.getLayoutY() - dy * rebound);

            ProgressBar bar = target.getProgressBar();
            bar.setTranslateX(bar.getTranslateX() + dx * rebound * 0.4);
            bar.setTranslateY(bar.getTranslateY() + dy * rebound * 0.4);

            VBox vbox = target.getvBox();
            vbox.setTranslateX(vbox.getTranslateX() + dx * rebound * 0.4);
            vbox.setTranslateY(vbox.getTranslateY() + dy * rebound * 0.4);
        });
    }
}

