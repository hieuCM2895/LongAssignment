package fa.training.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private int order_id;
    private Date orderDate;
    private int customer_id;
    private int employee_id;
    private double total;

    public Order() {
    }

    public Order(int order_id, String orderDate, int customer_id, int employee_id, double total) {
        this.order_id = order_id;
        this.orderDate = getOrderDate(orderDate);
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.total = total;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate(String orderDate) {
        Date formatOrderDate = null;
        try {
            formatOrderDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatOrderDate;
    }

    public String getOrderDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(orderDate);
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Used this function to support for testing all properties of Order
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_id == order.order_id && customer_id == order.customer_id && employee_id == order.employee_id && Double.compare(order.total, total) == 0 && orderDate.equals(order.orderDate);
    }

    @Override
    public String toString() {
        return order_id + "  |  " + getOrderDateString() + "  |    " + customer_id + "   |   " + employee_id + "    |  " + String.format("%,.3f", total);
    }

}
