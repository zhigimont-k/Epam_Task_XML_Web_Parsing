package by.epam.task4.service;

import by.epam.task4.dao.DAOException;
import by.epam.task4.dao.user.impl.UserDAOImpl;
import by.epam.task4.entity.User;

public class UserService {

    public boolean registerUser(String name, String password) throws ServiceException {
        UserDAOImpl dao = new UserDAOImpl();
        User newUser;
        try {
            newUser = new User(name, password);
            return dao.register(newUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean userLogin(String name, String password) throws ServiceException{
        UserDAOImpl dao = new UserDAOImpl();
        User user;
        try {
            user = new User(name, password);
            return dao.login(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
