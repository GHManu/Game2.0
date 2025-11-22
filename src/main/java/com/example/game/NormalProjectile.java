package com.example.game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class NormalProjectile extends Projectile {


    public NormalProjectile(Image img, double x, double y, double xDest, double yDest) {
            setImgView(new ImageView(img));
            getImgView().setFitWidth(IScreenSettings.sizeTile);
            getImgView().setFitHeight(IScreenSettings.sizeTile);

            setX(x);
            setY(y);
            this.getImgView().setLayoutX(this.getX());
            this.getImgView().setLayoutY(this.getY());




        this.xDest = xDest;
        this.yDest = yDest;

        double dx = this.xDest - x;
        double dy = this.yDest - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance != 0){
            directionX = dx / distance;
            directionY = dy / distance;
        }

        setCld( new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }

    protected void journey(double deltaTime, double speed){

        double x = getX(), y = getY();
        x += deltaTime * speed * directionX;
        setX(x);
        y +=  deltaTime * speed * directionY;
        setY(y);

        Platform.runLater(() -> {
            this.getImgView().setLayoutX(this.getX());
            this.getImgView().setLayoutY(this.getY());
            this.getCld().ret.setX(this.getX());
            this.getCld().ret.setY(this.getY());

        });

    }



}
