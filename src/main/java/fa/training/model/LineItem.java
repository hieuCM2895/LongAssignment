package fa.training.model;

public class LineItem {

    private int order_id;
    private int product_id;
    private int quantity;
    private double price;

    public LineItem() {
    }

    public LineItem(int order_id, int product_id, int quantity, double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quality) {
        this.quantity = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Used this function to support for testing all properties of LineItem
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return order_id == lineItem.order_id && product_id == lineItem.product_id && quantity == lineItem.quantity && Double.compare(lineItem.price, price) == 0;
    }

    @Override
    public String toString() {
        return "   " + order_id + "       |        " + product_id + "       |      " + quantity + "       |   " + String.format("%,.3f", price);
    }

}
