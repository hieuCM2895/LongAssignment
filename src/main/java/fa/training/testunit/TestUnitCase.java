import fa.training.model.Customer;
import fa.training.model.LineItem;
import fa.training.model.Order;

import fa.training.service.implement.CustomerServiceImpl;
import fa.training.service.implement.LineItemServiceImpl;
import fa.training.service.implement.OrderServiceImpl;
import fa.training.controller.ReadFileDataForTesting;

import fa.training.util.ConnectionManager;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUnitCase {

    private final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();
    private final LineItemServiceImpl lineItemService = new LineItemServiceImpl();
    private final ReadFileDataForTesting test = new ReadFileDataForTesting();

    @BeforeAll
    public static void before() {
        ConnectionManager.AUTO_COMMIT = false;
    }

    @AfterAll
    public static void after() {
        ConnectionManager.AUTO_COMMIT = true;
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void TestAddCustomer() {

        try {
            int numberActual = customerService.addCustomerService();
            Assertions.assertEquals(5, numberActual);
            ConnectionManager.connect.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void TestDeleteCustomer() {

        try {
            int numberActual = customerService.deleteCustomerService();
            Assertions.assertEquals(5, numberActual);
            ConnectionManager.connect.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void TestUpdateCustomer() {

        try {
            int numberActual = customerService.updateCustomerService();
            Assertions.assertEquals(5, numberActual);
            ConnectionManager.connect.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void TestAddOrder() {

        try {
            int numberActual = orderService.addOrderService();
            Assertions.assertEquals(3, numberActual);
            ConnectionManager.connect.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void TestAddLineItem() {

        try {
            List<Integer> listNumberRecord = lineItemService.addLineItemService();
            int numberRecordInsert = listNumberRecord.get(0);
            int numberRecordUpdate = listNumberRecord.get(1);
            Assertions.assertEquals(1, numberRecordUpdate);
            Assertions.assertEquals(3, numberRecordInsert);
            ConnectionManager.connect.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(6)
    public void TestDisplayAllCustomer() {

        try {
            List<Customer> testListCustomer = customerService.getAllCustomer();
            List<Customer> listFromTxt = test.getDataCustomerFromCustomerTxt("src/main/java/fa/training/util/file/customer/TestListCustomer.txt");
            for (int i = 0; i < testListCustomer.size(); i++) {
                Assertions.assertEquals(testListCustomer.get(i), listFromTxt.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(7)
    public void TestAllOrderByCustomerId() {

        try {
            List<Customer> listCustomerId = test.getDataCustomerFromCustomerTxt("src/main/java/fa/training/util/file/order/InputCustomerIdToTestOrder.txt");
            List<Order> listTestOrderByCustomerId = new ArrayList<>();
            for (Customer customer : listCustomerId) {
                listTestOrderByCustomerId.addAll(orderService.getAllOrderByCustomerId(customer.getCustomerId()));
            }
            List<Order> listFromTxt = test.getDataFromOrderTxt("src/main/java/fa/training/util/file/order/TestListOrderByCustomerId.txt");
            for (int i = 0; i < listFromTxt.size(); i++) {
                Assertions.assertEquals(listFromTxt.get(i), listTestOrderByCustomerId.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    @org.junit.jupiter.api.Order(8)
    public void TestAllLineItemByOrderId() {

        try {
            List<Integer> listOfOrderId = test.getDataFromOrderIdTxt("src/main/java/fa/training/util/file/lineitem/InputOrderIdToTestLineItem.txt");
            List<LineItem> listOfAllLineItemByOrderId = new ArrayList<>();
            for (Integer order_id : listOfOrderId) {
                listOfAllLineItemByOrderId.addAll(lineItemService.getAllLineItemByOrderId(order_id));
            }
            List<LineItem> listFromTxt = test.getDataFromLineItemTxt("src/main/java/fa/training/util/file/lineitem/TestListLineItemByOrderId.txt");
            for (int i = 0; i < listFromTxt.size(); i++) {
                Assertions.assertEquals(listFromTxt.get(i), listOfAllLineItemByOrderId.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
