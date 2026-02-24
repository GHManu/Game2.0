package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Collider;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.CollisionResolver;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyEnemy;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.ProjectileManager;
import com.example.game.UI.EGameImages;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AProjectile;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.NormalAProjectile;


public class CommonAttackFireWeaponEnemy extends ACommonAttack implements IFightStrategyEnemy {
    private final AFireWeapon fw;
    private final ProjectileManager projectileManager = new ProjectileManager();
    private final CollisionResolver collisionResolver = new CollisionResolver();

    public CommonAttackFireWeaponEnemy(AFireWeapon fw){
        this.fw = fw;
    }

    @Override
    public void normalAttack(double dt, ACharacterEnemy subject, ACharacterPlayable target) {

        initAttack(dt, subject, target);

        if (subject.isAlive() &&
                !subject.isAttack_flag() &&
                target.isAlive()) {

            projectileManager.updateProjectile(dt, fw);

            AProjectile p = fw.getProjectile();
            Collider projectileCld = p.getCld();


            if (p.isArrived(target.getX(), target.getY())) {
                projectileManager.removeProjectile(target.root, p);
                subject.setAttack_flag(true);
                return;
            }


            if (projectileCld.intersect(target.getCld().getShape())) {

                double speed = target.getSpeed();
                double rebound = subject.getREBOUND();

                for (Direction d : Direction.values()) {
                    if (target.getCld().canHit(d))
                        collisionResolver.applyRebound(target, d, speed, rebound);
                }

                projectileManager.removeProjectile(target.root, p);
                applyDamage(target, 0.2);

                subject.setAttack_flag(true);
            }
        }

        if (!subject.isAlive() &&
                !fw.getProjectile().isArrived(target.getX(), target.getY())) {
            projectileManager.removeProjectile(target.root, fw.getProjectile());
        }
    }

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if (enemy.isAttack_flag() && player.isAlive() && enemy.isAlive()) {

            enemy.setAttack_flag(false);

            AProjectile p = new NormalAProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), enemy.getX(), enemy.getY(), player.getX(), player.getY());

            projectileManager.spawnProjectile(player.root, p);

            ((AFireWeapon) enemy.getWeapon()).getMag().set(0, p);

        }


    }
}
