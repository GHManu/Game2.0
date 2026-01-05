package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.ACharacter;

public class DamageData {
    private ACharacter character;
    private double damage;

    public DamageData(ACharacter character, double damage) {
        this.character = character;
        this.damage = damage;
    }

    public ACharacter getCharacter() {
        return character;
    }

    public void setCharacter(ACharacter character) {
        this.character = character;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
