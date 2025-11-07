package com.example.game;

import javafx.application.Platform;

import java.util.ArrayList;



public class NormalPistol extends AFireWeapon {


    public NormalPistol(){
        this.setProjectiles(new ArrayList<Projectile>());
        this.getProjectiles().add(new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(),0,0, 0, 0));
    }

    @Override
    void shot(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy) {

        p.journey(deltaTime, p.speed);

    }


    //da togliere e mettere nell'altro behaviour
    @Override
    void movement(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy) {

        double maxDestY = IScreenSettings.screenHeight - IScreenSettings.sizeTile;
        double minDestY = 0; // oppure un valore di partenza

        if (enemy.goingDown) {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Front_Enemy_c.getImage());
            });
            enemy.y += enemy.speed * deltaTime;
            if (enemy.y >= maxDestY) {
                enemy.y = maxDestY;
                enemy.goingDown = false;
            }
        } else {
            Platform.runLater(() -> {
                enemy.changeImage(EGameImages.Back_Enemy_c.getImage());
            });
            enemy.y -= enemy.speed * deltaTime;
            if (enemy.y <= minDestY) {
                enemy.y = minDestY;
                enemy.goingDown = true;
            }
        }

        double finalY = enemy.y;
        Platform.runLater(() -> {
            enemy.imgView.setLayoutY(finalY);
            enemy.cld.ret.setY(finalY);
            enemy.vBox.setLayoutY(finalY - 20);
        });
    }

    @Override
    public void attack(double deltatime, ACharacterPlayable plr,ACharacterEnemy enemy) {

    }
}
