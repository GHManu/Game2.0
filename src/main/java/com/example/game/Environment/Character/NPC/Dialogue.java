package com.example.game.Environment.Character.NPC;

public class Dialogue {
    private final String[] lines;
    private int index = 0;
    public Dialogue(String... lines) {
        this.lines = lines;
    }
    public boolean hasNext() { return index < lines.length; }
    public String next() {
        return hasNext() ? lines[index++] : null;
    }
    public void reset() { index = 0; }
}
