package com.example.game.Weapon.CloseRange;

import com.example.game.Player;
import com.example.game.Weapon.AWeapon;

public abstract class AMeleeWeapon extends AWeapon {
    abstract void slash(double deltaTime, Player plr);
}
