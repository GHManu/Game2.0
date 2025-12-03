package com.example.game;

import javafx.scene.input.KeyCode;

import java.util.Set;

public class Sprint extends APlayerMovementDecorator {


    public Sprint(ACharacterPlayable character) {
        this.setCharacter(character);
    }


    @Override
    protected void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy) {
        this.getCharacter().getFightStrategy().normalAttack(deltatime, enemy, plr);
    }

    @Override
    protected void movement(double deltatime, Set<KeyCode> keyCodes) {

    }
}
