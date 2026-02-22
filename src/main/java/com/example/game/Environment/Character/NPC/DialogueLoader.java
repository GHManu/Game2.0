package com.example.game.Environment.Character.NPC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogueLoader {

    public static Map<String, Dialogue> loadAll(String path) {
        Map<String, List<String>> groups = new HashMap<>();
        String currentKey = null;

        try {
            for (String line : Files.readAllLines(Path.of(path))) {
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentKey = line.substring(1, line.length() - 1);
                    groups.put(currentKey, new ArrayList<>());
                } else if (currentKey != null && !line.isBlank()) {
                    groups.get(currentKey).add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Dialogue> dialogues = new HashMap<>();
        for (var entry : groups.entrySet()) {
            dialogues.put(entry.getKey(),
                    new Dialogue(entry.getValue().toArray(new String[0])));
        }
        return dialogues;
    }

}

