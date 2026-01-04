package com.example.game.Environment.NonInteractable;

import com.example.game.Collider;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;
import javafx.scene.image.ImageView;

public class Wall extends ANonInterableObjects {

    public Wall(int pos_x, int pos_y){
        this.setX(pos_x * IScreenSettings.sizeTile);
        this.setY(pos_y * IScreenSettings.sizeTile);

        this.setImg(EGameImages.Wall.getImage());

        this.setImgView(new ImageView(getImg()));

        this.getImgView().setFitWidth(IScreenSettings.sizeTile);
        this.getImgView().setFitHeight(IScreenSettings.sizeTile);

        this.getImgView().setLayoutX(getX());
        this.getImgView().setLayoutY(getY());

        this.setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }
}
