package com.example.game;

public abstract class APlayerMovementDecorator extends ACharacterPlayable{
    private ACharacterPlayable character;

    public ACharacterPlayable getCharacter() {
        return character;
    }

    public void setCharacter(ACharacterPlayable character) {
        this.character = character;
    }
}
