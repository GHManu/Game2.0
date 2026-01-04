package com.example.game.Weapon.Ranged;

import com.example.game.Collider;
import com.example.game.UI.IScreenSettings;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class NormalAProjectile extends AProjectile {


    public NormalAProjectile(Image img, double x, double y, double xDest, double yDest) {
            setImgView(new ImageView(img));
            getImgView().setFitWidth(IScreenSettings.sizeTile);
            getImgView().setFitHeight(IScreenSettings.sizeTile);

            setX(x);
            setY(y);
            this.getImgView().setLayoutX(this.getX());
            this.getImgView().setLayoutY(this.getY());




        this.setxDest(xDest);
        this.setyDest(yDest);

        double dx = this.getxDest() - x;
        double dy = this.getyDest() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance != 0){
            this.setDirectionX(dx / distance);
            this.setDirectionY(dy / distance);
        }

        setCld( new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }

    protected void journey(double deltaTime, double speed){

        double x = getX(), y = getY();
        x += deltaTime * speed * this.getDirectionX();
        setX(x);
        y +=  deltaTime * speed * this.getDirectionY();
        setY(y);

        Platform.runLater(() -> {
            this.getImgView().setLayoutX(this.getX());
            this.getImgView().setLayoutY(this.getY());
            this.getCld().getShape().setX(this.getX());
            this.getCld().getShape().setY(this.getY());

        });

    }

}
