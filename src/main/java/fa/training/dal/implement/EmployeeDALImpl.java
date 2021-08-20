package fa.training.dal.implement;

import fa.training.dal.EmployeeDAL;
import fa.training.util.ConnectionManager;

import java.sql.SQLException;

/**
 * Implements interface EmployeeDAL
 * Extends connectionManager to reuse properties of connectionJDBC
 * Build functions for employee database
 */
public class EmployeeDALImpl extends ConnectionManager implements EmployeeDAL {

    private static final String SELECT_ID_EMPLOYEE = "SELECT employee_id FROM Employee WHERE employee_id = ?";

    @Override
    public boolean checkEmployeeIdExits(int employee_id) throws SQLException {

        int check_id;
        boolean result = false;
        rs = executeQuery(SELECT_ID_EMPLOYEE, employee_id);
        while (rs.next()) {
            check_id = rs.getInt(1);
            if (check_id == employee_id) {
                result = true;
            }
        }
        return result;

    }

}
