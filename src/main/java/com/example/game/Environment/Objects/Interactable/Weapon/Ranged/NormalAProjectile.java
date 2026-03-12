package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import com.example.game.Environment.Collider;
import com.example.game.UI.IScreenSettings;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class NormalAProjectile extends AProjectile {


    public NormalAProjectile(Image img, double x, double y, double x_dest, double y_dest) {
            super(EProjectileType.NORMAL);
            setImg_view(new ImageView(img));
            getImg_view().setFitWidth(IScreenSettings.size_tile);
            getImg_view().setFitHeight(IScreenSettings.size_tile);

            setX(x);
            setY(y);
        Platform.runLater(() -> {
            this.getImg_view().setTranslateX(this.getX());
            this.getImg_view().setTranslateY(this.getY());
        });



        this.setX_dest(x_dest);
        this.setY_dest(y_dest);

        double dx = this.getX_dest() - x;
        double dy = this.getY_dest() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance != 0){
            this.setDirection_x(dx / distance);
            this.setDirection_y(dy / distance);
        }

        setCollider( new Collider(getX(), getY(), IScreenSettings.size_tile, IScreenSettings.size_tile));
    }



}
