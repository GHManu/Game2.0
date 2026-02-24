package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Collider;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.UI.EGameImages;
import com.example.game.UI.IScreenSettings;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Enemy extends ACharacterEnemy {

    public Enemy(){
        this.setDirection(Direction.DOWN);

        setX( (IScreenSettings.screenWidth/2.0) + 480.0);
        setY((IScreenSettings.screenHeight/2.0) - 0.25);

        setProgressBar(new ProgressBar(1.0));
        setvBox(new VBox(getProgressBar()));

        getvBox().setSpacing(10);
        getvBox().setLayoutX(getX());
        getvBox().setLayoutY(getY() - 20);

         this.setHealth(this.getInitial_Health());
        setSpeed(1.5);
        setStrength(4);

        setImg( EGameImages.Front_Enemy_c.getImage());

        setImgView(new ImageView(getImg()));


        getImgView().setFitWidth(IScreenSettings.sizeTile);
        getImgView().setFitHeight(IScreenSettings.sizeTile);

        getImgView().setLayoutX(getX());
        getImgView().setLayoutY(getY());

        setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));

        attack_flag = true;

    }

    public Enemy( AWeapon weapon){
        this();
        setWeapon(weapon);
    }
    @Override
    public void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        this.getFightStrategyEnemy().normalAttack(deltatime, enemy,plr);

    }

}
