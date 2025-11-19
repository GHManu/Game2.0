package com.example.game;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


//94 righe

public class Enemy extends ACharacterEnemy {

    public Enemy(){
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

    public Enemy(AWeapon weapon){
        setWeapon(weapon);

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
    @Override
    protected void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        if(this.getWeapon() instanceof AFireWeapon){
            this.initialize_attack(deltatime, plr, enemy);
            this.shot(deltatime,plr,enemy);
        }
    }

    protected void initialize_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        if(enemy.attack_flag && plr.progressBar.getProgress() > 0.1 && enemy.progressBar.getProgress() > 0.1) {

            this.attack_flag = false;

            if(this.getWeapon() instanceof AFireWeapon){
                Projectile p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), enemy.x, enemy.y, plr.x, plr.y);
                Platform.runLater(() -> {
                    plr.root.getChildren().addAll(p.cld.ret, p.imgView);
                });
                ((AFireWeapon) this.getWeapon()).getMag().set(0, p);
            }
        }
    }


    protected void shot(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy){
        AFireWeapon fireWeapon = ((AFireWeapon)this.getWeapon());

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
        Collider projcld = fireWeapon.p.cld;
        Rectangle projret = projcld.ret;


        if(enemyprogbar.getProgress() > 0.1 && plrprogbar.getProgress() > 0.1)    this.getMovementStrategyWithoutInput().movement(deltatime, plr, enemy);

        if(enemyprogbar.getProgress() > 0.1 && !enemy.attack_flag && plrprogbar.getProgress() > 0.1) {  //finchè è in vita

            fireWeapon.p =  fireWeapon.getMag().getFirst();
            this.getWeapon().fight(deltatime);

            plrcld.collision_Detected(projret, false);

            if (fireWeapon.p.isArrived(plr.x, plr.y)) {
                Platform.runLater(() -> { plrrootchildren.removeAll(projret, fireWeapon.p.imgView); } );
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

                Platform.runLater(() -> { plrrootchildren.removeAll(projret, fireWeapon.p.imgView); } );
                Platform.runLater(() -> {  plrprogbar.setProgress(plrprogbar.getProgress() - 0.2); });
                enemy.attack_flag = true;
            }

        }


        if(enemyprogbar.getProgress() < 0.1 && !fireWeapon.p.isArrived(plr.x, plr.y)){
            Platform.runLater(() -> { plrrootchildren.removeAll(projret, fireWeapon.p.imgView); } );
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


}
