package com.example.game.Environment;

import com.example.game.Environment.Character.ACharacter;
import javafx.scene.Group;
import javafx.scene.Scene;

public class Camera {
    private final Group world;
    private final Scene scene;
    private ACharacter target;
    private double lerpFactor = 1.0;
    public Camera(Group world, Scene scene, ACharacter target) {
        this.world = world;
        this.scene = scene;
        this.target = target;
    }
    public void setTarget(ACharacter target) {
        this.target = target;
    }
    public void setLerp(double lerp) {
        this.lerpFactor = lerp;
    }
    public void update() {
        double targetX = scene.getWidth() / 2 - (target.getX() + target.getImg().getWidth() / 2);
        double targetY = scene.getHeight() / 2 - (target.getY() + target.getImg().getHeight() / 2);
        double newX = world.getLayoutX() + (targetX - world.getLayoutX()) * lerpFactor;
        double newY = world.getLayoutY() + (targetY - world.getLayoutY()) * lerpFactor;
        world.setLayoutX(newX); world.setLayoutY(newY);
    }
}
