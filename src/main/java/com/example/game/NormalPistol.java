package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public class NormalPistol extends AFireWeapon {

    Projectile p;

    public Projectile getP() {
        return p;
    }

    public void setP(Projectile p) {
        this.p = p;
    }

    public NormalPistol(){

    }

    @Override
    void shot(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy) {
        if(enemy.progressBar.getProgress() > 0.1 )    movement(deltaTime, plr, p, enemy);

        if(enemy.progressBar.getProgress() > 0.1 && !enemy.attack_flag && plr.progressBar.getProgress() > 0.1) {  //finchè è in vita
            p.journey(deltaTime, p.speed);
            plr.cld.collision_Detected(p.cld.ret, false);

            if (p.isArrived(plr.x, plr.y)) {
                Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
                enemy.attack_flag = true;
            }
            if(p.cld.ret.intersects(plr.cld.ret.getBoundsInLocal())){

                if(plr.cld.dx && plr.x > 0){
                    Platform.runLater(() -> {
                        plr.imgView.setX(plr.imgView.getX() - (plr.speed * enemy.REBOUND));
                        plr.cld.ret.setLayoutX(plr.cld.ret.getLayoutX() - (plr.speed * enemy.REBOUND));
                        plr.progressBar.setTranslateX(plr.progressBar.getTranslateX() - (plr.speed * enemy.REBOUND * 0.4));
                        plr.vBox.setTranslateX(plr.vBox.getTranslateX() - (plr.speed * enemy.REBOUND * 0.4));
                    });
                }

                if(plr.cld.sx && plr.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)){
                    Platform.runLater(() -> {
                        plr.imgView.setX(plr.imgView.getX() + (plr.speed * enemy.REBOUND));
                        plr.cld.ret.setLayoutX(plr.cld.ret.getLayoutX() + (plr.speed * enemy.REBOUND));
                        plr.progressBar.setTranslateX(plr.progressBar.getTranslateX() + (plr.speed * enemy.REBOUND * 0.4));
                        plr.vBox.setTranslateX(plr.vBox.getTranslateX() + (plr.speed * enemy.REBOUND * 0.4));
                    });
                }

                if(plr.cld.br && plr.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)){


                    Platform.runLater(() -> {
                        plr.imgView.setY(plr.imgView.getY() + (plr.speed * enemy.REBOUND));
                        plr.cld.ret.setLayoutY(plr.cld.ret.getLayoutY() + (plr.speed * enemy.REBOUND));
                        plr.progressBar.setTranslateY(plr.progressBar.getTranslateY() + (plr.speed * enemy.REBOUND* 0.4));
                        plr.vBox.setTranslateY(plr.vBox.getTranslateY() + (plr.speed * enemy.REBOUND* 0.4));
                    });
                }
                if(plr.cld.fr && plr.y >0){
                    Platform.runLater(() -> {
                        plr.imgView.setY(plr.imgView.getY() - (plr.speed * enemy.REBOUND));
                        plr.cld.ret.setLayoutY(plr.cld.ret.getLayoutY() - (plr.speed * enemy.REBOUND));
                        plr.progressBar.setTranslateY(plr.progressBar.getTranslateY() - (plr.speed * enemy.REBOUND* 0.4));
                        plr.vBox.setTranslateY(plr.vBox.getTranslateY() - (plr.speed * enemy.REBOUND* 0.4));
                    });
                }

                Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
                Platform.runLater(() -> {  plr.progressBar.setProgress(plr.progressBar.getProgress() - 0.2); });
                enemy.attack_flag = true;
            }

        }


        if(enemy.progressBar.getProgress() < 0.1 && !p.isArrived(plr.x, plr.y)){
            Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
        }

    }

    @Override
    void movement(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy) {
        double maxDestY = IScreenSettings.screenHeight - IScreenSettings.sizeTile;
        double minDestY = 0; // oppure un valore di partenza

        if (enemy.goingDown) {
            Platform.runLater(() -> {
                enemy.changeImage("Images/Front_Enemy_c.png");
            });
            enemy.y += enemy.speed * deltaTime;
            if (enemy.y >= maxDestY) {
                enemy.y = maxDestY;
                enemy.goingDown = false;
            }
        } else {
            Platform.runLater(() -> {
                enemy.changeImage("Images/Back_Enemy_c.png");
            });
            enemy.y -= enemy.speed * deltaTime;
            if (enemy.y <= minDestY) {
                enemy.y = minDestY;
                enemy.goingDown = true;
            }
        }

        double finalY = this.y;
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
            p = new NormalProjectile(enemy.attackImage, this.x, this.y, plr.x, plr.y);
            Platform.runLater(() -> { plr.root.getChildren().addAll(p.cld.ret,p.imgView); });
            shot(deltatime, plr, p, enemy);
        }
    }
}
