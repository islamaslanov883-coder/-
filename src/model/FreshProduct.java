package model;

public class FreshProduct extends Product {
    public FreshProduct(int productId, String name, double price, int quantity, String category, String description) {
        super(productId, name, price, quantity, category, description);
    }

    @Override
    public void use() {
        System.out.println(getName() + " is fresh and should be consumed quickly!");
    }
}
