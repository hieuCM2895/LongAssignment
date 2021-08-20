package fa.training.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Build properties of connection to reuse
 */
public class ConnectionManager {

    public static boolean AUTO_COMMIT = true;
    public static Connection connect = ConnectionJDBC.getJDBCConnection();
    public static PreparedStatement statement = null;
    public static ResultSet rs = null;
    public static boolean result;

    /**
     * Function are receive query statements and all params (?, ?, ?...) transmitted
     *
     * @param sql
     * @param args
     * @return statement
     * @throws SQLException
     */
    public static PreparedStatement getStatement(String sql, Object... args) throws SQLException {

        statement = connect.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }
        return statement;

    }

    /**
     * Function executeQuery are received query statements and all params (?, ?, ?...) transmitted
     *
     * @param sql
     * @param args
     * @return Table record
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql, Object... args) throws SQLException {

        statement = getStatement(sql, args);
        ResultSet rs = statement.executeQuery();
        return rs;

    }

    /**
     * Function executeUpdate are received query statements and all params (?, ?, ?...) transmitted
     *
     * @param sql
     * @param args
     * @return Number record affected
     * @throws SQLException
     */
    public int executeUpdate(String sql, Object... args) throws SQLException {

        statement = getStatement(sql, args);
        int rs = statement.executeUpdate();
        return rs;

    }

}
