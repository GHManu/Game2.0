package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;

import java.util.List;

public class CollisionHandler {

        public void handle(List<ACharacter> characters) {
            for (ACharacter c1 : characters)
                for (ACharacter c2 : characters)
                    if (c1 != c2)
                        c1.getCld().collisionDetected(c2.getCld().getShape(), true);
        }

}
