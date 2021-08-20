package fa.training.dal.implement;

import fa.training.dal.ProductDAL;
import fa.training.util.ConnectionManager;

import java.sql.SQLException;

public class ProductDALImpl extends ConnectionManager implements ProductDAL {

    private static final String CHECK_PRODUCT_ID = "SELECT product_id FROM Product WHERE product_id = ?";
    private static final String SELECT_LIST_PRICE_BY_PRODUCT_ID = "SELECT list_price FROM Product WHERE product_id = ?";

    @Override
    public boolean checkProductIdExits(int product_id) throws SQLException {

        int check_id;
        boolean result = false;
        rs = executeQuery(CHECK_PRODUCT_ID, product_id);
        while (rs.next()) {
            check_id = rs.getInt(1);
            if (check_id == product_id) {
                result = true;
            }
        }
        return result;

    }

    @Override
    public int getListPriceByProductId(int product_id) throws SQLException {

        int list_price = 0;
        rs = executeQuery(SELECT_LIST_PRICE_BY_PRODUCT_ID, product_id);
        while (rs.next()) {
            list_price = rs.getInt(1);
        }
        return list_price;

    }

}
