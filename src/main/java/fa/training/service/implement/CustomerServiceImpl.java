package fa.training.service.implement;

import fa.training.dal.implement.CustomerDALImpl;
import fa.training.model.Customer;
import fa.training.service.CustomerService;
import fa.training.util.ConnectionJDBC;
import fa.training.util.ConnectionManager;
import fa.training.controller.ReadFileDataForTesting;
import fa.training.util.Notification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDALImpl customerDAL = new CustomerDALImpl();
    private final ReadFileDataForTesting test = new ReadFileDataForTesting();

    public int addCustomerService() throws Exception {

        int numberRecord = 0;
        try {
            ConnectionManager.connect.setAutoCommit(false);
            List<Customer> listForAddCustomer = test.getDataCustomerFromCustomerTxt("src/main/java/fa/training/util/file/customer/AddCustomer.txt");
            String name;

            for (Customer customers : listForAddCustomer) {
                int id = customers.getCustomerId();
                try {
                    if (customerDAL.checkCustomerIdExits(id)) {
                        throw new Exception(Notification.EXIST_CUSTOMER_ID + id);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                name = customers.getCustomerName();
                numberRecord += customerDAL.addCustomerDAL(new Customer(id, name));
            }

            if (ConnectionManager.AUTO_COMMIT) {
                ConnectionManager.connect.commit();
            }
        } catch (Exception ex) {
            ConnectionManager.connect.rollback();
            throw new Exception(Notification.FAILURE_ADD_CUSTOMER);
        }
        return numberRecord;

    }

    public int deleteCustomerService() throws Exception {

        int numberRecord = 0;
        try {
            ConnectionManager.connect.setAutoCommit(false);
            List<Customer> listOfCustomerIdForDelete = test.getDataCustomerFromCustomerTxt("src/main/java/fa/training/util/file/customer/DeleteCustomer.txt");

            for (Customer customer : listOfCustomerIdForDelete) {
                try {
                    if (!customerDAL.checkCustomerIdExits(customer.getCustomerId())) {
                        throw new Exception(Notification.NOT_EXIST_CUSTOMER_ID + customer.getCustomerId());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                numberRecord += customerDAL.deleteCustomerDAL(customer.getCustomerId());
            }

            if (ConnectionManager.AUTO_COMMIT) {
                ConnectionManager.connect.commit();
            }
        } catch (Exception ex) {
            ConnectionManager.connect.rollback();
            throw new Exception(Notification.FAILURE_DELETE_CUSTOMER);
        }
        return numberRecord;

    }

    public int updateCustomerService() throws Exception {

        int numberRecord = 0;
        try {
            ConnectionManager.connect.setAutoCommit(false);
            List<Customer> listOfCustomerForUpdate = test.getDataCustomerFromCustomerTxt("src/main/java/fa/training/util/file/customer/UpdateCustomer.txt");

            for (Customer customerUpdate : listOfCustomerForUpdate) {
                int id = customerUpdate.getCustomerId();
                try {
                    if (!customerDAL.checkCustomerIdExits(id)) {
                        throw new Exception(Notification.NOT_EXIST_CUSTOMER_ID + id);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                String name = customerUpdate.getCustomerName();
                numberRecord += customerDAL.updateCustomerDAL(new Customer(id, name));
            }

            if (ConnectionManager.AUTO_COMMIT) {
                ConnectionManager.connect.commit();
            }
        } catch (Exception ex) {
            ConnectionManager.connect.rollback();
            throw new Exception(Notification.FAILURE_UPDATE_CUSTOMER);
        }
        return numberRecord;

    }

    public List<Customer> getAllCustomer() throws SQLException {

        return customerDAL.getAllCustomer();
    }

}
