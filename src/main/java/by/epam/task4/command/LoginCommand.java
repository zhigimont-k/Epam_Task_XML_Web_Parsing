package by.epam.task4.command;

import by.epam.task4.entity.User;
import by.epam.task4.service.ServiceException;
import by.epam.task4.service.UserService;
import by.epam.task4.util.JspAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            UserService service = new UserService();
            service.userLogin(login, password);
            request.getRequestDispatcher(JspAddress.MAIN).forward(request, response);
        } catch (ServiceException e){
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(JspAddress.ERROR).forward(request, response);
        }
    }
}
