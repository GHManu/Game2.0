package com.example.game;

import javafx.application.Platform;

import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class NormalPistol extends AFireWeapon {


    public NormalPistol(){

    }

    @Override
    void shot(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy) {

        //Player
        Collider plrcld = plr.cld;
        Rectangle ret = plrcld.ret;
        ImageView plrimgview = plr.imgView;
        ProgressBar plrprogbar = plr.progressBar;
        VBox plrvbox = plr.vBox;
        ObservableList<Node> plrrootchildren = plr.root.getChildren();

        //Enemy
        Collider enemycld = enemy.cld;
        ImageView enemyimgview = enemy.imgView;
        ProgressBar enemyprogbar = enemy.progressBar;

        //Projectile
        Collider projcld = p.cld;
        Rectangle projret = projcld.ret;


        if(enemyprogbar.getProgress() > 0.1 )    movement(deltaTime, plr, p, enemy);

        if(enemyprogbar.getProgress() > 0.1 && !enemy.attack_flag && plrprogbar.getProgress() > 0.1) {  //finchè è in vita

            p.journey(deltaTime, p.speed);
            plrcld.collision_Detected(projret, false);

            if (p.isArrived(plr.x, plr.y)) {
                Platform.runLater(() -> { plrrootchildren.removeAll(projret, p.imgView); } );
                enemy.attack_flag = true;
            }
            if(projret.intersects(ret.getBoundsInLocal())){

                if(plrcld.dx && plr.x > 0){
                    setPlrCollisionX(plrimgview, ret, plrprogbar, plrvbox, -plr.speed, enemy.REBOUND);
                }

                if(plrcld.sx && plr.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)){
                    setPlrCollisionX(plrimgview, ret, plrprogbar, plrvbox, plr.speed, enemy.REBOUND);
                }

                if(plrcld.br && plr.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)){
                    setPlrCollisionY(plrimgview, ret, plrprogbar, plrvbox, plr.speed, enemy.REBOUND);
                }
                if(plrcld.fr && plr.y >0){
                    setPlrCollisionY(plrimgview, ret, plrprogbar, plrvbox, -plr.speed, enemy.REBOUND);
                }

                Platform.runLater(() -> { plrrootchildren.removeAll(projret, p.imgView); } );
                Platform.runLater(() -> {  plrprogbar.setProgress(plrprogbar.getProgress() - 0.2); });
                enemy.attack_flag = true;
            }

        }


        if(enemyprogbar.getProgress() < 0.1 && !p.isArrived(plr.x, plr.y)){
            Platform.runLater(() -> { plrrootchildren.removeAll(projret, p.imgView); } );
        }

    }

    private void setPlrCollisionY(ImageView imageView, Rectangle ret, ProgressBar progbar, VBox vBox, double speed, double enemyrebound){
        Platform.runLater(() -> {
            imageView.setY(imageView.getY() + (speed * enemyrebound));
            ret.setLayoutY(ret.getLayoutY() - (speed * enemyrebound));
            progbar.setTranslateY(progbar.getTranslateY() + (speed * enemyrebound* 0.4));
            vBox.setTranslateY(vBox.getTranslateY() + (speed * enemyrebound* 0.4));
        });
    }
    private void setPlrCollisionX(ImageView imageView, Rectangle ret, ProgressBar progbar, VBox vBox, double speed, double enemyrebound){
        Platform.runLater(() -> {
            imageView.setX(imageView.getX() + (speed * enemyrebound));
            ret.setLayoutX(ret.getLayoutX() - (speed * enemyrebound));
            progbar.setTranslateX(progbar.getTranslateX() + (speed * enemyrebound* 0.4));
            vBox.setTranslateX(vBox.getTranslateX() + (speed * enemyrebound* 0.4));
        });
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
            Projectile p = new NormalProjectile(new Image(getClass().getResourceAsStream("Images/ProvaAttaccoEnemy.png")), enemy.x, enemy.y, plr.x, plr.y);
            Platform.runLater(() -> { plr.root.getChildren().addAll(p.cld.ret, p.imgView); });

            if(enemy instanceof Enemy){
                ((Enemy)enemy).p = p;
                shot(deltatime, plr, ((Enemy)enemy).p , enemy);
            }

        }
    }
}
