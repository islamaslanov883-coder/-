package model;

public class FoodProduct extends Product {
    private String expirationDate;

    public FoodProduct(int id, String name, double price, String expirationDate) {
        super(id, name, price);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() { return expirationDate; }

    @Override
    public String getDescription() {
        return "[Food] " + name + " | Best before: " + expirationDate;
    }
}