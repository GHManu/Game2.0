package com.example.game.InputManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;


public class InputManager {
    private Set<KeyCode> keysPressed;
    private double mouseX;
    private double mouseY;
    private boolean mouseClicked;


    public InputManager(Scene gameScene) {
        keysPressed = new HashSet<>();
        setEventListeners(gameScene);
    }
    private void setEventListeners(Scene gameScene) {
        gameScene.setOnKeyPressed(event -> keysPressed.add(event.getCode()));
        gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));
        gameScene.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == AInputCommands.MOUSE_LEFT){
                mouseX = mouseEvent.getSceneX();
                mouseY = mouseEvent.getSceneY();
                mouseClicked = true;
            }
        });
    }
    public boolean isButtonPressed(KeyCode key) { return keysPressed.contains(key); }
    public boolean isMovingForward() { return isButtonPressed(AInputCommands.FORWARD) || isButtonPressed(AInputCommands.FORWARD_ARROW); }
    public boolean isMovingBackward() { return isButtonPressed(AInputCommands.BACKWARD) || isButtonPressed(AInputCommands.BACKWARD_ARROW); }
    public boolean isMovingLeft() { return isButtonPressed(AInputCommands.LEFTWARD) || isButtonPressed(AInputCommands.LEFTWARD_ARROW); }
    public boolean isMovingRight() { return isButtonPressed(AInputCommands.RIGHTWARD) || isButtonPressed(AInputCommands.RIGHTWARD_ARROW); }
    public boolean isSprinting() { return isButtonPressed(AInputCommands.SPRINT); }
    public boolean consumeMouseClick() { boolean clicked = mouseClicked; mouseClicked = false; return clicked; }
    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public Set<KeyCode> getPressedKeys() {
        return keysPressed;
    }

}
