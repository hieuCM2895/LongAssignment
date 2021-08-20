package fa.training.controller;

import fa.training.model.Customer;
import fa.training.model.LineItem;
import fa.training.model.Order;
import fa.training.service.implement.CustomerServiceImpl;
import fa.training.service.implement.OrderServiceImpl;
import fa.training.service.implement.LineItemServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Display layer with all function for application
 */
public class ApplicationLayer {

    private final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();
    private final LineItemServiceImpl lineItemService = new LineItemServiceImpl();

    public void showInformationDataBase() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("  -------------------MANAGEMENT SYSTEM-------------------");
        System.out.println("    1. Add an Customer");
        System.out.println("    2. Delete an Customer");
        System.out.println("    3. Update an Customer");
        System.out.println("    4. Add an Order");
        System.out.println("    5. Add an LineItem");
        System.out.println("    6. Show information of all Customer");
        System.out.println("    7. Show information of all order by customer_id");
        System.out.println("    8. Show information of all item by order_id");

        while (true) {
            System.out.print("Your choice: ");
            int set = sc.nextInt();
            switch (set) {
                case 1 -> {
                    int numberRecord = customerService.addCustomerService();
                    System.out.println("Number of new records affected: " + numberRecord);
                }
                case 2 -> {
                    int numberRecord = customerService.deleteCustomerService();
                    System.out.println("Number of delete records affected: " + numberRecord);
                }
                case 3 -> {
                    int numberRecord = customerService.updateCustomerService();
                    System.out.println("Number of update records affected: " + numberRecord);
                }
                case 4 -> {
                    int numberRecord = orderService.addOrderService();
                    System.out.println("Number of add records affected: " + numberRecord);
                }
                case 5 -> {
                    List<Integer> listNumberRecord = lineItemService.addLineItemService();
                    if (listNumberRecord.get(1) >= 1)
                        System.out.println("Number of update records affected: " + listNumberRecord.get(1));
                    System.out.println("Number of add records affected: " + listNumberRecord.get(0));
                }
                case 6 -> {
                    System.out.println("Id \t Name");
                    List<Customer> listAllCustomer = customerService.getAllCustomer();
                    listAllCustomer.forEach(System.out::println);
                }
                case 7 -> {
                    System.out.print("Input customer_id: ");
                    int customer_id = sc.nextInt();
                    List<Order> listOfOrderByCustomerId = orderService.getAllOrderByCustomerId(customer_id);
                    System.out.println("Id |    Date      | cus_id | emp_id | total");
                    listOfOrderByCustomerId.forEach(System.out::println);
                }
                case 8 -> {
                    System.out.print("Input order_id: ");
                    int order_id = sc.nextInt();
                    List<LineItem> listOfLineItemByOrderId = lineItemService.getAllLineItemByOrderId(order_id);
                    System.out.println("order_id   |   product_id   |   quantity   |   price");
                    listOfLineItemByOrderId.forEach(System.out::println);
                }
                default -> System.out.println("Please enter again: ");
            }
        }

    }
}
