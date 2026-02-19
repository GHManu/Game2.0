package com.example.game.InputManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;


public class InputManager {
    private final Set<KeyCode> keysPressed;

    private double mouseXClick;
    private double mouseYClick;

    private double currentMouseX;
    private double currentMouseY;

    private boolean mouseClicked;


    public InputManager(Stage stage, Scene gameScene) {
        keysPressed = new HashSet<>();
        setEventListeners(stage, gameScene);
    }
    private void setEventListeners(Stage stage, Scene gameScene) {

        gameScene.setOnKeyPressed(event -> keysPressed.add(event.getCode()));
        gameScene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));

        gameScene.setOnMouseMoved(e -> { currentMouseX = e.getSceneX(); currentMouseY = e.getSceneY(); });

        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if(mouseEvent.getButton() == AInputCommands.MOUSE_LEFT){
                mouseXClick = mouseEvent.getX();
                mouseYClick = mouseEvent.getY();
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
    public double getMouseX() { return mouseXClick; }
    public double getMouseY() { return mouseYClick; }
    public Set<KeyCode> getPressedKeys() {
        return keysPressed;
    }

}
