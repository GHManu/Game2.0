package com.example.game.Application;

import com.example.game.Environment.AEntity;
import com.example.game.Environment.Camera;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Enemy.ACharacterEnemyFactory;
import com.example.game.Environment.Character.Enemy.EnemyFactory;
import com.example.game.Environment.Character.NPC.ANPC;
import com.example.game.Environment.Character.NPC.NPC;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Character.Playable.ACharacterPlayableFactory;
import com.example.game.Environment.Character.Playable.PlayerFactory;
import com.example.game.Environment.Destroyer;
import com.example.game.Environment.Map.MyMap;
import com.example.game.InputManager.InputManager;
import com.example.game.Scene.GameScene;
import com.example.game.State.GameLoop.IGameLoopState;
import com.example.game.State.GameLoop.PlayingState;
import com.example.game.UI.HUD;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

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
    private final MyMap world_My_map;
    private IGameLoopState current_state;
    private GameController game_controller;
    InputManager input_manager;
    private Destroyer destroyer;
    private Camera camera;
    ANPC npc1;
    private static List<AEntity> characters = new ArrayList<>();

    public static List<AEntity> getCharacters() {
        return characters;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }

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
        currentThread = new Thread(this);
        characters = new ArrayList<>();
        this.world = world;
        world_My_map = new MyMap("/com/example/game/Maps/map.txt");

        character_playable_factory = new PlayerFactory();
        character_enemy_factory = new EnemyFactory();

        destroyer = new Destroyer(world);
        HUD.getInstance();
    }

    public void startGameLoop(Stage stage,GameScene gameScene){
        world.getChildren().clear();


        this.game_scene = gameScene;
        this.input_manager = new InputManager(stage,game_scene);

        plr = character_playable_factory.createPlayer("fire weapon", "pistol", "with input", "sixway", input_manager);
        enemy = character_enemy_factory.createEnemy("fire weapon", "pistol", "without input", "oneway");

        npc1 = new NPC("npc1.txt");

        characters.add(plr);
        characters.add(enemy);
        characters.add(npc1);

        this.setState(new PlayingState());
        plr.setRoot(world);
        camera = new Camera(world, game_scene, plr);
        world_My_map.drawMap(world);

        List<Node> elements = List.of(
                plr.getvBox(),
                plr.getImgView(),
                enemy.getvBox(),
                enemy.getImgView(),
                npc1.getImgView()
        );

        elements.forEach(node ->
                 HUD.addElement(world, node)
        );



        currentThread.start();

    }


    @Override
    public void run() { //processo in esecuzione

        double deltatime = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();



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

    public void gameMethodMovementHandler(double deltaTime) {

        for (AEntity c1 : characters) {
            for (AEntity c2 : characters) {
                if (c1 != c2) {
                    c1.getCld().collisionDetected(c2.getCld().getShape(), true);
                }
            }
        }


        enemy.getMovementStrategy().movement(deltaTime, enemy);
        plr.movement(deltaTime);
        //npc1.getMovementStrategy().movement(deltaTime, npc1);
    }



    public void gameMethodAttackHandler(double deltatime){
       if(input_manager.consumeMouseClick()){
           plr.setInit_attack_flag(true);
           plr.setxDest(input_manager.getMouseX());
           plr.setyDest(input_manager.getMouseY());
       }
       if(plr.isAttack_flag() || plr.isInit_attack_flag()){
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

    public InputManager getInput_manager() {
        return input_manager;
    }
}
