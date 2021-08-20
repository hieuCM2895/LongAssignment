package fa.training.dal.implement;

import fa.training.util.ConnectionManager;
import fa.training.dal.OrderDAL;
import fa.training.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements interface OrderDAL
 * Extends connectionManager to reuse properties of connectionJDBC
 * Build functions for orders database
 */
public class OrderDALImpl extends ConnectionManager implements OrderDAL {

    private static final String SELECT_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM Orders Where customer_id = ?";
    private static final String INSERT_ORDERS = "INSERT INTO Orders (order_id, order_date, customer_id, employee_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_TOTAL_BY_ORDER_ID = "UPDATE Orders SET total = ? WHERE order_id = ?";
    private static final String CHECK_ORDER_ID = "SELECT order_id FROM Orders WHERE order_id = ?";

    @Override
    public List<Order> getAllOrdersByCustomerId(int customer_id) throws SQLException {

        List<Order> orderByCustomerId = new ArrayList<>();
        rs = executeQuery(SELECT_ORDERS_BY_CUSTOMER_ID, customer_id);
        while (rs.next()) {
            orderByCustomerId.add(new Order(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getDouble(5)));
        }
        return orderByCustomerId;

    }

    @Override
    public int addOrderDAL(Order order) throws SQLException {

        int order_id = order.getOrderId();
        String order_date = order.getOrderDateString();
        int customer_id = order.getCustomerId();
        int employee_id = order.getEmployeeId();
        return executeUpdate(INSERT_ORDERS, order_id, order_date, customer_id, employee_id);

    }

    @Override
    public boolean updateOrderTotal(int order_id, double total) throws SQLException {

        int numberRowInsert = executeUpdate(UPDATE_TOTAL_BY_ORDER_ID, total, order_id);
        result = numberRowInsert >= 1;
        return result;

    }

    @Override
    public boolean checkOrderIdExits(int order_id) throws SQLException {

        int check_id;
        boolean result = false;
        rs = executeQuery(CHECK_ORDER_ID, order_id);
        while (rs.next()) {
            check_id = rs.getInt(1);
            if (check_id == order_id) {
                result = true;
            }
        }
        return result;

    }

}
