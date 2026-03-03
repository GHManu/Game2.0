package com.example.game.Application;

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
    private final CharacterSpawner characterSpawner;
    private MovementHandler movementHandler;
    private AttackHandler attackHandler;

    private final Group world;
    private final Thread currentThread;
    private final MyMap world_My_map;
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

    protected GameUpdate(Group world){
        currentThread = new Thread(this);
        this.world = world;
        world_My_map = new MyMap("/com/example/game/Maps/map.txt");
        this.characterSpawner = new CharacterSpawner(new PlayerFactory(), new EnemyFactory());
        destroyer = new Destroyer(world);
        HUD.getInstance();
    }

    public void startGameLoop(Stage stage,GameScene gameScene){
        world.getChildren().clear();


        this.input_manager = new InputManager(stage, gameScene);

        plr = characterSpawner.spawnPlayer(this.input_manager);
        enemy = characterSpawner.spawnEnemy();

        CollisionHandler collisionHandler = new CollisionHandler();
        movementHandler = new MovementHandler(collisionHandler);
        attackHandler = new AttackHandler(this.input_manager);

        this.setState(new PlayingState());
        plr.setRoot(world);
        camera = new Camera(world, gameScene, plr);
        world_My_map.drawMap(world);

        List<Node> elements = List.of(
                plr.getvBox(),
                plr.getImgView(),
                enemy.getvBox(),
                enemy.getImgView()
        );

        elements.forEach(node ->
                 HUD.addElement(world, node)
        );


        currentThread.start();

    }


    @Override
    public void run() {

        double deltatime = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();



        while(currentThread.isAlive()){
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
        movementHandler.handle(dt, characterSpawner.getCharacters(), enemy, plr);
    }

    public void updateAttack(double dt) {
        attackHandler.handle(dt, plr, enemy, characterSpawner.getCharacters());
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
