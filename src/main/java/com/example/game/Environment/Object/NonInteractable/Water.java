package com.example.game.Environment.Object.NonInteractable;

import com.example.game.Environment.Collider;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;
import javafx.scene.image.ImageView;

public class Water extends ANonInterableObjects {
    public Water(int pos_x, int pos_y){
        this.setX(pos_x * IScreenSettings.sizeTile);
        this.setY(pos_y * IScreenSettings.sizeTile);

        this.setImg(EGameImages.Water.getImage());

        this.setImgView(new ImageView(getImg()));

        this.getImgView().setFitWidth(IScreenSettings.sizeTile);
        this.getImgView().setFitHeight(IScreenSettings.sizeTile);

        this.getImgView().setLayoutX(getX());
        this.getImgView().setLayoutY(getY());

        this.setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }
}
