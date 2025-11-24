package com.example.game;
import javafx.scene.Group;
public class Map extends AEntity{
    int[][] tile_map;
    public Map(){
        tile_map = new int[][]{ //12x16, 0 = libero, 1 = wall
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    protected void drawMap(Group root){
        for(int r = 0; r < IScreenSettings.maxScreenHeight; r++){
            for(int c = 0; c < IScreenSettings.maxScreenWidth; c++){
                if (tile_map[r][c] == 1){
                    Wall wall = new Wall(c,r);
                    root.getChildren().addLast(wall.getCld().ret);
                    root.getChildren().addLast(wall.getImgView());
                }
                else{
                    Grass grass = new Grass(c,r);
                    root.getChildren().addLast(grass.getCld().ret);
                    root.getChildren().addLast(grass.getImgView());
                }
            }
        }
    }

}
