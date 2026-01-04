package com.example.game.Weapon.Ranged;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectileIterator implements Iterator<AProjectile> {
    private final List<AProjectile> projectilesList;
    private int position = 0, lastReturnedIndex = -1;

    public ProjectileIterator(List<AProjectile> projectilesList) {
        this.projectilesList = projectilesList;
    }

    @Override
    public boolean hasNext() {
        return position < projectilesList.size();
    }

    @Override
    public AProjectile next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more projectiles");
        }
        lastReturnedIndex = position;
        return projectilesList.get(position++);
    }

    @Override
    public void remove() {
        if (lastReturnedIndex < 0) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        projectilesList.remove(lastReturnedIndex);
        position = lastReturnedIndex;
        lastReturnedIndex = -1;
    }
}
