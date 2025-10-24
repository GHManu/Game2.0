package com.example.game;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public abstract class AFireWeapon extends AWeapon implements IFightStrategy{
    abstract void shot(double deltaTime, Player plr, Projectile p);
    abstract void movement(double deltaTime, Player plr, Projectile p);
}
