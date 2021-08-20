package fa.training.util;

import java.sql.*;

public class ConnectionJDBC {

    private static final String DRIVER_URL = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER = "jdbc:mysql://";
    private static final String HOST = "localhost:";
    private static final String BACKWARD = "/";
    private static final String DB_NAME = "SMS";
    private static final String USER_NAME = "root";
    private static final String USER_PASS = "matkhau1995";
    private static final int PORT = 3306;

    /**
     * Google translate
     * Get a connection to database
     * @return Connection
     */
    public static Connection getJDBCConnection() {

        try {
            Class.forName(DRIVER_URL);
            return DriverManager.getConnection(DRIVER + HOST + PORT + BACKWARD + DB_NAME, USER_NAME, USER_PASS);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

}