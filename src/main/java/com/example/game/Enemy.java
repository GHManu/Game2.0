package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


//94 righe

public class Enemy extends ACharacterEnemy {


    public Enemy(IFightStrategy fightStrategy){
        super(fightStrategy);
        goingDown = true;

        this.x = (IScreenSettings.screenWidth/2.0) + 100.0;
        this.y = (IScreenSettings.screenHeight/2.0) - 100.0;

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

        img = new Image(getClass().getResourceAsStream("Images/Front_Enemy_c.png"), IScreenSettings.sizeTile, // requestedWidth
                IScreenSettings.sizeTile, // requestedHeight
                false, false);  //preserveRatio = false e disattivando il smoothing a livello di Image
        //Questo dice a JavaFX: "Scala esattamente a 48x48, non interpolare, non mantenere le proporzioni"
        imgView = new ImageView(img);

        //imposto la grandezza dell'immagine
        imgView.setFitWidth(IScreenSettings.sizeTile);
        imgView.setFitHeight(IScreenSettings.sizeTile);

        imgView.setLayoutX(x);
        imgView.setLayoutY(y);

        cld = new Collider(x, y, IScreenSettings.sizeTile, IScreenSettings.sizeTile);

        attackImage = new Image(getClass().getResourceAsStream("Images/ProvaAttaccoEnemy.png"), IScreenSettings.sizeTile, // requestedWidth
                IScreenSettings.sizeTile, // requestedHeight
                false, false);

        attack_flag = true;

    }


//    protected void attack(double deltatime, Player plr){
//        if(attack_flag && plr.progressBar.getProgress() > 0.1 && this.progressBar.getProgress() > 0.1){
//            attack_flag = false;
//            NormalProjectile p = new NormalProjectile(attackImage, this.x, this.y, plr.x, plr.y);
//            Platform.runLater(() -> { plr.root.getChildren().addAll(p.cld.ret,p.imgView); });
//            this.p = p;
//            shot(deltatime, plr, this.p);
//        }
//    }

    protected void attack(double deltatime, Player plr){
        this.getFightStrategy().attack(deltatime, plr, this.attack_flag, this.progressBar, this.attackImage);
    }


    protected void shot(double deltaTime, Player plr, Projectile p){

        if(this.progressBar.getProgress() > 0.1 )    movement(deltaTime);

        if(this.progressBar.getProgress() > 0.1 && !attack_flag && plr.progressBar.getProgress() > 0.1) {  //finchè è in vita
            p.journey(deltaTime, p.speed);
            plr.cld.collision_Detected(p.cld.ret, false);

            if (p.isArrived(plr.x, plr.y)) {
                Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
                attack_flag = true;
            }
            if(p.cld.ret.intersects(plr.cld.ret.getBoundsInLocal())){

                if(plr.cld.dx && plr.x > 0){
                    Platform.runLater(() -> {
                        plr.imgView.setX(plr.imgView.getX() - (plr.speed * this.REBOUND));
                        plr.cld.ret.setLayoutX(plr.cld.ret.getLayoutX() - (plr.speed * this.REBOUND));
                        plr.progressBar.setTranslateX(plr.progressBar.getTranslateX() - (plr.speed * this.REBOUND * 0.4));
                        plr.vBox.setTranslateX(plr.vBox.getTranslateX() - (plr.speed * this.REBOUND * 0.4));
                    });
                }

                if(plr.cld.sx && plr.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)){
                    Platform.runLater(() -> {
                        plr.imgView.setX(plr.imgView.getX() + (plr.speed * this.REBOUND));
                        plr.cld.ret.setLayoutX(plr.cld.ret.getLayoutX() + (plr.speed * this.REBOUND));
                        plr.progressBar.setTranslateX(plr.progressBar.getTranslateX() + (plr.speed * this.REBOUND * 0.4));
                        plr.vBox.setTranslateX(plr.vBox.getTranslateX() + (plr.speed * this.REBOUND * 0.4));
                    });
                }

                if(plr.cld.br && plr.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)){


                    Platform.runLater(() -> {
                        plr.imgView.setY(plr.imgView.getY() + (plr.speed * this.REBOUND));
                        plr.cld.ret.setLayoutY(plr.cld.ret.getLayoutY() + (plr.speed * this.REBOUND));
                        plr.progressBar.setTranslateY(plr.progressBar.getTranslateY() + (plr.speed * this.REBOUND* 0.4));
                        plr.vBox.setTranslateY(plr.vBox.getTranslateY() + (plr.speed * this.REBOUND* 0.4));
                    });
                }
                if(plr.cld.fr && plr.y >0){
                    Platform.runLater(() -> {
                        plr.imgView.setY(plr.imgView.getY() - (plr.speed * this.REBOUND));
                        plr.cld.ret.setLayoutY(plr.cld.ret.getLayoutY() - (plr.speed * this.REBOUND));
                        plr.progressBar.setTranslateY(plr.progressBar.getTranslateY() - (plr.speed * this.REBOUND* 0.4));
                        plr.vBox.setTranslateY(plr.vBox.getTranslateY() - (plr.speed * this.REBOUND* 0.4));
                    });
                }

                Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
                Platform.runLater(() -> {  plr.progressBar.setProgress(plr.progressBar.getProgress() - 0.2); });
                attack_flag = true;
            }

        }


        if(this.progressBar.getProgress() < 0.1 && !p.isArrived(plr.x, plr.y)){
            Platform.runLater(() -> { plr.root.getChildren().removeAll(p.cld.ret, p.imgView); } );
        }



    }

    public void movement(double deltaTime){
        double maxDestY = IScreenSettings.screenHeight - IScreenSettings.sizeTile;
        double minDestY = 0; // oppure un valore di partenza

        if (goingDown) {
            Platform.runLater(() -> {
                this.changeImage("Images/Front_Enemy_c.png");
            });
            this.y += this.speed * deltaTime;
            if (this.y >= maxDestY) {
                this.y = maxDestY;
                goingDown = false;
            }
        } else {
            Platform.runLater(() -> {
                this.changeImage("Images/Back_Enemy_c.png");
            });
            this.y -= this.speed * deltaTime;
            if (this.y <= minDestY) {
                this.y = minDestY;
                goingDown = true;
            }
        }

        double finalY = this.y;
        Platform.runLater(() -> {
            this.imgView.setLayoutY(finalY);
            this.cld.ret.setY(finalY);
            this.vBox.setLayoutY(finalY - 20);
        });
    }


}
