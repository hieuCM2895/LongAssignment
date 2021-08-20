package fa.training.dal;

import fa.training.model.LineItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Insert new LineItem into database
 * Some function support to calculate the price in every order_id and check many condition
 */
public interface LineItemDAL {

    /**
     * This method is used to get all data of list item by order_id in database
     *
     * @param order_id
     * @return List of LineItem by orderId
     * @throws SQLException
     */
    List<LineItem> getAllItemsByOrderId(int order_id) throws SQLException;

    /**
     * This method is used to add a new lineItem into database
     *
     * @param item
     * @return number record when insert affected
     * @throws SQLException
     */
    int addLineItemDAL(LineItem item) throws SQLException;

    /**
     * This method is used to compute total for orderId
     *
     * @param order_id
     * @return list price by order_id to calculate total
     * @throws SQLException
     */
    List<Double> getListPriceByOrderId(int order_id) throws SQLException;

    /**
     * In case add new LineItem when duplicates
     * Get quantity to update the LineItem by order_id and product_id
     *
     * @param order_id
     * @param product_id
     * @return quantity
     * @throws SQLException
     */
    int getQuantityByOrderIdAndProductId(int order_id, int product_id) throws SQLException;

    /**
     * In case add new LineItem if duplicates
     * This method is used to update quantity for lineItem
     *
     * @param order_id
     * @param product_id
     * @param quantity
     * @param price
     * @return number record update affected
     * @throws SQLException
     */
    int updateLineItemWhenDuplicated(int order_id, int product_id, int quantity, double price) throws SQLException;

    /**
     * Check for duplicates
     *
     * @param order_id
     * @param product_id
     * @return true if The two params already exists in the database
     * @throws SQLException
     */
    boolean checkOrderIdAndProductIdInLineItem(int order_id, int product_id) throws SQLException;

}
