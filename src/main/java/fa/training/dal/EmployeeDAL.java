package fa.training.dal;

import java.sql.SQLException;

/**
 * Check employee_id exists in database
 *
 */
public interface EmployeeDAL {

    /**
     * This function to check employee_id exits in database
     *
     * @param employee_id
     * @return boolean true if employee_id exits in database
     * @throws SQLException
     */
    boolean checkEmployeeIdExits(int employee_id) throws SQLException;

}
