package fa.training.dal;

import fa.training.model.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Insert, delete, update for customer in database
 * Check customer_id in database
 */
public interface CustomerDAL {

    /**
     * This method is used to get all data of list customer in database
     *
     * @return List of all customer
     * @throws SQLException
     */
    List<Customer> getAllCustomer() throws SQLException;

    /**
     * This method is used to add a new customer into database
     *
     * @param customer
     * @return number row insert affected
     * @throws SQLException
     */
    int addCustomerDAL(Customer customer) throws SQLException;

    /**
     * This method is used to delete a customer into database
     *
     * @param customer_id
     * @return number row delete affected
     * @throws SQLException
     */
    int deleteCustomerDAL(int customer_id) throws SQLException;

    /**
     * This method is used to update a customer into database
     *
     * @param customer
     * @return number row update affected
     * @throws SQLException
     */
    int updateCustomerDAL(Customer customer) throws SQLException;

    /**
     * @param customer_id
     * @return boolean true if customer_id exits in database
     * @throws SQLException
     */
    boolean checkCustomerIdExits(int customer_id) throws SQLException;

}
