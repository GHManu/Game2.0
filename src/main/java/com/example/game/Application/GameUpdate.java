package com.example.game.Application;

import com.example.game.*;
import com.example.game.Environment.Map;
import com.example.game.Scene.GameScene;
import com.example.game.State.GameLoop.IGameLoopState;
import com.example.game.State.UI.PlayingState;
import com.example.game.UI.HUD;
import com.example.game.UI.UIDTO;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

import java.util.*;


public class GameUpdate implements Runnable{
    private final Group world;
    private ACharacterPlayable plr;
    private final Thread currentThread;
    private GameScene game_scene;
    private final float FPS = 60;
    private ACharacterEnemy enemy;
    private final ACharacterEnemyFactory character_enemy_factory;
    private final ACharacterPlayableFactory character_playable_factory;
    private final Map world_map;
    private IGameLoopState current_state;
    private Set<KeyCode> keys_pressed;
    private GameController game_controller;

    public GameController getGame_controller() {
        return game_controller;
    }

    public void setGame_controller(GameController game_controller) {
        this.game_controller = game_controller;
    }

    public void setState(IGameLoopState newState) {
        if (current_state != null)
            current_state.stop(this);
        current_state = newState;
        current_state.start(this);
    }

    protected GameUpdate(Group world){
        EventBus.GetInstance();
        this.setState(new PlayingState());
        currentThread = new Thread(this);

        this.world = world;
        world_map = new Map();

        character_playable_factory = new PlayerFactory();
        character_enemy_factory = new EnemyFactory();

        plr = character_playable_factory.createPlayer("fire weapon", "pistol", "with input", "sixway");

        enemy = character_enemy_factory.createEnemy("fire weapon", "pistol", "without input", "oneway");

        HUD.getInstance();
    }

    public void startGameLoop(GameScene gameScene){
        world.getChildren().clear();
        plr.setRoot(world);

        this.game_scene = gameScene;


        world_map.drawMap(world);

        List<Node> elements = List.of(
                plr.getvBox(),
                plr.getImgView(),
                enemy.getvBox(),
                enemy.getImgView()
        );

        elements.forEach(node ->
                EventBus.get().notifyEventListenerObserver(
                        new DTOEvent(EEventType.ADD_ELEMENT, new UIDTO(world, node))
                )
        );



        currentThread.start();

    }

    private void setEventListeners(){
        if(plr.getProgressBar().getProgress() > 0.1){
            game_scene.setOnKeyPressed((event) -> keys_pressed.add(event.getCode()));
            game_scene.setOnKeyReleased(event -> keys_pressed.remove(event.getCode()));
            game_scene.setOnMouseClicked(mouseEvent -> {
                if (plr.getProgressBar().getProgress() <= 0.1)
                    return;

                plr.setxDest(mouseEvent.getSceneX());
                plr.setyDest(mouseEvent.getSceneY());
                plr.setInit_attack_flag(true);
                plr.setAttack_flag(true);
            });

        }

    }


    @Override
    public void run() { //processo in esecuzione

        double deltatime = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();

        keys_pressed = new HashSet<>();

        this.setEventListeners();

        while(currentThread.isAlive()){
            currentTime = System.currentTimeMillis();

            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);
            lastUpdate = currentTime;

            if(deltatime >= 1){
                current_state.update(this,deltatime);
                deltatime--;
            }
        }

    }

    public void kill_Character(ACharacter character){
        if(character != null && character.getCld().getShape() != null) {
                EventBus.get().notifyEventListenerObserver(
                        new DTOEvent(EEventType.REMOVE_ELEMENT, new UIDTO(world,character.getvBox())));
                character.setvBox(null);

                EventBus.get().notifyEventListenerObserver(
                        new DTOEvent(EEventType.REMOVE_ELEMENT, new UIDTO(world,character.getCld().getShape())));
                character.getCld().setShape(null);

                EventBus.get().notifyEventListenerObserver(
                        new DTOEvent(EEventType.REMOVE_ELEMENT, new UIDTO(world,character.getImgView())));
                character.setImgView(null);
           System.gc();
        }

    }

    public void updateCamera() {
        double targetX = game_scene.getWidth() / 2 - (plr.getX() + plr.getImg().getWidth() / 2);
        double targetY = game_scene.getHeight() / 2 - (plr.getY() + plr.getImg().getHeight() / 2);

        // Fattore di interpolazione (0.1 = segue lentamente, 1 = istantaneo)
        double lerpFactor = 1;

        double newX = world.getLayoutX() + (targetX - world.getLayoutX()) * lerpFactor;
        double newY = world.getLayoutY() + (targetY - world.getLayoutY()) * lerpFactor;

        world.setLayoutX(newX);
        world.setLayoutY(newY);
    }


    public void gameMethodMovementHandler(double deltaTime, Set<KeyCode> keysPressed) {

            if(enemy != null && plr != null && plr.getCld() != null && enemy.getCld() != null)   plr.getCld().collisionDetected(enemy.getCld().getShape(), true);


            assert plr != null;

            plr.movement(deltaTime, keysPressed);

    }

    public void gameMethodAttackHandler(double deltatime){
        if(plr.isAttack_flag() && plr.getProgressBar().getProgress() > 0.1){
            plr.select_attack(deltatime, plr, enemy);
        }

    }

    public ACharacterPlayable getPlr() {
        return plr;
    }

    public void setPlr(ACharacterPlayable plr) {
        this.plr = plr;
    }

    public ACharacterEnemy getEnemy() {
        return enemy;
    }

    public void setEnemy(ACharacterEnemy enemy) {
        this.enemy = enemy;
    }

    public Set<KeyCode> getKeys_pressed() {
        return keys_pressed;
    }
}
