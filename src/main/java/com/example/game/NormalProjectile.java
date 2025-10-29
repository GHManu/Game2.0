package com.example.game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//circa 33 righe

public class NormalProjectile extends Projectile {


    public NormalProjectile(Image img, double x, double y, double xDest, double yDest) {

            this.imgView = new ImageView(img);
            this.imgView.setFitWidth(IScreenSettings.sizeTile);
            this.imgView.setFitHeight(IScreenSettings.sizeTile);

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

        cld = new Collider(this.x, this.y, IScreenSettings.sizeTile, IScreenSettings.sizeTile);
    }

    protected void journey(double deltaTime, double speed){
        System.out.println("Journey");
        x += deltaTime * speed * directionX;
        y +=  deltaTime * speed * directionY;

        Platform.runLater(() -> {
            imgView.setLayoutX(x);
            imgView.setLayoutY(y);
            cld.ret.setX(x);
            cld.ret.setY(y);

        });
    }



}
