package by.epam.task4.command;

import by.epam.task4.service.ChangeLocaleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("local", lang);
        try {
            request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
        } catch (IOException e){
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }

    }
}
