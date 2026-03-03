package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import javafx.scene.image.Image;

public class NormalProjectileFactory implements IProjectileFactory {
    @Override
    public AProjectile create(double fromX, double fromY, double toX, double toY, Image img) {
        return new NormalAProjectile(img, fromX, fromY, toX, toY);
    }

}
