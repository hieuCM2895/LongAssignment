package fa.training.service.implement;

import fa.training.dal.implement.CustomerDALImpl;
import fa.training.dal.implement.EmployeeDALImpl;
import fa.training.dal.implement.OrderDALImpl;
import fa.training.model.Order;
import fa.training.service.OrderService;
import fa.training.util.ConnectionManager;
import fa.training.controller.ReadFileDataForTesting;
import fa.training.util.Notification;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDALImpl orderDAL = new OrderDALImpl();
    private final ReadFileDataForTesting test = new ReadFileDataForTesting();
    private final EmployeeDALImpl employeeDAL = new EmployeeDALImpl();
    private final CustomerDALImpl customerDAL = new CustomerDALImpl();

    public int addOrderService() throws Exception {

        int numberRecord = 0;
        try {
            ConnectionManager.connect.setAutoCommit(false);
            List<Order> listOfNewOrder = test.getDataFromOrderTxt("src/main/java/fa/training/util/file/order/AddOrder.txt");
            for (Order newOrder : listOfNewOrder) {
                int order_id = newOrder.getOrderId();
                String order_date = newOrder.getOrderDateString();
                int customer_id = newOrder.getCustomerId();
                int employee_id = newOrder.getEmployeeId();
                try {
                    if (orderDAL.checkOrderIdExits(order_id)) {
                        throw new Exception(Notification.EXIST_ORDER_ID + order_id);
                    }
                    if (!customerDAL.checkCustomerIdExits(customer_id)) {
                        throw new Exception(Notification.NOT_EXIST_CUSTOMER_ID + customer_id);
                    }
                    if (!employeeDAL.checkEmployeeIdExits(employee_id)) {
                        throw new Exception(Notification.NOT_EXIST_EMPLOYEE_ID + employee_id);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                numberRecord += orderDAL.addOrderDAL(new Order(order_id, order_date, customer_id, employee_id, 0));
            }
            if (ConnectionManager.AUTO_COMMIT) {
                ConnectionManager.connect.commit();
            }
        } catch (Exception ex) {
            ConnectionManager.connect.rollback();
            throw new Exception(Notification.FAILURE_ADD_ORDER);
        }
        return numberRecord;

    }

    public List<Order> getAllOrderByCustomerId(int customer_id) throws Exception {

        try {
            if (!customerDAL.checkCustomerIdExits(customer_id)) {
                throw new Exception(Notification.NOT_EXIST_CUSTOMER_ID + customer_id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderDAL.getAllOrdersByCustomerId(customer_id);

    }
}
