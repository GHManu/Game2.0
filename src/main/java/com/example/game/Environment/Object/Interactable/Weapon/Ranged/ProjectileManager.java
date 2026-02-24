package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.Environment.Collider;
import com.example.game.UI.HUD;
import javafx.application.Platform;
import javafx.scene.Group;

public class ProjectileManager {

    public void spawnProjectile(Group root, AProjectile p) {
        Platform.runLater(() -> HUD.addElement(root, p.getImgView()));
    }

    public void removeProjectile(Group root, AProjectile p) {
        Platform.runLater(() -> HUD.removeElement(root, p.getImgView()) );
    }
    public void removeProjectile(Group root, AProjectile p, ProjectileIterator it) {
        it.remove();
        Platform.runLater(() -> HUD.removeElement(root, p.getImgView()) );
    }


    public void updateProjectile(double dt, AFireWeapon weapon) {
        weapon.setProjectile(weapon.getMag().getFirst());
        weapon.fight(dt);

        Collider projectileCld = weapon.getProjectile().getCld();
        projectileCld.collisionDetected(projectileCld.getShape(), false);

    }
}
