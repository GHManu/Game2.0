package com.example.game.Weapon.Ranged;

import com.example.game.AEntity;

public abstract class AProjectile extends AEntity {
    double directionX, directionY;
    double xDest, yDest;
    protected static final double margine = 2.0;
    protected double normal_damage = 0.2;
    protected final double speed = 2.5;

    protected abstract void journey(double deltaTime, double speed);
    public final boolean isArrived(double xDest, double yDest){
        double dx = this.xDest - getX();
        double dy = this.yDest - getY();
        return Math.sqrt(dx * dx + dy * dy) <= margine;
    }

    public double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public double getxDest() {
        return xDest;
    }

    public void setxDest(double xDest) {
        this.xDest = xDest;
    }

    public double getyDest() {
        return yDest;
    }

    public void setyDest(double yDest) {
        this.yDest = yDest;
    }
}
