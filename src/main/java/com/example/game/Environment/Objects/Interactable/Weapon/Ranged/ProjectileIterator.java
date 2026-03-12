package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectileIterator implements Iterator<AProjectile> {
    private final List<AProjectile> projectiles;
    private int position = 0, last_returned_index = -1;

    public ProjectileIterator(List<AProjectile> projectilesList) {
        this.projectiles = projectilesList;
    }

    @Override
    public boolean hasNext() {
        return position < projectiles.size();
    }

    @Override
    public AProjectile next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more projectiles");
        }
        last_returned_index = position;
        return projectiles.get(position++);
    }

    @Override
    public void remove() {
        if (last_returned_index < 0) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        projectiles.remove(last_returned_index);
        position = last_returned_index;
        last_returned_index = -1;
    }
}
