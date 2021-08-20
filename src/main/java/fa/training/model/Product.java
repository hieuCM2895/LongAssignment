package fa.training.model;

public class Product {

    private int product_id;
    private String productName;
    private double listPrice;

    public Product() {
    }

    public Product(int product_id, String productName, double listPrice) {
        this.product_id = product_id;
        this.productName = productName;
        this.listPrice = listPrice;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

}
