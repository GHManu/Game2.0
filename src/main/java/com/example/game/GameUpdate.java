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
    private Thread currentThread;
    private GameScene gameScene;
    private final float FPS = 60;
    private ACharacterEnemy enemy;
    ACharacterEnemyFactory characterEnemyFactory;
    AWeaponFactory weaponFactory;


    private volatile static GameUpdate uniqueInstance;

    private GameUpdate(Group root){
        currentThread = new Thread(this);
        weaponFactory = new FireWeaponFactory();


        plr = new Player(weaponFactory.createWeapon("pistol"));


        characterEnemyFactory = new EnemyFactory();
        enemy = characterEnemyFactory.createEnemy("fireweapon", "pistol", "withoutinput", "oneway");

    }

    public static GameUpdate getInstance(Group root){
        if(uniqueInstance == null)
            synchronized (GameUpdate.class){
                if(uniqueInstance == null)
                    uniqueInstance =  new GameUpdate(root);
            }
        return uniqueInstance;
    }



    public void startGameLoop(GameScene gameScene, Group root){
        plr.setRoot(root);

        root.getChildren().addAll(plr.getvBox(),plr.getCld().ret, plr.getImgView());
        this.gameScene = gameScene;
        root.getChildren().addAll(enemy.getvBox(), enemy.getCld().ret, enemy.getImgView());
        currentThread.start();
        plr.setEnemy(enemy);
        plr.setMovementStrategyWithInput(new EightWaySmoothlyMovementWithoutInput());
    }

    @Override
    public void run() { //processo in esecuzione

        double deltatime = 0;
        long currentTime;    //tempo corrente del processo
        long lastUpdate = System.currentTimeMillis();
        //creo un Set di KeyCode, per salvare tutti gli eventi relativi all'input da tastiera
        Set<KeyCode> keysPressed = new HashSet<>();

        if(plr.getProgressBar().getProgress() > 0.1) gameScene.setOnKeyPressed((event) -> keysPressed.add(event.getCode()));
        if(plr.getProgressBar().getProgress() > 0.1) gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));


        //gameLoop
        while(currentThread.isAlive()){
            currentTime = System.currentTimeMillis();

            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);   //se uso FPS allora metto l'if dopo, sennò no
            lastUpdate = currentTime;

            if(deltatime >= 1){

                if(plr.getProgressBar().getProgress() > 0.1) gameMethodMovementHandler(deltatime, keysPressed);
                if(plr.getProgressBar().getProgress() > 5.551115123125783E-17) gameMethodAttackHandler(deltatime);
                if(enemy != null && enemy.getProgressBar().getProgress() <= 0.1) {   kill_Character(enemy); }//perchè è 1.1368683772161603E-13

                if(plr != null && plr.getProgressBar().getProgress() <= 0.1)    kill_Character(plr);

                if (enemy != null) {
                    enemy.select_attack(deltatime, plr , enemy);
                }
                deltatime--;
            }
        }

    }

    private void kill_Character(ACharacter character){
        if(character != null && character.getCld().ret != null) {
            Platform.runLater(() -> {
                plr.root.getChildren().remove(character.getvBox());
                character.setvBox(null);
            });
            Platform.runLater(() -> {
                plr.root.getChildren().remove(character.getCld().ret);
                character.getCld().ret = null;
            });
           Platform.runLater(() -> {
                plr.root.getChildren().remove(character.getImgView());
                character.setImgView(null);
            });
           System.gc(); //richiama il garbage collector
        }

    }


    private void gameMethodMovementHandler(double deltaTime, Set<KeyCode> keysPressed) {

            if(enemy != null && plr != null && plr.getCld() != null && enemy.getCld() != null)   plr.getCld().collision_Detected(enemy.getCld().ret, true);


            assert plr != null;
            plr.getMovementStrategyWithInput().movement(deltaTime, plr, keysPressed);

            // Sprint
            if (keysPressed.contains(AInputCommands.sprint)) {
                plr.sprintStatus(deltaTime);  // Attiva o continua lo sprint
                plr.sprint(deltaTime);
            } else {
                plr.walk(deltaTime);
            }

    }

    private void gameMethodAttackHandler(double deltatime){

             if(plr.getProgressBar().getProgress() > 5.551115123125783E-17) {
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

        if(plr.attack_flag && plr.getProgressBar().getProgress() > 0.1){
            plr.select_attack(deltatime, plr, enemy);
        }

    }

}
