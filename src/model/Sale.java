package model;

public class Sale {
    private int saleId;
    private String productName;
    private double amount;
    private String status;

    public Sale(int saleId, String productName, double amount, String status) {
        this.saleId = saleId;
        this.productName = productName;
        setAmount(amount);
        this.status = status;
    }

    public Sale() {
        this.saleId = 0;
        this.productName = "None";
        this.amount = 0.0;
        this.status = "Pending";
    }

    public void setAmount(double amount) {
        this.amount = (amount >= 0) ? amount : 0;
    }

    public void setSaleId(int saleId) { this.saleId = saleId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setStatus(String status) { this.status = status; }

    public int getSaleId() { return saleId; }
    public String getProductName() { return productName; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void completeSale() { this.status = "Completed"; }

    @Override
    public String toString() {
        return "model.Sale{id=" + saleId + ", product='" + productName + "', amount=" + amount + ", status='" + status + "'}";
    }
}
