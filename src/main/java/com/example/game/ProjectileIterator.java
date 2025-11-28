package com.example.game;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectileIterator implements Iterator<Projectile> {
    private final List<Projectile> projectilesList;
    private int position = 0, lastReturnedIndex = -1;

    public ProjectileIterator(List<Projectile> projectilesList) {
        this.projectilesList = projectilesList;
    }

    @Override
    public boolean hasNext() {
        return position < projectilesList.size();
    }

    @Override
    public Projectile next() {
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
