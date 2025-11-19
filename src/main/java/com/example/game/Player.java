package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Iterator;

//circa 108 righe
public class Player extends ACharacterPlayable {
    //per lo sprint
    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0; // durata dello sprint
    protected static final double RECHARGE_TIME_DURATION = 400.0; // tempo di ricarica
    protected boolean isSprinting;

    //attacco
    boolean attack_flag;
    double xDest, yDest;

    private ACharacterEnemy enemy;

    public Player(){
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


    public Player(AWeapon weapon){
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
                this.timeReCharge = Player.RECHARGE_TIME_DURATION;  // Inizia la ricarica
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
    protected void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy) {
        if(this.getWeapon() instanceof AFireWeapon fireWeapon) {
            this.shot(deltatime);
        }
    }


    protected void normal_attack(double deltatime) {
        if(this.progressBar.getProgress() > 5.551115123125783E-17) {
            AFireWeapon fireWeapon = (AFireWeapon) this.getWeapon();
            NormalProjectile p = new NormalProjectile(EGameImages.ProvaAttacco.getImage(), imgView.getLayoutX(), imgView.getLayoutY(), xDest, yDest);

            this.attack_flag = true;

            fireWeapon.getMag().add(p);
            Platform.runLater(() -> {
                root.getChildren().addAll(p.cld.ret, p.imgView);
            });
        }
    }

    protected void setDestinationAttack(double xDest, double yDest){
        this.xDest = xDest;
        this.yDest = yDest;
    }


    protected void setEnemy(ACharacterEnemy enemy){this.enemy = enemy;}

    private void shot(double deltaTime){

        AFireWeapon fireWeapon = (AFireWeapon) this.getWeapon();
        ProjectileIterator projectileIterator = new ProjectileIterator(fireWeapon.getMag());
        //Iterator<Projectile> iterator = fireWeapon.getMag().iterator();
        while(projectileIterator.hasNext()){
            NormalProjectile p = (NormalProjectile) projectileIterator.next();
            fireWeapon.p = p;
            this.getWeapon().fight(deltaTime);
            //fireWeapon.shot(deltaTime,p,this, enemy);

            if(p.isArrived(xDest, yDest)){
                Platform.runLater(() -> {
                    root.getChildren().removeAll(p.cld.ret, p.imgView);
                });

                projectileIterator.remove();

            }else if ((enemy != null) && (enemy.cld.ret != null) ) {
                if ( p.cld.ret.intersects(enemy.cld.ret.getBoundsInLocal())) {  //se colpisce il nemico
                    if (enemy.health > 1) {  //perchè non so il perchè non va negativo e si ferma a circa 1e-13
                        enemy.speed += 0.2;
                        enemy.health -= (enemy.initial_Health * p.normal_damage);
                        //System.out.println(enemy.health);
                        Platform.runLater(() -> {
                            enemy.progressBar.setProgress(enemy.progressBar.getProgress() - p.normal_damage);
                        });
                    }

                    Platform.runLater(() -> {
                        root.getChildren().removeAll(p.cld.ret, p.imgView);
                    });

                    projectileIterator.remove();
//                    enemy.p.cld.ret.getBoundsInLocal())
                    //DA MODIFICARE
                } else if(p.cld.ret.intersects( ((AFireWeapon) enemy.getWeapon()).getMag().getFirst().cld.ret.getBoundsInLocal() ) ){    // se colpisce il proiettile del nemico
                    Platform.runLater(() -> {
                        root.getChildren().removeAll(p.cld.ret, p.imgView);
                    });

                    projectileIterator.remove();
                }
            }
        }

        if(fireWeapon.getMag().isEmpty())   attack_flag = false;    //così ne posso sparare di più


    }

}
