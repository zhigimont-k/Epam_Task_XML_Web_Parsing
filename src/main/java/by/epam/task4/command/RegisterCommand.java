package by.epam.task4.command;

import by.epam.task4.entity.User;
import by.epam.task4.service.ServiceException;
import by.epam.task4.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

            UserService service = new UserService();
            User newUser = service.registerUser(login, password);
    }
}
