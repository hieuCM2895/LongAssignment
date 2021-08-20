package fa.training.service;

import fa.training.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    /**
     * Function to add Order from file.txt into database
     *
     * @return how many record will be affected when the database is inserted
     * @throws Exception
     */
    int addOrderService() throws Exception;

    /**
     * Function to get all Order by customer_id in database to show in application
     *
     * @param customer_id
     * @return list of all order by customer_id
     * @throws SQLException
     */
    List<Order> getAllOrderByCustomerId(int customer_id) throws Exception;

}
