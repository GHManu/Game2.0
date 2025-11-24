package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Player extends ACharacterPlayable {

    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0;
    protected static final double RECHARGE_TIME_DURATION = 400.0;
    protected boolean isSprinting;

    boolean attack_flag;
    double xDest, yDest;

    private ACharacterEnemy enemy;

    public Player(){

        setX( (IScreenSettings.screenWidth/2.0));
        setY((IScreenSettings.screenHeight/2.0));

        setProgressBar(new ProgressBar(1.0));
        setvBox(new VBox(getProgressBar()));

        getvBox().setSpacing(10);
        getvBox().setLayoutX(getX());
        getvBox().setLayoutY(getY() - 20);


        attack_flag = false;

        xDest = 0;
        yDest = 0;

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


    public Player(AWeapon weapon){
        this();
        this.setWeapon(weapon);
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
            setSpeed(4);
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
        setSpeed(2.5);
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
        if(this.getProgressBar().getProgress() > 5.551115123125783E-17) {
            System.out.println("Normal Attack");
            AFireWeapon fireWeapon = (AFireWeapon) this.getWeapon();
            NormalProjectile p = new NormalProjectile(EGameImages.ProvaAttacco.getImage(), this.getImgView().getLayoutX(), this.getImgView().getLayoutY(), xDest, yDest);

            this.attack_flag = true;

            fireWeapon.getMag().add(p);
            Platform.runLater(() -> {
                root.getChildren().add( p.getImgView());
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
        AFireWeapon fireWeapon_enemy = (AFireWeapon) enemy.getWeapon();
        ProjectileIterator projectileIterator = new ProjectileIterator(fireWeapon.getMag());

        while(projectileIterator.hasNext()){
            NormalProjectile p = (NormalProjectile) projectileIterator.next();
            fireWeapon.p = p;
            this.getWeapon().fight(deltaTime);

            if(p.isArrived(xDest, yDest)){

                Platform.runLater(() -> {
                    root.getChildren().remove( p.getImgView());
                });

                projectileIterator.remove();

            }else if ((enemy != null) && (enemy.getCld().ret != null) ) {
                if ( p.getCld().ret.intersects(enemy.getCld().ret.getBoundsInLocal())) {
                    if (enemy.getHealth() > 1) {
                        double speed = enemy.getSpeed(), health = enemy.getHealth();
                        speed += 0.2;
                        enemy.setSpeed(speed);
                        health -= (enemy.getInitial_Health() * p.normal_damage);
                        enemy.setHealth(health);

                        Platform.runLater(() -> {
                            enemy.getProgressBar().setProgress(enemy.getProgressBar().getProgress() - p.normal_damage);
                        });
                    }

                    Platform.runLater(() -> {
                        root.getChildren().remove(p.getImgView());
                    });

                    projectileIterator.remove();
                    //DA MODIFICARE
                } else if(p.getCld().ret.intersects( fireWeapon_enemy.getMag().getFirst().getCld().ret.getBoundsInLocal() ) ){
                    Platform.runLater(() -> {
                        root.getChildren().remove(p.getImgView());
                    });

                    projectileIterator.remove();
                }
            }
        }

        if(fireWeapon.getMag().isEmpty())   attack_flag = false;    //così ne posso sparare di più


    }

}
