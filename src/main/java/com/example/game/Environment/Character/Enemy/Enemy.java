package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Collider;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Enemy extends ACharacterEnemy {

    public Enemy(){
        this.setDirection(Direction.DOWN);

        setX( (IScreenSettings.screenWidth/2.0) + 480.0);
        setY((IScreenSettings.screenHeight/2.0) - 0.25);

        setProgress_bar(new ProgressBar(1.0));
        setVbox(new VBox(getProgress_bar()));

        getVbox().setSpacing(10);
        getVbox().setLayoutX(getX());
        getVbox().setLayoutY(getY() - 20);

         this.setHealth(this.getInitial_Health());
        setSpeed(1.5);
        setStrength(4);

        setImg( EGameImages.Front_Enemy_c.getImage());

        setImg_view(new ImageView(getImg()));


        getImg_view().setFitWidth(IScreenSettings.size_tile);
        getImg_view().setFitHeight(IScreenSettings.size_tile);

        getImg_view().setLayoutX(getX());
        getImg_view().setLayoutY(getY());

        setCollider(new Collider(getX(), getY(), IScreenSettings.size_tile, IScreenSettings.size_tile));

        attack_flag = true;

    }

    public Enemy( AWeapon weapon){
        this();
        setWeapon(weapon);
    }
    @Override
    public void selectAttack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        this.getFight_strategy_enemy().normalAttack(deltatime, enemy,plr);

    }

}
