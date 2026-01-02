package com.example.game;

public class AttackFireWeaponPlayer implements IFightStrategy{

    private void removeProjectile(NormalProjectile p, ACharacterPlayable player, ProjectileIterator it) {
        EventBus.get().notifyEventListenerObserver(new DTOEvent(EEventType.REMOVE_ELEMENT, new UIDTO(player.root, p.getImgView())));
        it.remove();
    }

    private void applyDamage(ACharacterEnemy enemy, NormalProjectile p) {
        double newHealth = enemy.getHealth() - (enemy.getInitial_Health() * p.normal_damage);
        enemy.setHealth(newHealth);
        enemy.setSpeed(enemy.getSpeed() + 0.2);
        EventBus.get().notifyEventListenerObserver(new DTOEvent(EEventType.DAMAGED, new DamageData(enemy, p.normal_damage)));
    }


    @Override
    public void normalAttack(double dt, ACharacterEnemy enemy, ACharacterPlayable player) {
        initAttack(dt, enemy, player);
        AFireWeapon fw = (AFireWeapon) player.getWeapon();
        AFireWeapon fwEnemy = (AFireWeapon) enemy.getWeapon();
        ProjectileIterator it = new ProjectileIterator(fw.getMag());
        while (it.hasNext()) {
            NormalProjectile p = (NormalProjectile) it.next();
            fw.p = p;
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
        if (player.isInit_attack_flag() && player.getWeapon() instanceof AFireWeapon fw) {
            NormalProjectile p = new NormalProjectile( EGameImages.ProvaAttacco.getImage(),
                    player.getImgView().getLayoutX(),
                    player.getImgView().getLayoutY(),
                    player.getxDest(),
                    player.getyDest() );
            player.setAttack_flag(true);
            fw.getMag().add(p);
            EventBus.get().notifyEventListenerObserver(new DTOEvent(EEventType.ADD_ELEMENT, new UIDTO(player.root, p.getImgView())));
        }
        player.setInit_attack_flag(false);
    }


}
