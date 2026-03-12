package com.example.game.Application.Game;

import com.example.game.Application.GameController;
import com.example.game.Environment.Camera;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Enemy.EnemyFactory;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
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

    private ACharacterPlayable plr;
    private ACharacterEnemy enemy;
    private final CharacterSpawner character_spawner;
    private MovementHandler movement_handler;
    private AttackHandler attack_handler;

    private final Group world;
    private final Thread current_thread;
    private final MyMap world_my_map;
    private IGameLoopState current_state;
    private GameController game_controller;
    InputManager input_manager;
    private Destroyer destroyer;
    private Camera camera;




    public void setState(IGameLoopState newState) {
        if (current_state != null)
            current_state.stop(this);
        current_state = newState;
        current_state.start(this);
    }

    public GameUpdate(Group world){
        current_thread = new Thread(this);
        this.world = world;
        world_my_map = new MyMap("/com/example/game/Maps/map.txt");
        this.character_spawner = new CharacterSpawner(new PlayerFactory(), new EnemyFactory());
        destroyer = new Destroyer(world);
        HUD.getInstance();
    }

    public void startGameLoop(Stage stage,GameScene game_scene){
        world.getChildren().clear();


        this.input_manager = new InputManager(stage, game_scene);

        plr = character_spawner.spawnPlayer(this.input_manager);
        enemy = character_spawner.spawnEnemy();

        CollisionHandler collision_handler = new CollisionHandler();
        movement_handler = new MovementHandler(collision_handler);
        attack_handler = new AttackHandler(this.input_manager);

        this.setState(new PlayingState());
        plr.setRoot(world);
        camera = new Camera(world, game_scene, plr);
        world_my_map.drawMap(world);

        List<Node> elements = List.of(
                plr.getVbox(),
                plr.getImg_view(),
                enemy.getVbox(),
                enemy.getImg_view()
        );

        elements.forEach(node ->
                 HUD.addElement(world, node)
        );


        current_thread.start();

    }


    @Override
    public void run() {

        double deltatime = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();

        while(current_thread.isAlive()){
            currentTime = System.currentTimeMillis();

            float FPS = 60;
            deltatime += (currentTime - lastUpdate) / (1000.0 / FPS);
            lastUpdate = currentTime;

            if(deltatime >= 1){
                current_state.update(this,deltatime);
                deltatime--;
            }
        }

    }
    public void updateMovement(double dt) {
        movement_handler.handle(dt, character_spawner.getCharacters(), enemy, plr);
    }

    public void updateAttack(double dt) {
        attack_handler.handle(dt, plr, enemy, character_spawner.getCharacters());
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
}
