package fa.training.model;

public class Customer {

    private int customer_id;
    private String customerName;

    public Customer() {
    }

    public Customer(int customer_id, String customerName) {
        this.customer_id = customer_id;
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Used this function to support for testing all properties of Customer
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customer_id == customer.customer_id && customerName.equals(customer.customerName);
    }

    @Override
    public String toString() {
        return customer_id + "\t" + customerName;
    }

}
