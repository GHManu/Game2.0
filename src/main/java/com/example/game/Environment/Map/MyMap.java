package com.example.game.Environment.Map;
import com.example.game.Environment.AEntity;
import com.example.game.Environment.Collider;
import com.example.game.Environment.Object.NonInteractable.Grass;
import com.example.game.Environment.Object.NonInteractable.Wall;
import com.example.game.Environment.Object.NonInteractable.Water;
import com.example.game.UI.HUD;
import javafx.scene.Group;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyMap extends AEntity {
    private static HashMap<Point, Integer> tile_map;
    private static List<Collider> wallColliders = new ArrayList<>();

    public static List<Collider> getWallColliders() { return wallColliders; }

    public MyMap(String filename) {
        tile_map = new HashMap<>();
        loadFromFile(filename);
    }

    private void loadFromFile(String filename) {
        try (InputStream is = getClass().getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int r = 0;
            while ((line = br.readLine()) != null) {
                for (int c = 0; c < line.length(); c++) {
                    char ch = line.charAt(c);
                    int value = Character.getNumericValue(ch);
                    tile_map.put(new Point(c, r), value);
                }
                r++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(Group root) {
        for (Point p : tile_map.keySet()) {
            int value = tile_map.get(p);
            switch (value) {
                case 1: // muro
                    Wall wall = new Wall(p.x, p.y);
                    wallColliders.add(wall.getCld());
                    HUD.addElement(root,wall.getCld().getShape());
                    HUD.addElement(root,wall.getImgView());
                    break;
                case 2: // acqua
                    Water water = new Water(p.x, p.y);
                    HUD.addElement(root,water.getCld().getShape());
                    HUD.addElement(root,water.getImgView());
                    break;
                default: // erba
                    Grass grass = new Grass(p.x, p.y);
                    HUD.addElement(root,grass.getCld().getShape());
                    HUD.addElement(root,grass.getImgView());
                    break;
            }
        }
    }

}
