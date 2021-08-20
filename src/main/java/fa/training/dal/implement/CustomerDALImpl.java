package fa.training.dal.implement;

import fa.training.util.ConnectionManager;
import fa.training.dal.CustomerDAL;
import fa.training.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements interface CustomerDAL
 * Extends connectionManager to reuse properties of connectionJDBC
 * Build functions for customers database
 */
public class CustomerDALImpl extends ConnectionManager implements CustomerDAL {

    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM Customer";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer(customer_id, customer_name) VALUES (?, ?)";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE customer_id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET customer_name = ? WHERE customer_id = ?";
    private static final String SELECT_CUSTOMER_ID = "SELECT customer_id FROM Customer WHERE customer_id = ?";


    @Override
    public List<Customer> getAllCustomer() throws SQLException {

        List<Customer> listOfCustomer = new ArrayList<>();
        rs = executeQuery(SELECT_ALL_CUSTOMER);
        while (rs.next()) {
            listOfCustomer.add(new Customer(rs.getInt(1),
                    rs.getString(2)));
        }
        return listOfCustomer;

    }

    @Override
    public int addCustomerDAL(Customer customer) throws SQLException {

        int id = customer.getCustomerId();
        String name = customer.getCustomerName();
        return executeUpdate(INSERT_CUSTOMER, id, name);

    }

    @Override
    public int deleteCustomerDAL(int customer_id) throws SQLException {

        return executeUpdate(DELETE_CUSTOMER, customer_id);
    }

    @Override
    public int updateCustomerDAL(Customer customer) throws SQLException {

        int id = customer.getCustomerId();
        String name = customer.getCustomerName();
        return executeUpdate(UPDATE_CUSTOMER, name, id);

    }

    @Override
    public boolean checkCustomerIdExits(int customer_id) throws SQLException {

        int check_id;
        boolean result = false;
        rs = executeQuery(SELECT_CUSTOMER_ID, customer_id);
        while (rs.next()) {
            check_id = rs.getInt(1);
            if (check_id == customer_id) {
                result = true;
            }
        }
        return result;

    }

}
