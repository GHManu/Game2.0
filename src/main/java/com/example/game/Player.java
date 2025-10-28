package com.example.game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//circa 108 righe

public class Player extends ACharacterPlayable {
    //per lo sprint
    protected double timeSprint;
    protected double timeReCharge;
    protected static final double SPRINT_TIME_DURATION = 400.0; // durata dello sprint
    protected static final double RECHARGE_TIME_DURATION = 400.0; // tempo di ricarica
    protected boolean isSprinting;

    //attacco
    private List<NormalProjectile> normalProjectiles;
    Image attackImage;
    boolean attack_flag;
    double xDest, yDest;

    private Enemy enemy;

    public Player(){
        x = IScreenSettings.screenWidth/2.0;
        y = IScreenSettings.screenHeight/2.0;


        progressBar = new ProgressBar(1.0);   //1 = 100%, 0.5 = 50%
        vBox = new VBox(progressBar);
        vBox.setSpacing(10);
        vBox.setLayoutX(this.x);
        vBox.setLayoutY(this.y - 20);


        this.normalProjectiles = new ArrayList<>();
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

        img = new Image(getClass().getResourceAsStream("Images/Front_Pg.png"), IScreenSettings.sizeTile, // requestedWidth
                IScreenSettings.sizeTile, // requestedHeight
                false, false);  //preserveRatio = false e disattivando il smoothing a livello di Image
        //Questo dice a JavaFX: "Scala esattamente a 48x48, non interpolare, non mantenere le proporzioni"
        imgView = new ImageView(img);

        //imposto la grandezza dell'immagine
        imgView.setFitWidth(IScreenSettings.sizeTile);
        imgView.setFitHeight(IScreenSettings.sizeTile);



        imgView.setLayoutX(x);
        imgView.setLayoutY(y);

        attackImage = new Image(getClass().getResourceAsStream("Images/ProvaAttacco.png"), IScreenSettings.sizeTile, // requestedWidth
                IScreenSettings.sizeTile, // requestedHeight
                false, false);


        cld = new Collider(x, y, IScreenSettings.sizeTile, IScreenSettings.sizeTile);
    }

//    protected static void walk(KeyEvent keyEvent, double deltatime) {
//        if(forward == keyEvent.getCode() || forwardArrow == keyEvent.getCode()){
//            y -= speed * deltatime;   //così è più fluido il movimento invece di spostarsi di netto
//            playerView.setY(y); //sposto la posizione dell'immagine
//
//            System.out.println("Stai premendo W");
//        }
//
//        if(backward == keyEvent.getCode() || backwardArrow == keyEvent.getCode()){
//            y += speed * deltatime;
//            playerView.setY(y);
//            System.out.println("Stai premendo S");
//        }
//
//        if(leftward == keyEvent.getCode() || leftwardArrow == keyEvent.getCode()){
//            x -= speed * deltatime;
//            playerView.setX(x);
//            System.out.println("Stai premendo A");
//        }
//
//        if(rightward == keyEvent.getCode() || rightwardArrow == keyEvent.getCode()){
//            x += speed * deltatime;
//            playerView.setX(x);
//            System.out.println("Stai premendo D");
//        }

    /*Se stai aggiornando la posizione del personaggio in un thread separato (ad esempio, per animazioni o movimenti continui),
    assicurati che gli aggiornamenti vengano eseguiti nel thread giusto. In JavaFX, tutte le modifiche all'interfaccia grafica devono essere
    fatte nel JavaFX Application Thread.
    Puoi risolverlo utilizzando Platform.runLater() se stai aggiornando la UI da un thread separato:
    * */


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
    protected void normal_attack(double deltatime) {

        if(this.progressBar.getProgress() > 5.551115123125783E-17) {

            NormalProjectile p = new NormalProjectile(attackImage, imgView.getLayoutX(), imgView.getLayoutY(), xDest, yDest);

            this.attack_flag = true;
//        Platform.runLater(() -> {attackView.setLayoutX(playerView.getLayoutX());
//        attackView.setLayoutY(playerView.getLayoutY()); });

            normalProjectiles.add(p);
            Platform.runLater(() -> {
                root.getChildren().addAll(p.cld.ret, p.imgView);
            });
            //System.out.println("Attacco");
        }
    }

    protected void setDestinationAttack(double xDest, double yDest){
        this.xDest = xDest;
        this.yDest = yDest;
    }

    protected void setRoot(Group root){
        this.root = root;
    }

    protected void setEnemy(Enemy enemy){this.enemy = enemy;}

    protected void shot(double deltaTime){
        //teorema di pitagora
//        double dx = xDest - xAttack;
//        double dy = yDest - yAttack;
//        double distance = Math.sqrt(dx * dx + dy * dy); //ipotenusa, vettore direzione
//        //calcolo le componenti
//        double directionX = dx / distance;  //coseno dell'angolo tra x e l'ipotenusa
//        double directionY = dy / distance;  //seno dell'angolo tra y e l'ipotenusa

        Iterator<NormalProjectile> iterator = normalProjectiles.iterator();
        while(iterator.hasNext()){
            NormalProjectile p = iterator.next();
            p.journey(deltaTime, p.speed);


            if(p.isArrived(xDest, yDest)){
                Platform.runLater(() -> {
                    root.getChildren().removeAll(p.cld.ret, p.imgView);
                });
                iterator.remove();
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
                    iterator.remove();
                } else if(p.cld.ret.intersects(enemy.p.cld.ret.getBoundsInLocal())){    // se colpisce il proiettile del nemico
                    Platform.runLater(() -> {
                        root.getChildren().removeAll(p.cld.ret, p.imgView);
                    });
                    iterator.remove();
                }
            }
        }

        if(normalProjectiles.isEmpty())   attack_flag = false;    //così ne posso sparare di più


    }


}
