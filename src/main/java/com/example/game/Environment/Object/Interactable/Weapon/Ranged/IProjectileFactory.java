package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import javafx.scene.image.Image;

public interface IProjectileFactory {
    AProjectile create(double fromX, double fromY, double toX, double toY, Image img);
}
