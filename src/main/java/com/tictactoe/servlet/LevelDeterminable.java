package com.tictactoe.servlet;

import com.tictactoe.model.Level;

import javax.servlet.http.HttpSession;

public interface LevelDeterminable {

    default Level getLevel(HttpSession currentSession) {
        Object levelAttribute = currentSession.getAttribute("level");
        try {
            return Level.valueOf(levelAttribute.toString());
        } catch (IllegalArgumentException e) {
            return Level.LOW;
        }
    }
}
