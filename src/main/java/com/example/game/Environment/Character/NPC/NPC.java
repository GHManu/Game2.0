package com.example.game.Environment.Character.NPC;

import com.example.game.Environment.Character.Movement.NoInput.OneWayMovement;
import com.example.game.Environment.Collider;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;
import javafx.scene.image.ImageView;

public class NPC extends ANPC{

    public NPC() {

        setX( (IScreenSettings.screenWidth/2.0) + 280.0);
        setY((IScreenSettings.screenHeight/2.0) - (-400));
        setImg( EGameImages.Front_Enemy_c.getImage());

        setImgView(new ImageView(getImg()));

        getImgView().setFitWidth(IScreenSettings.sizeTile);
        getImgView().setFitHeight(IScreenSettings.sizeTile);

        getImgView().setLayoutX(getX());
        getImgView().setLayoutY(getY());

        setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
        this.setMovementStrategy(new OneWayMovement());
    }

    public NPC(String filename) {
        this();
        this.setDialogues( DialogueLoader.loadAll(this.PATH + filename) );
    }

    public Dialogue getDialogue(String key){
        return this.getDialogues().get(key);
    }
}
