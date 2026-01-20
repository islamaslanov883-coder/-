package model;

public class ElectronicProduct extends Product implements Sellable {
    public ElectronicProduct(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getDescription() {
        return "Electronic: " + name + " ($" + price + ")";
    }

    @Override
    public void processSale() {
        System.out.println("Processing electronic item sale...");
    }
}
