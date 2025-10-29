package com.example.game;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public abstract class AFireWeapon extends AWeapon {
    Projectile p;
    abstract void shot(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy);
    abstract void movement(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy);
}
