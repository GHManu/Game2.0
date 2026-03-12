package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Objects.Interactable.Weapon.IFightStrategyPlayer;

import javafx.scene.Group;

import java.util.List;


public abstract class ACharacterPlayable extends ACharacter {
    public Group root;
    private double x_dest, y_dest;
    private IFightStrategyPlayer fight_strategy_player;

    public IFightStrategyPlayer getFight_strategy_player() {
        return fight_strategy_player;
    }

    public void setFight_strategy_player(IFightStrategyPlayer fight_strategy_player) {
        this.fight_strategy_player = fight_strategy_player;
    }

    public double getX_dest() {
        return x_dest;
    }

    public void setX_dest(double x_dest) {
        this.x_dest = x_dest;
    }

    public double getY_dest() {
        return y_dest;
    }

    public void setY_dest(double y_dest) {
        this.y_dest = y_dest;
    }

    public void setRoot(Group root){
        this.root = root;
    }

    public abstract void selectAttack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy, List<ACharacter> characters);
    public abstract void movement(double deltatime, List<ACharacter> characters);
}
