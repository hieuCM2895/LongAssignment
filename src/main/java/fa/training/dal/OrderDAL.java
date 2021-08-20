package fa.training.dal;

import fa.training.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Insert, update and check for Order
 */
public interface OrderDAL {

    /**
     * This method is used to get all data of list order by customerId in database.
     *
     * @param customer_id
     * @return List of all orders by customerId
     * @throws SQLException
     */
    List<Order> getAllOrdersByCustomerId(int customer_id) throws SQLException;

    /**
     * This method is used to add a new order into database
     *
     * @param order
     * @return number row affected when insert
     * @throws SQLException
     */
    int addOrderDAL(Order order) throws SQLException;

    /**
     * This method is used to update order total into database
     *
     * @param order_id
     * @param total
     * @return boolean true if updateOrderTotal is successful
     * @throws SQLException
     */
    boolean updateOrderTotal(int order_id, double total) throws SQLException;

    /**
     * Check order_id exits in database.
     *
     * @param order_id
     * @return true if exits
     * @throws SQLException
     */
    boolean checkOrderIdExits(int order_id) throws SQLException;

}
