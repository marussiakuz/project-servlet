package com.tictactoe.servlet;

import com.tictactoe.model.Field;
import com.tictactoe.model.Level;
import com.tictactoe.model.Sign;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        Field field = new Field();
        Map<Integer, Sign> fieldData = field.getField();
        List<Sign> data = field.getFieldData();
        currentSession.setAttribute("field", field);
        currentSession.setAttribute("data", data);
        Level level = getLevel(req);
        currentSession.setAttribute("level", level == null ? getLevel(req) : level);
        if (level == Level.MIDDLE) currentSession.setAttribute("thoughtful", true);
        getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

    private Level getLevel(HttpServletRequest request) {
        String level = request.getParameter("level");
        if (level == null) return Level.LOW;
        try {
            return Level.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Level.LOW;
        }
    }
}
