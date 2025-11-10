package com.example.game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Iterator;

//circa 108 righe

public class PlayerFireWeaponType extends ACharacterPlayable {
    //per lo sprint
    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0; // durata dello sprint
    protected static final double RECHARGE_TIME_DURATION = 400.0; // tempo di ricarica
    protected boolean isSprinting;

    //attacco
    boolean attack_flag;
    double xDest, yDest;

    private EnemyFireWeaponType enemyFireWeaponType;

    public PlayerFireWeaponType(AFireWeapon weapon){
        this.setWeapon(weapon);
        x = IScreenSettings.screenWidth/2.0;
        y = IScreenSettings.screenHeight/2.0;


        progressBar = new ProgressBar(1.0);   //1 = 100%, 0.5 = 50%
        vBox = new VBox(progressBar);
        vBox.setSpacing(10);
        vBox.setLayoutX(this.x);
        vBox.setLayoutY(this.y - 20);


        attack_flag = false;

        xDest = 0;
        yDest = 0;

        timeSprint = 0.0;
        timeReCharge = 0.0;
        isSprinting = false;


        dir_forward = false;
        dir_backward = false;
        dir_rightward = false;
        dir_leftward = false;
        dir_forward_oblq_right = false;
        dir_forward_oblq_left = false;
        dir_backward_oblq_right = false;
        dir_backward_oblq_left = false;

        speed = 2.5;
        strength = 2.46793;

        img = EGameImages.Front_Pg.getImage();
        imgView = new ImageView(img);

        //imposto la grandezza dell'immagine
        imgView.setFitWidth(IScreenSettings.sizeTile);
        imgView.setFitHeight(IScreenSettings.sizeTile);



        imgView.setLayoutX(x);
        imgView.setLayoutY(y);


        cld = new Collider(x, y, IScreenSettings.sizeTile, IScreenSettings.sizeTile);
    }


    //implementazione metodi movimento
    @Override
    protected void moveUp(double deltaTime) {
        y -= speed * deltaTime ;
        //playerView.setY(y);
        Platform.runLater(() -> { imgView.setLayoutY(y); cld.ret.setY(y);   vBox.setLayoutY(y-20);});
    }

    @Override
    protected void moveDown(double deltaTime) {
        y += speed * deltaTime ;
       // playerView.setY(y);
        Platform.runLater(() -> {
            imgView.setLayoutY(y); cld.ret.setY(y); vBox.setLayoutY(y-20);});
    }

    @Override
    protected void moveLeft(double deltaTime) {
        x -= speed * deltaTime ;
        //playerView.setX(x);
        Platform.runLater(() -> {
            imgView.setLayoutX(x); cld.ret.setX(x); vBox.setLayoutX(x);});
    }

    @Override
    protected void moveRight(double deltaTime) {
        x += speed * deltaTime;
        //playerView.setX(x);
        Platform.runLater(() -> {
            imgView.setLayoutX(x); cld.ret.setX(x); vBox.setLayoutX(x);});
    }

    @Override
    protected void sprintStatus(double deltatime){
        if (!isSprinting && this.timeReCharge <= 0) {
            // Se non stiamo sprintando e il tempo di ricarica è finito
            isSprinting = true; //si attiva sprint
            this.timeSprint = SPRINT_TIME_DURATION;
            System.out.println("Sprint attivato! Durata: " + SPRINT_TIME_DURATION + " secondi.");
        } else if (isSprinting) {
            System.out.println("Già sprintando!");
        } else if (this.timeReCharge > 0) {
            System.out.println("Non puoi sprintare, il tempo di ricarica non è finito.");
        }

    }

    protected void sprint(double deltatime){
        if (this.isSprinting) {
            this.speed = 4;
            this.timeSprint -= deltatime;  // Diminuisci il tempo dello sprint
            System.out.println(this.timeSprint);
            if (this.timeSprint <= 0) {
                this.walk(deltatime);
                this.isSprinting = false;
                this.timeReCharge = PlayerFireWeaponType.RECHARGE_TIME_DURATION;  // Inizia la ricarica
                System.out.println("Sprint finito! Inizia la ricarica.");
            }
        }
    }


    @Override
    protected void walk(double deltaTime){
        speed = 2.5;
        // Se il tasto di sprint non è premuto, gestisci la ricarica
        if (this.timeReCharge > 0) {
            this.timeReCharge -= deltaTime;
            System.out.println("Sto ricaricando");
            if (this.timeReCharge <= 0) {
                System.out.println("Ricarica completa. Puoi sprintare di nuovo!");
            }
        }
    }

    @Override
    protected void normal_attack(double deltatime) {
        if(this.progressBar.getProgress() > 5.551115123125783E-17) {
            AFireWeapon fireWeapon = (AFireWeapon) this.getWeapon();
            NormalProjectile p = new NormalProjectile(EGameImages.ProvaAttacco.getImage(), imgView.getLayoutX(), imgView.getLayoutY(), xDest, yDest);

            this.attack_flag = true;

            fireWeapon.getProjectiles().add(p);
            Platform.runLater(() -> {
                root.getChildren().addAll(p.cld.ret, p.imgView);
            });

        }
    }

    protected void setDestinationAttack(double xDest, double yDest){
        this.xDest = xDest;
        this.yDest = yDest;
    }

    protected void setRoot(Group root){
        this.root = root;
    }

    protected void setEnemy(EnemyFireWeaponType enemyFireWeaponType){this.enemyFireWeaponType = enemyFireWeaponType;}

    protected void shot(double deltaTime){

        AFireWeapon fireWeapon = (AFireWeapon) this.getWeapon();
        Iterator<Projectile> iterator = fireWeapon.getProjectiles().iterator();
        while(iterator.hasNext()){
            NormalProjectile p = (NormalProjectile) iterator.next();
            fireWeapon.p = p;
            this.getWeapon().fight(deltaTime);
            //fireWeapon.shot(deltaTime,p,this, enemy);

            if(p.isArrived(xDest, yDest)){
                Platform.runLater(() -> {
                    root.getChildren().removeAll(p.cld.ret, p.imgView);
                });

                iterator.remove();

            }else if ((enemyFireWeaponType != null) && (enemyFireWeaponType.cld.ret != null) ) {
                if ( p.cld.ret.intersects(enemyFireWeaponType.cld.ret.getBoundsInLocal())) {  //se colpisce il nemico
                    if (enemyFireWeaponType.health > 1) {  //perchè non so il perchè non va negativo e si ferma a circa 1e-13
                        enemyFireWeaponType.speed += 0.2;
                        enemyFireWeaponType.health -= (enemyFireWeaponType.initial_Health * p.normal_damage);
                        //System.out.println(enemy.health);
                        Platform.runLater(() -> {
                            enemyFireWeaponType.progressBar.setProgress(enemyFireWeaponType.progressBar.getProgress() - p.normal_damage);
                        });
                    }

                    Platform.runLater(() -> {
                        root.getChildren().removeAll(p.cld.ret, p.imgView);
                    });

                    iterator.remove();
//                    enemy.p.cld.ret.getBoundsInLocal())
                    //DA MODIFICARE
                } else if(p.cld.ret.intersects( ((AFireWeapon) enemyFireWeaponType.getWeapon()).getProjectiles().getFirst().cld.ret.getBoundsInLocal() ) ){    // se colpisce il proiettile del nemico
                    Platform.runLater(() -> {
                        root.getChildren().removeAll(p.cld.ret, p.imgView);
                    });

                    iterator.remove();
                }
            }
        }

        if(fireWeapon.getProjectiles().isEmpty())   attack_flag = false;    //così ne posso sparare di più


    }

}
