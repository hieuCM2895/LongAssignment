package fa.training.service;

import fa.training.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    /**
     * Function to add customer from file.txt into database
     *
     * @return
     * @throws Exception
     */
    int addCustomerService() throws Exception;

    /**
     * Function to delete customer from file.txt into database
     *
     * @return number record affected when the database is deleted
     * @throws Exception
     */
    int deleteCustomerService() throws Exception;

    /**
     * Function to update customer from file.txt into database
     *
     * @return number record affected when the database is updated
     * @throws Exception
     */
    int updateCustomerService() throws Exception;

    /**
     * Function to get all customer in database to show in application
     *
     * @return list of all customer
     * @throws SQLException
     */
    List<Customer> getAllCustomer() throws SQLException;

}
