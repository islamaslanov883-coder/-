public class FoodProduct extends Product {

    private String expirationDate;

    public FoodProduct(int productId, String name, double price, int stockQuantity, String expirationDate) {
        super(productId, name, price, stockQuantity);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getDescription() {
        return "Food product: " + name + ", expires on " + expirationDate;
    }
}

