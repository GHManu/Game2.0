package com.example.game.Environment.Objects.NonInteractable;

import com.example.game.Environment.Collider;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;
import javafx.scene.image.ImageView;

public class Grass extends ANonInterableObjects {
    public Grass(int pos_x, int pos_y){
        this.setX(pos_x * IScreenSettings.size_tile);
        this.setY(pos_y * IScreenSettings.size_tile);

        this.setImg(EGameImages.Grass.getImage());

        this.setImg_view(new ImageView(getImg()));

        this.getImg_view().setFitWidth(IScreenSettings.size_tile);
        this.getImg_view().setFitHeight(IScreenSettings.size_tile);

        this.getImg_view().setLayoutX(getX());
        this.getImg_view().setLayoutY(getY());

        this.setCollider(new Collider(getX(), getY(), IScreenSettings.size_tile, IScreenSettings.size_tile));
    }
}
