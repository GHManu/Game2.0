package com.example.game.Environment.Object.Interactable.Weapon.CloseRange;

import com.example.game.Environment.Character.Playable.Player;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;

public abstract class AMeleeWeapon extends AWeapon {
    abstract void slash(double deltaTime, Player plr);
}
