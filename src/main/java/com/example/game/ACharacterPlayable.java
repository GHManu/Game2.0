package com.example.game;

import javafx.scene.input.KeyCode;

//16 righe

public abstract class ACharacterPlayable extends ACharacter {
    //comandi
    protected  final KeyCode forward = KeyCode.W;
    protected  final KeyCode backward = KeyCode.S;
    protected  final KeyCode leftward = KeyCode.A;
    protected  final KeyCode rightward = KeyCode.D;
    protected  final KeyCode forwardArrow = KeyCode.UP;
    protected  final KeyCode backwardArrow = KeyCode.DOWN;
    protected  final KeyCode leftwardArrow = KeyCode.LEFT;
    protected  final KeyCode rightwardArrow = KeyCode.RIGHT;
    protected  final KeyCode sprint = KeyCode.SHIFT;

    //movimento
    protected abstract void moveUp(double deltaTime);

    protected abstract void moveDown(double deltaTime);

    protected abstract void moveLeft(double deltaTime);

    protected abstract void moveRight(double deltaTime);

    protected abstract void sprintStatus(double deltatime);
    protected abstract void walk(double deltaTime);

    protected abstract void normal_attack(double deltatime);

}
