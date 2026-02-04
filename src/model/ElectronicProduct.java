package model;

public class ElectronicProduct extends Product {

    public ElectronicProduct(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getDescription() {
        return "Electronic product: " + name + ", price $" + price;
    }
}
