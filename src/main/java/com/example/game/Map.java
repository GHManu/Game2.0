package com.example.game;
import javafx.scene.Group;

import java.awt.*;
import java.util.HashMap;

public class Map extends AEntity{
    private HashMap<Point, Integer> tile_map;

    public Map() {
        tile_map = new HashMap<>();

        //  muri (1) e erba (0)
        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 16; c++) {
                if (r == 0 || r == 11 || c == 0 || c == 15) {
                    tile_map.put(new Point(c, r), 1); // muro
                } else {
                    tile_map.put(new Point(c, r), 0); // erba
                }
            }
        }

        // acqua (2), 7 strati
        int layers = 7;

        // sopra
        for (int r = -layers; r < 0; r++) {
            for (int c = -layers; c < 16 + layers; c++) {
                tile_map.put(new Point(c, r), 2);
            }
        }
        // sotto
        for (int r = 12; r < 12 + layers; r++) {
            for (int c = -layers; c < 16 + layers; c++) {
                tile_map.put(new Point(c, r), 2);
            }
        }
        // sinistra
        for (int c = -layers; c < 0; c++) {
            for (int r = -layers; r < 12 + layers; r++) {
                tile_map.put(new Point(c, r), 2);
            }
        }
        // destra
        for (int c = 16; c < 16 + layers; c++) {
            for (int r = -layers; r < 12 + layers; r++) {
                tile_map.put(new Point(c, r), 2);
            }
        }
    }

    protected void drawMap(Group root) {
        for (Point p : tile_map.keySet()) {
            int value = tile_map.get(p);
            switch (value) {
                case 1: // muro
                    Wall wall = new Wall(p.x, p.y);
                    root.getChildren().add(wall.getCld().ret);
                    root.getChildren().add(wall.getImgView());
                    break;
                case 2: // acqua
                    Water water = new Water(p.x, p.y);
                    root.getChildren().add(water.getCld().ret);
                    root.getChildren().add(water.getImgView());
                    break;
                default: // erba
                    Grass grass = new Grass(p.x, p.y);
                    root.getChildren().add(grass.getCld().ret);
                    root.getChildren().add(grass.getImgView());
                    break;
            }
        }
    }

    @Override
    public void update(AStatsObject statsObject) {

    }
}
