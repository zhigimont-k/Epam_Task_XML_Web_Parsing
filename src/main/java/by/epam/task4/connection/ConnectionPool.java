package by.epam.task4.connection;

import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static ConnectionPool instance = null;
    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> unavailableConnections;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static final String NUMERICAL_PATTERN = "\\d+";
    private static final int DEFAULT_POOL_SIZE = 5;

    private ConnectionPool() {
    }

    public void initPool() {
        try {
            availableConnections = new LinkedBlockingQueue<>();
            unavailableConnections = new LinkedBlockingQueue<>();
            for (int i = 0; i < this.poolSize; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                availableConnections.add(connection);
            }
        } catch (SQLException e) {
            logger.fatal("Couldn't init connection pool.", e);
            throw new RuntimeException("Couldn't init connection pool.", e);
        }
    }

    public void registerDriver() throws ConnectionPoolException {
        ResourceBundle bundle = ResourceBundle.getBundle(ConnectionData.BASE_NAME);
        driver = bundle.getString(ConnectionData.DATABASE_DRIVER);
        url = bundle.getString(ConnectionData.DATABASE_URL);
        user = bundle.getString(ConnectionData.DATABASE_USER);
        password = bundle.getString(ConnectionData.DATABASE_PASSWORD);
        String poolSize = bundle.getString(ConnectionData.DATABASE_POOL_SIZE);
        Pattern pattern = Pattern.compile(NUMERICAL_PATTERN);
        Matcher matcher = pattern.matcher(poolSize);
        this.poolSize = (matcher.matches()) ? Integer.parseInt(poolSize) : DEFAULT_POOL_SIZE;

        try {
            DriverManager.registerDriver(DriverManager.getDriver(url));
        } catch (SQLException e) {
            throw new ConnectionPoolException("Couldn't register driver", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } catch (Exception e) {
                logger.error(e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = availableConnections.take();
            unavailableConnections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Couldn't get connection", e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) throws ConnectionPoolException{
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            unavailableConnections.remove(connection);
            availableConnections.put(connection);
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public void closeConnectionPool() throws ConnectionPoolException{
        try {
            closeConnectionQueue(unavailableConnections);
            closeConnectionQueue(availableConnections);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Couldn't close connection queue", e);
        }
    }

    private void closeConnectionQueue(BlockingQueue<ProxyConnection> queue) throws SQLException{
        ProxyConnection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }
}
