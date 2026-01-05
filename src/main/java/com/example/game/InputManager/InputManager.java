package com.example.game.InputManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class InputManager {
    private Set<KeyCode> keysPressed;
    private double mouseX; private double mouseY;
    private boolean mouseClicked;
    public InputManager(Scene gameScene) {
        keysPressed = new HashSet<>();
        setEventListeners(gameScene);
    }
    private void setEventListeners(Scene gameScene) {
        gameScene.setOnKeyPressed(event -> keysPressed.add(event.getCode()));
        gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));
        gameScene.setOnMouseClicked(mouseEvent -> {
            mouseX = mouseEvent.getSceneX();
            mouseY = mouseEvent.getSceneY();
            mouseClicked = true; });
    }
    public boolean isPressed(KeyCode key) { return keysPressed.contains(key); }
    public boolean isMovingForward() { return isPressed(AInputCommands.forward) || isPressed(AInputCommands.forwardArrow); }
    public boolean isMovingBackward() { return isPressed(AInputCommands.backward) || isPressed(AInputCommands.backwardArrow); }
    public boolean isMovingLeft() { return isPressed(AInputCommands.leftward) || isPressed(AInputCommands.leftwardArrow); }
    public boolean isMovingRight() { return isPressed(AInputCommands.rightward) || isPressed(AInputCommands.rightwardArrow); }
    public boolean isSprinting() { return isPressed(AInputCommands.sprint); }
    public boolean consumeMouseClick() { boolean clicked = mouseClicked; mouseClicked = false; return clicked; }
    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public Set<KeyCode> getPressedKeys() {
        return keysPressed;
    }

}
