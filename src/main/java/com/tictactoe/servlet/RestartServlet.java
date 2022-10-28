package com.tictactoe.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RestartServlet", value = "/restart")
public class RestartServlet extends HttpServlet implements LevelDeterminable {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String level = getLevel(req);
        req.getSession().invalidate();
        resp.sendRedirect("/start?level=" + level);
    }

    private String getLevel(HttpServletRequest req) {
        String level = req.getParameter("level");
        if (level != null && !level.equals("undefined")) return level;
        return req.getSession(true).getAttribute("level").toString();
    }
}
