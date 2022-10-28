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

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        Field field = new Field();
        List<Sign> data = field.getFieldData();
        Level level = getLevel(req);

        currentSession.setAttribute("field", field);
        currentSession.setAttribute("data", data);
        currentSession.setAttribute("level",level);
        if (level == Level.MIDDLE) currentSession.setAttribute("thoughtful", true);

        getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

    private Level getLevel(HttpServletRequest request) {
        Object levelAttribute = request.getSession(true).getAttribute("level");

        if (levelAttribute != null) {
            return getLevel(levelAttribute.toString());
        }

        String level = request.getParameter("level");
        if (level == null) return Level.LOW;
        return getLevel(level);
    }

    private Level getLevel(String name) {
        try {
            return Level.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Level.LOW;
        }
    }
}
