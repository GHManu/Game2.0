package com.example.game;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Enemy extends ACharacterEnemy {

    public Enemy(){
        setGoingDown(true);

        setX( (IScreenSettings.screenWidth/2.0) + 150.0);
        setY((IScreenSettings.screenHeight/2.0) - 120.0);

        setProgressBar(new ProgressBar(1.0));
        setvBox(new VBox(getProgressBar()));

        getvBox().setSpacing(10);
        getvBox().setLayoutX(getX());
        getvBox().setLayoutY(getY() - 20);


        setDir_forward(false);
        setDir_backward(false);
        setDir_rightward(false);
        setDir_leftward(false);
        setDir_forward_oblq_right(false);
        setDir_forward_oblq_left(false);
        setDir_backward_oblq_right(false);
        setDir_backward_oblq_left(false);

         this.setHealth(this.getInitial_Health());
        setSpeed(1.5);
        setStrength(4);

        setImg( EGameImages.Front_Enemy_c.getImage());

        setImgView(new ImageView(getImg()));

        //imposto la grandezza dell'immagine
        getImgView().setFitWidth(IScreenSettings.sizeTile);
        getImgView().setFitHeight(IScreenSettings.sizeTile);

        getImgView().setLayoutX(getX());
        getImgView().setLayoutY(getY());

        setCld(new Collider(getX(), getY(), IScreenSettings.sizeTile, IScreenSettings.sizeTile));

        attack_flag = true;

    }

    public Enemy(AWeapon weapon){
        this();
        setWeapon(weapon);
    }
    @Override
    protected void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        this.getFightStrategy().normalAttack(deltatime, enemy, plr);

    }


    @Override
    public void update(AStatsObject statsObject) {

    }
}
