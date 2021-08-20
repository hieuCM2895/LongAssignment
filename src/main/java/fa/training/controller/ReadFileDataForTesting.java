package fa.training.controller;

import com.mysql.cj.util.StringUtils;
import fa.training.model.Customer;
import fa.training.model.LineItem;
import fa.training.model.Order;
import fa.training.util.Notification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Create this class to build function can read and transmission data from file.txt
 * Test all function by file txt
 * Function return list for testing
 */
public class ReadFileDataForTesting {

    /**
     * Function to get data in file Customer.txt
     *
     * @param path link file to read
     * @return List of Customer for testing
     */
    public List<Customer> getDataCustomerFromCustomerTxt(String path) throws Exception {

        List<Customer> listOfCustomer = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!StringUtils.isNullOrEmpty(line)) {
                    String[] columns = line.split("\\,");
                    int id = Integer.parseInt(columns[0].trim());
                    String name;
                    if (columns.length == 2) {
                        name = columns[1].trim();
                    } else {
                        name = null;
                    }
                    listOfCustomer.add(new Customer(id, name));
                }
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(Notification.FAILURE_READ_CUSTOMER);
        }
        return listOfCustomer;

    }

    /**
     * Function to get data in file Order.txt
     *
     * @param path link file to read order
     * @return List of Order
     */
    public List<Order> getDataFromOrderTxt(String path) throws Exception {

        List<Order> listOfNewOrder = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!StringUtils.isNullOrEmpty(line)) {
                    String[] columns = line.split("\\,");
                    int order_id = Integer.parseInt(columns[0].trim());
                    String order_date = columns[1].trim();
                    int customer_id = Integer.parseInt(columns[2].trim());
                    int employee_id = Integer.parseInt(columns[3].trim());
                    double total;
                    if (columns.length == 5) {
                        total = Double.parseDouble(columns[4].trim());
                    } else {
                        total = 0;
                    }
                    listOfNewOrder.add(new Order(order_id, order_date, customer_id, employee_id, total));
                }
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(Notification.FAILURE_READ_ORDER);
        }
        return listOfNewOrder;

    }

    /**
     * Function to get data in file LineItem.txt
     *
     * @param path link file to read LineItem
     * @return List of LineItem
     */
    public List<LineItem> getDataFromLineItemTxt(String path) throws Exception {

        List<LineItem> listOfNewLineItem = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!StringUtils.isNullOrEmpty(line)) {
                    String[] columns = line.split("\\,");
                    int order_id = Integer.parseInt(columns[0].trim());
                    int product_id = Integer.parseInt(columns[1].trim());
                    int quantity = Integer.parseInt(columns[2].trim());
                    double price;
                    if (columns.length == 4) {
                        price = Double.parseDouble(columns[3].trim());
                    } else {
                        price = 0;
                    }
                    listOfNewLineItem.add(new LineItem(order_id, product_id, quantity, price));
                }
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(Notification.FAILURE_READ_LINEITEM);
        }
        return listOfNewLineItem;

    }

    /**
     * Function to get data in file InputOrderIdToTestLineItem.txt
     *
     * @param path link file to read LineItem
     * @return List of LineItem
     */
    public List<Integer> getDataFromOrderIdTxt(String path) throws Exception {

        List<Integer> listOfOrderId = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!StringUtils.isNullOrEmpty(line)) {
                    String[] columns = line.split("\\,");
                    int order_id = Integer.parseInt(columns[0].trim());
                    listOfOrderId.add(order_id);
                }
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(Notification.FAILURE_READ_ORDER);
        }
        return listOfOrderId;

    }

}

