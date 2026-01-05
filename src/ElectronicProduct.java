public class ElectronicProduct extends Product {

    private int warrantyMonths;

    public ElectronicProduct(int productId, String name, double price, int stockQuantity, int warrantyMonths) {
        super(productId, name, price, stockQuantity);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

    public String getDescription() {
        return "Electronic product: " + name + ", warranty: " + warrantyMonths + " months";
    }
}
