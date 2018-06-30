package by.epam.task4.controller;

import by.epam.task4.command.Command;
import by.epam.task4.command.CommandFactory;
import by.epam.task4.command.UnknownCommandException;
import by.epam.task4.util.JspAddress;
import by.epam.task4.util.JspParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandName = request.getParameter(JspParameter.COMMAND);

        try {
            Command command = CommandFactory.getInstance().initCommand(commandName);
            command.execute(request, response);
        } catch (UnknownCommandException e) {
            request.setAttribute(JspParameter.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(JspAddress.ERROR).forward(request, response);
        }
    }
}
