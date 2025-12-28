package com.example.game;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;


import java.util.Set;


public class Player extends ACharacterPlayable {

    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0;
    protected static final double RECHARGE_TIME_DURATION = 400.0;
    protected boolean isSprinting;






    public Player(ISubject subject){

        this.setSubject(subject);
        this.getSubject().addObserver(this);

        setX( (IScreenSettings.screenWidth/2.0));
        setY((IScreenSettings.screenHeight/2.0));

        setProgressBar(new ProgressBar(1.0));
        setvBox(new VBox(getProgressBar()));

        getvBox().setSpacing(10);
        getvBox().setLayoutX(getX());
        getvBox().setLayoutY(getY() - 20);

        this.setxDest(0);
        this.setyDest(0);

        this.setInit_attack_flag(false);
        this.setAttack_flag(false);

        timeSprint = 0.0;
        timeReCharge = 0.0;
        isSprinting = false;


        setDir_forward(false);
        setDir_backward(false);
        setDir_rightward(false);
        setDir_leftward(false);
        setDir_forward_oblq_right(false);
        setDir_forward_oblq_left(false);
        setDir_backward_oblq_right(false);
        setDir_backward_oblq_left(false);

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


    public Player(ISubject subject, AWeapon weapon){
        this(subject);
        this.setWeapon(weapon);
    }


    @Override
    protected void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy) {
            this.getFightStrategy().normalAttack(deltatime, enemy, plr);
    }

    @Override
    protected void movement(double deltatime, Set<KeyCode> keyCodes) {
        ((IMovementStrategyWithInput)this.getMovementStrategy()).setKeysPressed(keyCodes);
        this.getMovementStrategy().movement(deltatime, this);
    }


    @Override
    public void update(AStatsObject statsObject) {
        this.setStatsObject(statsObject);
    }
}
