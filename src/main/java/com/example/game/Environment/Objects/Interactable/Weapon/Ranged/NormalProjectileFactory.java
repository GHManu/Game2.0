package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import javafx.scene.image.Image;

public class NormalProjectileFactory implements IProjectileFactory {
    @Override
    public AProjectile create( double from_x, double from_y, double to_x, double to_y, Image img) {

            return new NormalAProjectile(img, from_x, from_y, to_x, to_y);
    }

}
