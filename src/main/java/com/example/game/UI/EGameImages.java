package com.example.game.UI;

import javafx.scene.image.Image;

import java.util.Objects;

public enum EGameImages {


    Back_Enemy_c("/com/example/game/Images/Back_Enemy_c.png"),
    Back_Pg("/com/example/game/Images/Back_Pg.png"),
    Front_Enemy_c("/com/example/game/Images/Front_Enemy_c.png"),
    Front_Pg("/com/example/game/Images/Front_Pg.png"),
    Left_Side_Enemy_c("/com/example/game/Images/Left_Enemy_c.png"),
    Left_Side_Pg("/com/example/game/Images/Left_Side_Pg.png"),
    Right_Side_Enemy_c("/com/example/game/Images/Right_Enemy_c.png"),
    Right_Side_Pg("/com/example/game/Images/Right_Side_Pg.png"),
    ProvaAttacco("/com/example/game/Images/ProvaAttacco.png"),
    ProvaAttaccoEnemy("/com/example/game/Images/ProvaAttaccoEnemy.png"),
    Grass("/com/example/game/Images/grass.png"),
    Water("/com/example/game/Images/Water.png"),
    Wall("/com/example/game/Images/wall.png");

    private final Image image;
    private final String path;

    private EGameImages(String path){
        this.path = path;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)), IScreenSettings.sizeTile, // requestedWidth
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
