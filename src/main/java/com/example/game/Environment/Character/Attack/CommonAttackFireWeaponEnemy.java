package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Collider;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.*;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyEnemy;
import com.example.game.UI.EGameImages;


public class CommonAttackFireWeaponEnemy extends ACommonAttack implements IFightStrategyEnemy {
    private final AFireWeapon fw;
    private final ProjectileManager projectileManager = new ProjectileManager();
    private final CollisionResolver collisionResolver = new CollisionResolver();
    private final IProjectileFactory projectileFactory;

    public CommonAttackFireWeaponEnemy(AFireWeapon fw, IProjectileFactory projectileFactory){
        this.fw = fw;
        this.projectileFactory = projectileFactory;
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

            AProjectile p = projectileFactory.create(enemy.getX(), enemy.getY(), player.getX(), player.getY(),EGameImages.ProvaAttaccoEnemy.getImage());
            projectileManager.spawnProjectile(player.root, p);

            fw.getMag().set(0, p);

        }


    }
}
