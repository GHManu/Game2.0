package com.example.game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//circa 33 righe

public class Projectile extends AEntity {

    double directionX, directionY;
    double xDest, yDest;
    protected static final double margine = 2.0;
    protected double normal_damage = 0.2;   //in percentuale
    protected final double speed = 2.5;

    public Projectile(Image img, double x, double y, double xDest, double yDest) {

            this.imgView = new ImageView(img);
            this.imgView.setFitWidth(ScreenSettings.sizeTile);
            this.imgView.setFitHeight(ScreenSettings.sizeTile);

            this.x = x;
            this.y = y;
            this.imgView.setLayoutX(this.x);
            this.imgView.setLayoutY(this.y);




        this.xDest = xDest;
        this.yDest = yDest;

        double dx = this.xDest - x;
        double dy = this.yDest - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        directionX = dx / distance;
        directionY = dy / distance;

        cld = new Collider(this.x, this.y, ScreenSettings.sizeTile, ScreenSettings.sizeTile);
    }

    protected void journey(double deltaTime, double speed){
        x += deltaTime * speed * directionX;
        y +=  deltaTime * speed * directionY;

        Platform.runLater(() -> {
            imgView.setLayoutX(x);
            imgView.setLayoutY(y);
            cld.ret.setX(x);
            cld.ret.setY(y);

        });
    }

    public boolean isArrived(double xDest, double yDest){
        double dx = this.xDest - x;
        double dy = this.yDest - y;
        return Math.sqrt(dx * dx + dy * dy) <= margine;
    }

}
