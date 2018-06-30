package by.epam.task4.dao.user.impl;

import by.epam.task4.connection.ConnectionPool;
import by.epam.task4.connection.ConnectionPoolException;
import by.epam.task4.connection.ProxyConnection;
import by.epam.task4.dao.DAOException;
import by.epam.task4.dao.user.UserDAO;
import by.epam.task4.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String REGISTER_QUERY = "INSERT INTO user (id, login, password)"
            + " VALUES (?, ?, SHA1(?))";
    private static final String CHECK_PASSWORD_QUERY = "SELECT user.id FROM user WHERE user.login = ? " +
            "AND user.password = SHA1(?)";
    private static final String CHECK_USER_EXISTS = "SELECT user.id FROM user WHERE user.login = ?";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public boolean register(User user) throws DAOException {
        ProxyConnection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            String login = user.getLogin();
            String password = user.getPassword();

            if (userExists(connection, login)){
                return false;
            }

            preparedStatement = connection.prepareStatement(REGISTER_QUERY);


            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int userID = resultSet.getInt(1);

            user.setId(userID);

            return true;
        } catch (SQLException e) {
            throw new DAOException("Failed to register user", e);
        } finally {
            try {
                pool.releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }
        }
    }

    public boolean login(User user) throws DAOException {
        ProxyConnection connection = null;
        try {
            connection = pool.getConnection();
            String login = user.getLogin();
            String password = user.getPassword();

            return passwordMatches(connection, login, password);
        } catch (SQLException e) {
            throw new DAOException("Failed to login user", e);
        } finally {
            try {
                pool.releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }
        }
    }

    private boolean userExists(ProxyConnection connection, String login) throws DAOException{
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(CHECK_USER_EXISTS);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            return !resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                pool.releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }
        }
    }

    private boolean passwordMatches(ProxyConnection connection, String login, String password) throws DAOException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(CHECK_PASSWORD_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                pool.releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }
        }
    }
}
