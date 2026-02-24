package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyPlayer;
import com.example.game.UI.HUD;

public abstract class ACommonAttack {
    public void applyDamage(ACharacter target, double amount) {
        target.takeDamage(amount);
        HUD.updateProgressBar(target, amount);
    }

}
