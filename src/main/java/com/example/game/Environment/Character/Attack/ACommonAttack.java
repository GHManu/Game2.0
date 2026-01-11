package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategy;
import com.example.game.UI.HUD;

public abstract class ACommonAttack implements IFightStrategy {
    public void applyDamage(ACharacter target, double amount) {
        target.takeDamage(amount);
        HUD.updateProgressBar(target, amount);
    }

}
