package com.example.game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.util.*;

//circa 89 righe

//implementa l'interfaccia per far si che il processo sia eseguibile
public class GameUpdate implements Runnable{
    private Player plr;
    private Thread currentThread;   //per tenere conto del tempo del processo
    private GameScene gameScene;
    private final float FPS = 60; //Frame Per Second
    private Enemy enemy;

    private volatile static GameUpdate uniqueInstance;

    private GameUpdate(Group root){
        currentThread = new Thread(this);
        plr = new Player();

        enemy = new Enemy(new NormalPistol());
        plr.setWeapon(new NormalPistol());
    }

    public static GameUpdate getInstance(Group root){
        if(uniqueInstance == null)
            synchronized (GameUpdate.class){
                if(uniqueInstance == null)
                    uniqueInstance =  new GameUpdate(root);
            }
        return uniqueInstance;
    }


    //inizializzare le varie cose
    public void startGameLoop(GameScene gameScene, Group root){
        plr.setRoot(root);

        root.getChildren().addAll(plr.vBox,plr.cld.ret, plr.imgView); //per far si che l'immagine stia sopra al rettangolo
        this.gameScene = gameScene;
        root.getChildren().addAll(enemy.vBox, enemy.cld.ret, enemy.imgView);
        currentThread.start(); //chiama implicitamente run, eseguo il processo
        plr.setEnemy(enemy);

    }

    @Override
    public void run() { //processo in esecuzione

        double deltatime = 0;
        long currentTime;    //tempo corrente del processo
        long lastUpdate = System.currentTimeMillis();
        //creo un Set di KeyCode, per salvare tutti gli eventi relativi all'input da tastiera
        Set<KeyCode> keysPressed = new HashSet<>();

        if(plr.progressBar.getProgress() > 0.1) gameScene.setOnKeyPressed((event) -> keysPressed.add(event.getCode()));
        if(plr.progressBar.getProgress() > 0.1) gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));


        //gameLoop
        while(currentThread.isAlive()){
            currentTime = System.currentTimeMillis();

            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);   //se uso FPS allora metto l'if dopo, sennò no
            lastUpdate = currentTime;

            if(deltatime >= 1){

                if(plr.progressBar.getProgress() > 0.1) gameMethodMovementHandler(deltatime, this.gameScene, keysPressed);
                if(plr.progressBar.getProgress() > 5.551115123125783E-17) gameMethodAttackHandler(deltatime);
                if(enemy != null && enemy.progressBar.getProgress() <= 0.1) {   kill_Enemy(); }//perchè è 1.1368683772161603E-13

                if(plr != null && plr.progressBar.getProgress() <= 0.1)    player_Died();

                if (enemy != null) {

                    enemy.attack(deltatime, plr, enemy);
                    if(!enemy.attack_flag) {
                        //posso farlo poichè io so che metto una pistola!
                        AFireWeapon weapon = (AFireWeapon) enemy.getWeapon();
//                        weapon.shot(deltatime, plr,enemy.p , enemy);
                        enemy.shot(deltatime, plr, weapon.getProjectiles().getFirst() , enemy);
                    }

                }
                deltatime--;
            }
        }

    }

    private void kill_Enemy(){
        if(this.enemy != null && this.enemy.cld.ret != null) {
            Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.vBox);
                enemy.vBox = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.cld.ret);
                enemy.cld.ret = null;
            });
           Platform.runLater(() -> {
                plr.root.getChildren().remove(enemy.imgView);
                enemy.imgView = null;
            });
           System.gc(); //richiama il garbage collector
        }

    }

    private void player_Died(){
        if(this.plr != null && this.plr.cld.ret != null) {
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.vBox);
                plr.vBox = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.cld.ret);
                plr.cld.ret = null;
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(plr.imgView);
                plr.imgView = null;
            });
            System.gc(); //richiama il garbage collector
        }
    }


    private void gameMethodMovementHandler(double deltaTime, GameScene gameScene, Set<KeyCode> keysPressed) {

            if(enemy != null && plr != null && plr.cld != null && enemy.cld != null)   plr.cld.collision_Detected(enemy.cld.ret, true);
            //gestisco l'evento, la penultima condizione degli if è per non far andare fuori mappa, l'ultima condizione è per la collisione
            if ( (keysPressed.contains(plr.forward) || keysPressed.contains(plr.forwardArrow)) && plr.y >0
                    ) {
                plr.dir_forward = true;

                plr.changeImage(EGameImages.Back_Pg.getImage());
                if(plr.cld.fr)   plr.moveUp(deltaTime);
            }
            else{
                plr.dir_forward = false;
            }

            if ( (keysPressed.contains(plr.backward) || keysPressed.contains(plr.backwardArrow)) && plr.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)
                     ) {
                plr.dir_backward = true;

                plr.changeImage(EGameImages.Front_Pg.getImage());
                if(plr.cld.br) plr.moveDown(deltaTime);
            }
            else{
                plr.dir_backward = false;
            }

            if ( (keysPressed.contains(plr.leftward) || keysPressed.contains(plr.leftwardArrow)) && plr.x > 0
                    ) {
                plr.dir_leftward = true;


                plr.changeImage(EGameImages.Left_Side_Pg.getImage());
                if(plr.cld.dx) plr.moveLeft(deltaTime);
            }
            else{
                plr.dir_leftward = false;
            }

            if ( (keysPressed.contains(plr.rightward) || keysPressed.contains(plr.rightwardArrow)) && plr.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)
                    ) {
                plr.dir_rightward = true;

                plr.changeImage(EGameImages.Right_Side_Pg.getImage());
                if(plr.cld.sx)    plr.moveRight(deltaTime);
            }
            else{
                plr.dir_rightward = false;
            }

            // Sprint
            // Sprint attivato quando il tasto è tenuto premuto
            if (keysPressed.contains(plr.sprint)) {
                plr.sprintStatus(deltaTime);  // Attiva o continua lo sprint
                plr.sprint(deltaTime);
            } else {
                plr.walk(deltaTime);
            }

    }

    private void gameMethodAttackHandler(double deltatime){

//             if(mouseButtons.contains(MouseButton.PRIMARY) && !plr.attack_flag){
//                 plr.normal_attack(deltatime);
//
//             }

             if(plr.progressBar.getProgress() > 5.551115123125783E-17) {
                 gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent mouseEvent) {
                         double getSceneX = mouseEvent.getSceneX();
                         double getSceneY = mouseEvent.getSceneY();
                         plr.setDestinationAttack(getSceneX, getSceneY);
                         plr.normal_attack(deltatime);
                     }
                 });
             }

        if(plr.attack_flag && plr.progressBar.getProgress() > 0.1){
            plr.shot(deltatime);
        }

    }

}
