package com.example.game;

public abstract class AMovementDecorator extends ACharacter{
    private ACharacter character;
    abstract void movement(double deltatime);

    public ACharacter getCharacter() {
        return character;
    }

    public void setCharacter(ACharacter character) {
        this.character = character;
    }
}
