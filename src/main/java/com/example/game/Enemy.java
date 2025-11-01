package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


//94 righe

public class Enemy extends ACharacterEnemy {

    public Projectile p;

    public Enemy(IFightStrategy fightStrategy, AWeapon aFireWeapon){
        super(fightStrategy);
        setWeapon(aFireWeapon);
        goingDown = true;

        this.x = (IScreenSettings.screenWidth/2.0) + 150.0;
        this.y = (IScreenSettings.screenHeight/2.0) - 120.0;

        progressBar = new ProgressBar(1.0);   //1 = 100%, 0.5 = 50%
        vBox = new VBox(progressBar);
        vBox.setSpacing(10);
        vBox.setLayoutX(this.x);
        vBox.setLayoutY(this.y - 20);


        dir_forward = false;
        dir_backward = false;
        dir_rightward = false;
        dir_leftward = false;
        dir_forward_oblq_right = false;
        dir_forward_oblq_left = false;
        dir_backward_oblq_right = false;
        dir_backward_oblq_left = false;

        this.health = this.initial_Health;
        speed = 1.5;
        strength = 4;

        img = EGameImages.Front_Enemy_c.getImage();
        
        imgView = new ImageView(img);

        //imposto la grandezza dell'immagine
        imgView.setFitWidth(IScreenSettings.sizeTile);
        imgView.setFitHeight(IScreenSettings.sizeTile);

        imgView.setLayoutX(x);
        imgView.setLayoutY(y);

        cld = new Collider(x, y, IScreenSettings.sizeTile, IScreenSettings.sizeTile);

        attack_flag = true;

    }

    protected void attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        this.getFightStrategy().attack(deltatime, plr, this);
    }

}
