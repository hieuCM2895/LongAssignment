package fa.training.service;

import fa.training.model.LineItem;

import java.sql.SQLException;
import java.util.List;

public interface LineItemService {

    /**
     * Function to add LineItem from file.txt into database
     *
     * @return list of number record affected when the database is inserted or updated
     * @throws Exception
     */
    List<Integer> addLineItemService() throws Exception;

    /**
     * Function to get all LineItem by order_id in database to show in application
     *
     * @param order_id
     * @return list of LineItem by order_id
     * @throws SQLException
     */
    List<LineItem> getAllLineItemByOrderId(int order_id) throws SQLException, Exception;

}
