package fa.training.dal.implement;

import fa.training.util.ConnectionManager;
import fa.training.dal.LineItemDAL;
import fa.training.model.LineItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements interface LineItemDAL
 * Extends connectionManager to reuse properties of connectionJDBC
 * Build functions for LineItems database
 */
public class LineItemDALImpl extends ConnectionManager implements LineItemDAL {

    private static final String SELECT_ALL_LINEITEM_BY_ORDER_ID = "SELECT * FROM LineItem Where order_id = ?";
    private static final String INSERT_LINEITEM = "INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PRICE_BY_ORDER_ID = "SELECT price FROM LineItem WHERE order_id = ?";
    private static final String SELECT_QUANTITY = "SELECT quantity FROM LineItem WHERE order_id = ? AND product_id = ?";
    private static final String UPDATE_QUANTITY_PRICE = "UPDATE LineItem SET quantity = ?, price = ? WHERE product_id = ? AND order_id = ?";
    private static final String SELECT_ORDER_ID_PRODUCT_ID = "SELECT order_id, product_id FROM LineItem WHERE order_id = ? AND product_id = ?";

    @Override
    public List<LineItem> getAllItemsByOrderId(int order_id) throws SQLException {

        List<LineItem> orderByCustomerId = new ArrayList<>();
        rs = executeQuery(SELECT_ALL_LINEITEM_BY_ORDER_ID, order_id);
        while (rs.next()) {
            orderByCustomerId.add(new LineItem(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getDouble(4)));
        }
        return orderByCustomerId;

    }

    @Override
    public int addLineItemDAL(LineItem item) throws SQLException {

        int order_id = item.getOrderId();
        int product_id = item.getProductId();
        int quantity = item.getQuantity();
        double price = item.getPrice();
        return executeUpdate(INSERT_LINEITEM, order_id, product_id, quantity, price);

    }

    @Override
    public List<Double> getListPriceByOrderId(int order_id) throws SQLException {

        List<Double> listPriceByOrderId = new ArrayList<>();
        rs = executeQuery(SELECT_PRICE_BY_ORDER_ID, order_id);
        while (rs.next()) {
            listPriceByOrderId.add(rs.getDouble(1));
        }
        return listPriceByOrderId;

    }

    @Override
    public int getQuantityByOrderIdAndProductId(int order_id, int product_id) throws SQLException {

        int quantity = 0;
        rs = executeQuery(SELECT_QUANTITY, order_id, product_id);
        while (rs.next()) {
            quantity = rs.getInt(1);
        }
        return quantity;

    }

    @Override
    public int updateLineItemWhenDuplicated(int order_id, int product_id, int quantity, double price) throws SQLException {

        return executeUpdate(UPDATE_QUANTITY_PRICE, quantity, price, product_id, order_id);

    }

    @Override
    public boolean checkOrderIdAndProductIdInLineItem(int order_id, int product_id) throws SQLException {

        boolean result = false;
        int check_id;
        rs = executeQuery(SELECT_ORDER_ID_PRODUCT_ID, order_id, product_id);
        while (rs.next()) {
            check_id = rs.getInt(1);
            if (check_id != 0) {
                result = true;
            }
        }
        return result;

    }

}
