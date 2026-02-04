package model;

public abstract class Product {

    protected int id;
    protected String name;
    protected double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return id + ". " + name + " - $" + price;
    }
}
