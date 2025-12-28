package com.example.game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.util.*;


public class GameUpdate implements Runnable{
    private final Group world;
    private final ACharacterPlayable plr;
    private final Thread currentThread;
    private GameScene gameScene;
    private final float FPS = 60;
    private ACharacterEnemy enemy;
    private ACharacterEnemyFactory characterEnemyFactory;
    private AWeaponFactory weaponFactory;
    private final Map world_map;
    private GameSubject game_subject;

    private volatile static GameUpdate uniqueInstance;

    private GameUpdate(Group world){
        game_subject = new GameSubject();
        currentThread = new Thread(this);
        weaponFactory = new FireWeaponFactory();
        this.world = world;
        world_map = new Map();
        plr = new Player(game_subject,weaponFactory.createWeapon("pistol"));


        characterEnemyFactory = new EnemyFactory();
        plr.setMovementStrategy(new SixWaySmoothlyMovementWithInput());
        plr.setFightStrategy(new AttackFireWeaponPlayer());
        enemy = characterEnemyFactory.createEnemy(game_subject,"fire weapon", "pistol", "without input", "oneway");

    }

    public static GameUpdate getInstance(Group root){
        if(uniqueInstance == null)
            synchronized (GameUpdate.class){
                if(uniqueInstance == null)
                    uniqueInstance =  new GameUpdate(root);
            }
        return uniqueInstance;
    }



    public void startGameLoop(GameScene gameScene){
        plr.setRoot(world);
        plr.setEnemy(enemy);
        this.gameScene = gameScene;


        world_map.drawMap(world);

        world.getChildren().addLast(plr.getvBox());
        world.getChildren().addLast(plr.getImgView());
        world.getChildren().addLast(enemy.getvBox());
        world.getChildren().addLast(enemy.getImgView());

        currentThread.start();


    }

    @Override
    public void run() { //processo in esecuzione

        double deltatime = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();

        Set<KeyCode> keysPressed = new HashSet<>();

        if(plr.getProgressBar().getProgress() > 0.1) gameScene.setOnKeyPressed((event) -> keysPressed.add(event.getCode()));
        if(plr.getProgressBar().getProgress() > 0.1) gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));


        while(currentThread.isAlive()){
            currentTime = System.currentTimeMillis();

            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);
            lastUpdate = currentTime;

            if(deltatime >= 1){

                Platform.runLater(this::updateCamera);

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

    private void updateCamera() {
        double targetX = gameScene.getWidth() / 2 - (plr.getX() + plr.getImg().getWidth() / 2);
        double targetY = gameScene.getHeight() / 2 - (plr.getY() + plr.getImg().getHeight() / 2);

        // Fattore di interpolazione (0.1 = segue lentamente, 1 = istantaneo)
        double lerpFactor = 1;

        double newX = world.getLayoutX() + (targetX - world.getLayoutX()) * lerpFactor;
        double newY = world.getLayoutY() + (targetY - world.getLayoutY()) * lerpFactor;

        world.setLayoutX(newX);
        world.setLayoutY(newY);
    }


    private void gameMethodMovementHandler(double deltaTime, Set<KeyCode> keysPressed) {

            if(enemy != null && plr != null && plr.getCld() != null && enemy.getCld() != null)   plr.getCld().collision_Detected(enemy.getCld().ret, true);


            assert plr != null;

            plr.movement(deltaTime, keysPressed);

    }

    private void gameMethodAttackHandler(double deltatime){

             if(plr.getProgressBar().getProgress() > 5.551115123125783E-17) {
                 gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent mouseEvent) {
                         double getSceneX = mouseEvent.getSceneX();
                         double getSceneY = mouseEvent.getSceneY();
                         plr.setxDest(getSceneX);
                         plr.setyDest(getSceneY);

                         plr.setInit_attack_flag(true);
                         plr.setAttack_flag(true);

                     }
                 });
             }

        if(plr.isAttack_flag() && plr.getProgressBar().getProgress() > 0.1){
            plr.select_attack(deltatime, plr, enemy);
        }

    }


}
