package com.example.game;

import javafx.scene.image.Image;

import java.util.Objects;

public enum EGameImages {


    Back_Enemy_c("Images/Back_Enemy_c.png"),
    Back_Pg("Images/Back_Pg.png"),
    Front_Enemy_c("Images/Front_Enemy_c.png"),
    Front_Pg("Images/Front_Pg.png"),
    Left_Side_Enemy_c("Images/Left_Enemy_c.png"),
    Left_Side_Pg("Images/Left_Side_Pg.png"),
    Right_Side_Enemy_c("Images/Right_Enemy_c.png"),
    Right_Side_Pg("Images/Right_Side_Pg.png"),
    ProvaAttacco("Images/ProvaAttacco.png"),
    ProvaAttaccoEnemy("Images/ProvaAttaccoEnemy.png"),
    Grass("Images/grass.png"),
    Water("Images/Water.png"),
    Wall("Images/wall.png");

    private final Image image;
    private final String path;

    private EGameImages(String path){
        this.path = path;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)),IScreenSettings.sizeTile, // requestedWidth
                IScreenSettings.sizeTile, // requestedHeight
                false, false);
    }

    public Image getImage() {
        return image;
    }
    public String getPath() {
        return path;
    }
}
