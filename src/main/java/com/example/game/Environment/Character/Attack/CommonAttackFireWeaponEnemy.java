package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Objects.Interactable.Weapon.IFightStrategyEnemy;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.*;
import com.example.game.Environment.Collider;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.UI.EGameImages;


public class CommonAttackFireWeaponEnemy extends ACommonAttack implements IFightStrategyEnemy {
    private final AFireWeapon fw;
    private final ProjectileManager projectile_manager = new ProjectileManager();
    private final CollisionResolver collision_resolver = new CollisionResolver();
    private final IProjectileFactory projectile_factory;

    public CommonAttackFireWeaponEnemy(AFireWeapon fw, IProjectileFactory projectile_factory){
        this.fw = fw;
        this.projectile_factory = projectile_factory;
    }

    @Override
    public void normalAttack(double dt, ACharacterEnemy subject, ACharacterPlayable target) {

        initAttack(dt, subject, target);

        if (subject.isAlive() &&
                !subject.isAttack_flag() &&
                target.isAlive()) {

            projectile_manager.updateProjectile(dt, fw);

            AProjectile p = fw.getProjectile();
            Collider projectileCld = p.getCollider();


            if (p.isArrived(target.getX(), target.getY())) {
                projectile_manager.removeProjectile(target.root, p);
                subject.setAttack_flag(true);
                return;
            }


            if (projectileCld.intersect(target.getCollider().getShape())) {

                double speed = target.getSpeed();
                double rebound = subject.getREBOUND();

                for (Direction d : Direction.values()) {
                    if (target.getCollider().canHit(d))
                        collision_resolver.applyRebound(target, d, speed, rebound);
                }

                projectile_manager.removeProjectile(target.root, p);
                applyDamage(target, 0.2);

                subject.setAttack_flag(true);
            }
        }

        if (!subject.isAlive() &&
                !fw.getProjectile().isArrived(target.getX(), target.getY())) {
            projectile_manager.removeProjectile(target.root, fw.getProjectile());
        }
    }

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if (enemy.isAttack_flag() && player.isAlive() && enemy.isAlive()) {

            enemy.setAttack_flag(false);

            AProjectile p = projectile_factory.create(enemy.getX(), enemy.getY(), player.getX(), player.getY(),EGameImages.ProvaAttaccoEnemy.getImage());
            projectile_manager.spawnProjectile(player.root, p);

            fw.getMag().set(0, p);

        }


    }
}
