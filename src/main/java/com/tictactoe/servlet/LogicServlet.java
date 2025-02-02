package com.tictactoe.servlet;

import com.tictactoe.model.Field;
import com.tictactoe.model.Level;
import com.tictactoe.model.Sign;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet implements LevelDeterminable {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        Field field = extractField(currentSession);
        int index = getSelectedIndex(req);

        Sign currentSign = field.getField().get(index);
        if (Sign.EMPTY != currentSign || currentSession.getAttribute("winner") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        field.getField().put(index, Sign.CROSS);
        if (checkWin(req, resp, currentSession, field)) {
            return;
        }

        Level level = getLevel(currentSession);
        Boolean isThoughtfulStep = null;
        if (level == Level.MIDDLE) {
            isThoughtfulStep = getThoughtfulStep(currentSession);
            currentSession.setAttribute("thoughtful", !isThoughtfulStep);
        }

        int emptyFieldIndex = (level == Level.LOW || Boolean.FALSE.equals(isThoughtfulStep)) ?
                field.getEmptyFieldIndex() : field.getTheBestFieldIndex();

        if (emptyFieldIndex >= 0) {
            field.getField().put(emptyFieldIndex, Sign.NOUGHT);
            if (checkWin(req, resp, currentSession, field)) {
                return;
            }
        } else {
            currentSession.setAttribute("draw", true);
            List<Sign> data = field.getFieldData();
            currentSession.setAttribute("data", data);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        List<Sign> data = field.getFieldData();

        currentSession.setAttribute("data", data);
        currentSession.setAttribute("field", field);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
        dispatcher.forward(req, resp);
    }

    private Field extractField(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute("field");
        if (Field.class != fieldAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (Field) fieldAttribute;
    }

    private boolean getThoughtfulStep(HttpSession currentSession) {
        Object isThoughtfulStepAttribute = currentSession.getAttribute("thoughtful");
        return (Boolean) isThoughtfulStepAttribute;
    }

    private int getSelectedIndex(HttpServletRequest request) {
        String click = request.getParameter("click");
        boolean isNumeric = click.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(click) : 0;
    }

    private boolean checkWin(HttpServletRequest request, HttpServletResponse response, HttpSession currentSession,
                             Field field) throws IOException, ServletException {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.NOUGHT == winner) {
            currentSession.setAttribute("winner", winner);

            List<Sign> data = field.getFieldData();

            currentSession.setAttribute("data", data);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
            dispatcher.forward(request, response);
            return true;
        }
        return false;
    }
}
