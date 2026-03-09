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

        setProgressBar(new ProgressBar(1.0));
        setvBox(new VBox(getProgressBar()));

        getvBox().setSpacing(10);
        getvBox().setLayoutX(getX());
        getvBox().setLayoutY(getY() - 20);

        this.setxDest(0);
        this.setyDest(0);

        this.setInit_attack_flag(false);
        this.setAttack_flag(false);


        this.setHealth(this.getInitial_Health());
        setSpeed(2.5);
        setStrength(2.46793);

        setImg( EGameImages.Front_Pg.getImage());

        setImgView(new ImageView(getImg()));

        getImgView().setFitWidth(IScreenSettings.sizeTile);
        getImgView().setFitHeight(IScreenSettings.sizeTile);

        getImgView().setLayoutX(getX());
        getImgView().setLayoutY(getY());

        setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));
    }


    public Player(AWeapon weapon){
        this();
        this.setWeapon(weapon);
    }


    @Override
    public void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy, List<ACharacter> characters) {
            this.getFightStrategyPlayer().normalAttack(deltatime, plr,characters);
    }

    @Override
    public void movement(double deltatime, List<ACharacter> characters) {
        this.getMovementStrategy().movement(deltatime, this, characters);
    }

}
