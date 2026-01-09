package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.UI.EGameImages;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategy;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.ProjectileIterator;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.NormalAProjectile;
import com.example.game.UI.HUD;

public class AttackFireWeaponPlayer implements IFightStrategy {
    private AFireWeapon fw;

    public AttackFireWeaponPlayer(AFireWeapon fw) {
        this.fw = fw;
    }

    private void removeProjectile(NormalAProjectile p, ACharacterPlayable player, ProjectileIterator it) {
        HUD.removeElement(player.root, p.getImgView());
        it.remove();
    }

    private void applyDamage(ACharacterEnemy enemy, NormalAProjectile p) {
        double newHealth = enemy.getHealth() - (enemy.getInitial_Health() * p.normal_damage);
        enemy.setHealth(newHealth);
        enemy.setSpeed(enemy.getSpeed() + 0.2);
        HUD.updateProgressBar(enemy, p.normal_damage);
    }


    @Override
    public void normalAttack(double dt, ACharacterEnemy enemy, ACharacterPlayable player) {
        initAttack(dt, enemy, player);
        AFireWeapon fwEnemy = (AFireWeapon) enemy.getWeapon();
        ProjectileIterator it = new ProjectileIterator(fw.getMag());
        while (it.hasNext()) {
            NormalAProjectile p = (NormalAProjectile) it.next();
            fw.setProjectile(p);
            player.getWeapon().fight(dt);
            if (p.isArrived(player.getxDest(), player.getyDest())) {
                removeProjectile(p, player, it);
                continue;
            }
            if (enemy.getCld().getShape() != null && p.getCld().getShape().intersects(enemy.getCld().getShape().getBoundsInLocal())) {
                if (enemy.getHealth() > 1) {
                    applyDamage(enemy, p);
                }
                removeProjectile(p, player, it);
                continue;
            }
            if (!fwEnemy.getMag().isEmpty() && p.getCld().getShape().intersects(fwEnemy.getMag().getFirst().getCld().getShape().getBoundsInLocal())) {
                removeProjectile(p, player, it);
            }
        }
        if (fw.getMag().isEmpty()) player.setAttack_flag(false); }

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if (player.getProgressBar().getProgress() <= 0) return;
        if (player.isInit_attack_flag()) {
            NormalAProjectile p = new NormalAProjectile( EGameImages.ProvaAttacco.getImage(),
                    player.getImgView().getLayoutX(),
                    player.getImgView().getLayoutY(),
                    player.getxDest(),
                    player.getyDest() );
            player.setAttack_flag(true);
            fw.getMag().add(p);
            HUD.addElement(player.root, p.getImgView());
        }
        player.setInit_attack_flag(false);
    }


}
