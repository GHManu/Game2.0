package com.example.game;

import javafx.application.Platform;

import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class NormalPistol extends AFireWeapon {


    public NormalPistol(){

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
        if(enemy.attack_flag && plr.progressBar.getProgress() > 0.1 && enemy.progressBar.getProgress() > 0.1){

            enemy.attack_flag = false;
            Projectile p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), enemy.x, enemy.y, plr.x, plr.y);
            Platform.runLater(() -> { plr.root.getChildren().addAll(p.cld.ret, p.imgView); });

            if(enemy instanceof Enemy){
               ((Enemy)enemy).p = p;
                shot(deltatime, plr, ((Enemy)enemy).p , enemy);
            }

       }
    }
}
