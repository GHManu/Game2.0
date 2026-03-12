package com.example.game.Environment;


import com.example.game.Environment.Character.ACharacter;
import com.example.game.UI.HUD;
import javafx.scene.Group;

public class Destroyer {
    private final Group world;
    public Destroyer(Group world) {
        this.world = world;
    }
    public void destroyCharacter(ACharacter c) {
        if(c != null && c.getCollider().getShape() != null) {
            HUD.removeElement(world, c.getVbox());
            HUD.removeElement(world, c.getCollider().getShape());
            HUD.removeElement(world, c.getImg_view());
            System.gc();
        }
    }
}
