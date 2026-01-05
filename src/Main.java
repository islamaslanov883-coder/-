import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer(1, "Alan", "VIP", 120000);
        System.out.println(c1);

        Sale sale1 = new Sale(1001, "Alan", 0.0, "2026-01-05");
        sale1.addItem(2500.0);
        sale1.addItem(1800.0);
        sale1.addItem(3200.0);
        System.out.println(sale1);
        System.out.println("Total amount: " + sale1.calculateTotal() + " KZT");
        FoodProduct food1 = new FoodProduct(101, "Milk 1L", 450.0, 30, "2026-01-10");
        ElectronicProduct elec1 = new ElectronicProduct(102, "Phone", 300000.0, 10, 24);
        ArrayList<Product> products = new ArrayList<>();
        products.add(food1);
        products.add(elec1);

        System.out.println("\n=== Products List ===");
        for (Product p : products) {
            System.out.println(p.getDescription());

            if (p instanceof FoodProduct) {
                FoodProduct fp = (FoodProduct) p;
                System.out.println("Expiration date: " + fp.getExpirationDate());
            }

            if (p instanceof ElectronicProduct) {
                ElectronicProduct ep = (ElectronicProduct) p;
                System.out.println("Warranty months: " + ep.getWarrantyMonths());
            }
            System.out.println("--------------------");
        }
    }
}

