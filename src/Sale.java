public class Sale {

    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;

    public Sale(int saleId, String customerName, double totalAmount, String date) {
        setSaleId(saleId);
        setCustomerName(customerName);
        setTotalAmount(totalAmount);
        setDate(date);
    }

    public Sale() {
        this.saleId = 0;
        this.customerName = "Unknown Customer";
        this.totalAmount = 0.0;
        this.date = "Not set";
    }

    public int getSaleId() { return saleId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public String getDate() { return date; }

    public void setSaleId(int saleId) { if (saleId > 0) this.saleId = saleId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setTotalAmount(double totalAmount) { if (totalAmount >= 0) this.totalAmount = totalAmount; }
    public void setDate(String date) { this.date = date; }

    public void addItem(double price) { if (price > 0) totalAmount += price; }
    public double calculateTotal() { return totalAmount; }

    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", date='" + date + '\'' +
                '}';
    }
}



