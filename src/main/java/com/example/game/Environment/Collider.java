package com.example.game.Environment;

import com.example.game.Environment.Character.Movement.Direction;
import javafx.scene.shape.Rectangle;


public class Collider {

    private Rectangle shape;
    private boolean can_hit_right = true;
    private boolean can_hit_left = true;
    private boolean can_hit_up = true;
    private boolean can_hit_down = true;

    public Collider(double x, double y, double width, double height) {
        this.shape = new Rectangle(x, y, width, height);
        this.shape.setArcHeight(10);
        this.shape.setArcWidth(10);
    }

    public Rectangle getShape() {
        return shape;
    }
    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public boolean canHit(Direction dir) {
        return switch (dir) {
            case RIGHT -> can_hit_right;
            case LEFT  -> can_hit_left;
            case UP    -> can_hit_up;
            case DOWN  -> can_hit_down;
        };
    }

    public void reset() {
        can_hit_right = can_hit_left = can_hit_up = can_hit_down = true;
    }

    public void collisionDetected(Rectangle other, boolean block) {
        if (other == null || shape == null) return;

        if (!shape.intersects(other.getBoundsInParent())) {
            reset();
            return;
        }

        double deltaX = shape.getX() - other.getX();
        double deltaY = shape.getY() - other.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            handleHorizontalCollision(other, deltaX, block);
        } else {
            handleVerticalCollision(other, deltaY, block);
        }
    }

    private void handleHorizontalCollision(Rectangle other, double deltaX, boolean block) {
        if (deltaX < 0) {
            can_hit_left = false;
            if (block) shape.setX(other.getX() - shape.getWidth());
        } else {
            can_hit_right = false;
            if (block) shape.setX(other.getX() + other.getWidth());
        }
    }

    private void handleVerticalCollision(Rectangle other, double deltaY, boolean block) {
        if (deltaY < 0) {
            can_hit_down = false;
            if (block) shape.setY(other.getY() - shape.getHeight());
        } else {
            can_hit_up = false;
            if (block) shape.setY(other.getY() + other.getHeight());
        }
    }
}

