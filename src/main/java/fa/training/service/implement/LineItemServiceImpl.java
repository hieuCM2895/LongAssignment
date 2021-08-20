package fa.training.service.implement;

import fa.training.dal.implement.*;
import fa.training.model.LineItem;
import fa.training.service.LineItemService;
import fa.training.util.ConnectionManager;
import fa.training.controller.ReadFileDataForTesting;
import fa.training.util.Notification;

import java.util.ArrayList;
import java.util.List;

public class LineItemServiceImpl implements LineItemService {

    private final OrderDALImpl orderDAL = new OrderDALImpl();
    private final LineItemDALImpl lineDAL = new LineItemDALImpl();
    private final ProductDALImpl productDAL = new ProductDALImpl();
    private final ReadFileDataForTesting test = new ReadFileDataForTesting();

    public List<Integer> addLineItemService() throws Exception {

        List<Integer> listOfNumberRecord = new ArrayList<>();
        int numberRecordInsert = 0;
        int numberRecordUpdate = 0;
        try {
            ConnectionManager.connect.setAutoCommit(false);
            List<LineItem> listOfAddLineItem = test.getDataFromLineItemTxt("src/main/java/fa/training/util/file/lineitem/AddLineItems.txt");
            for (LineItem lineItem : listOfAddLineItem) {
                int order_id = lineItem.getOrderId();
                int product_id = lineItem.getProductId();
                int quantity = lineItem.getQuantity();
                try {
                    if (!orderDAL.checkOrderIdExits(order_id)) {
                        throw new Exception(Notification.NOT_EXIST_ORDER_ID + order_id);
                    }
                    if (!productDAL.checkProductIdExits(product_id)) {
                        throw new Exception(Notification.NOT_EXIST_PRODUCT_ID + product_id);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                if (lineDAL.checkOrderIdAndProductIdInLineItem(order_id, product_id)) {
                    int quantityUpdate = lineDAL.getQuantityByOrderIdAndProductId(order_id, product_id) + quantity;
                    double price = quantityUpdate * productDAL.getListPriceByProductId(product_id);
                    numberRecordUpdate += lineDAL.updateLineItemWhenDuplicated(order_id, product_id, quantityUpdate, price);
                    List<Double> listPrice = lineDAL.getListPriceByOrderId(order_id);
                    double totalPriceByOrderId = 0;
                    for (Double listPriceOrderId : listPrice) {
                        totalPriceByOrderId += listPriceOrderId;
                    }
                    orderDAL.updateOrderTotal(order_id, totalPriceByOrderId);
                    continue;
                }
                double price = quantity * productDAL.getListPriceByProductId(product_id);
                numberRecordInsert += lineDAL.addLineItemDAL(new LineItem(order_id, product_id, quantity, price));
                List<Double> listPrice = lineDAL.getListPriceByOrderId(order_id);
                double totalPriceByOrderId = 0;
                for (Double listPriceOrderId : listPrice) {
                    totalPriceByOrderId += listPriceOrderId;
                }
                orderDAL.updateOrderTotal(order_id, totalPriceByOrderId);
            }
            listOfNumberRecord.add(0, numberRecordInsert);
            listOfNumberRecord.add(1, numberRecordUpdate);
            if (ConnectionManager.AUTO_COMMIT) {
                ConnectionManager.connect.commit();
            }
        } catch (Exception ex) {
            ConnectionManager.connect.rollback();
            throw new Exception(Notification.FAILURE_ADD_LINEITEM);
        }
        return listOfNumberRecord;

    }

    public List<LineItem> getAllLineItemByOrderId(int order_id) throws Exception {

        try {
            if (!orderDAL.checkOrderIdExits(order_id)) {
                throw new Exception(Notification.NOT_EXIST_ORDER_ID + order_id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lineDAL.getAllItemsByOrderId(order_id);

    }
}
