package fa.training.dal;

import java.sql.SQLException;

/**
 * Check and collect list price for product
 */
public interface ProductDAL {

    /**
     * Check product_id exits in database
     *
     * @param product_id
     * @return boolean true if product_id exits
     * @throws SQLException
     */
    boolean checkProductIdExits(int product_id) throws SQLException;

    /**
     * Get list price of product by product_id
     *
     * @param product_id
     * @return list_price for product by product_id in database
     * @throws SQLException
     */
    int getListPriceByProductId(int product_id) throws SQLException;

}
