package com.example.game;

import javafx.scene.image.ImageView;

public class Grass extends ANonInterableObjects{
    public Grass(int pos_x, int pos_y){
        this.setX(pos_x * IScreenSettings.sizeTile);
        this.setY(pos_y * IScreenSettings.sizeTile);

        this.setImg(EGameImages.Grass.getImage());

        this.setImgView(new ImageView(getImg()));

        this.getImgView().setFitWidth(IScreenSettings.sizeTile);
        this.getImgView().setFitHeight(IScreenSettings.sizeTile);

        this.getImgView().setLayoutX(getX());
        this.getImgView().setLayoutY(getY());

        this.setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }
}
