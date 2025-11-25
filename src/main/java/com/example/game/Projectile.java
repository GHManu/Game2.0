package com.example.game;

public abstract class Projectile extends AEntity {
    double directionX, directionY;
    double xDest, yDest;
    protected static final double margine = 2.0;
    protected double normal_damage = 0.2;   //in percentuale
    protected final double speed = 2.5;

    protected abstract void journey(double deltaTime, double speed);
    public final boolean isArrived(double xDest, double yDest){
        double dx = this.xDest - getX();
        double dy = this.yDest - getY();
        return Math.sqrt(dx * dx + dy * dy) <= margine;
    }
}
