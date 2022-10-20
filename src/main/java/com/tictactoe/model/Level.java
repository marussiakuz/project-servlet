package com.tictactoe.model;

public enum Level {
    LOW("LOW"),
    MIDDLE("MIDDLE"),
    ADVANCED("ADVANCED");

    private final String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
