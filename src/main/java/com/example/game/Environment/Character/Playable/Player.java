package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Collider;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class Player extends ACharacterPlayable {

    public Player(){

        setX( (IScreenSettings.screenWidth/2.0) + 250);
        setY((IScreenSettings.screenHeight/2.0) + 120);

        setProgress_bar(new ProgressBar(1.0));
        setVbox(new VBox(getProgress_bar()));

        getVbox().setSpacing(10);
        getVbox().setLayoutX(getX());
        getVbox().setLayoutY(getY() - 20);

        this.setX_dest(0);
        this.setY_dest(0);

        this.setInit_attack_flag(false);
        this.setAttack_flag(false);


        this.setHealth(this.getInitial_Health());
        setSpeed(2.5);
        setStrength(2.46793);

        setImg( EGameImages.Front_Pg.getImage());

        setImg_view(new ImageView(getImg()));

        getImg_view().setFitWidth(IScreenSettings.size_tile);
        getImg_view().setFitHeight(IScreenSettings.size_tile);

        getImg_view().setLayoutX(getX());
        getImg_view().setLayoutY(getY());

        setCollider(new Collider(getX(), getY(), IScreenSettings.size_tile, IScreenSettings.size_tile));
    }


    public Player(AWeapon weapon){
        this();
        this.setWeapon(weapon);
    }


    @Override
    public void selectAttack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy, List<ACharacter> characters) {
            this.getFight_strategy_player().normalAttack(deltatime, plr,characters);
    }

    @Override
    public void movement(double deltatime, List<ACharacter> characters) {
        this.getMovement_strategy().movement(deltatime, this, characters);
    }

}
