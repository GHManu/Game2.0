package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import com.example.game.Environment.AEntity;
import javafx.application.Platform;

public abstract class AProjectile extends AEntity {
    double direction_x, direction_y;
    double x_dest, y_dest;
    protected static final double margine = 2.0;
    public double normal_damage = 0.2;
    protected final double speed = 2.5;
    private EProjectileType type;
    public AProjectile( EProjectileType type){
        this.setProjectileType(type);
    }

    protected void journey(double deltaTime, double speed){

        double x = getX(), y = getY();
        x += deltaTime * speed * this.getDirection_x();
        setX(x);
        y +=  deltaTime * speed * this.getDirection_y();
        setY(y);

        Platform.runLater(() -> {
            this.getImg_view().setTranslateX(this.getX());
            this.getImg_view().setTranslateY(this.getY());
            this.getCollider().getShape().setX(this.getX());
            this.getCollider().getShape().setY(this.getY());

        });

    }
    public final boolean isArrived(double xDest, double yDest){
        double dx = this.x_dest - getX();
        double dy = this.y_dest - getY();
        return Math.sqrt(dx * dx + dy * dy) <= margine;
    }

    public double getDirection_x() {
        return direction_x;
    }

    public void setDirection_x(double direction_x) {
        this.direction_x = direction_x;
    }

    public double getDirection_y() {
        return direction_y;
    }

    public void setDirection_y(double direction_y) {
        this.direction_y = direction_y;
    }

    public double getX_dest() {
        return x_dest;
    }

    public void setX_dest(double x_dest) {
        this.x_dest = x_dest;
    }

    public double getY_dest() {
        return y_dest;
    }

    public void setY_dest(double y_dest) {
        this.y_dest = y_dest;
    }
    public void setProjectileType(EProjectileType type){
        this.type = type;
    }
    public EProjectileType getProjectileType(){
        return this.type;
    }
}
