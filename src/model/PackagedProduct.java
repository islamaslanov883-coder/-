package model;

public class PackagedProduct extends Product {
    public PackagedProduct(int productId, String name, double price, int quantity, String category, String description) {
        super(productId, name, price, quantity, category, description);
    }

    @Override
    public void use() {
        System.out.println(getName() + " is packaged and can be stored longer.");
    }
}