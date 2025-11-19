package com.example.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileIterator implements Iterator<Projectile> {
    List<Projectile> projectilesList;
    int position = 0;

    public ProjectileIterator(List<Projectile> original_projectiles_list){
        this.projectilesList = original_projectiles_list;
    }

    @Override
    public boolean hasNext() {
        return position != this.projectilesList.size();
    }

    @Override
    public Projectile next() {
        Projectile projectile = projectilesList.get(position);
        position += 1;
        return projectile;
    }

    @Override
    public void remove() {
        if(!projectilesList.isEmpty() && projectilesList.get(position) != null)
            projectilesList.set(position, null);
    }
}
