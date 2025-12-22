public class Main {
    public static void main(String[] args){
        customer a123 = new customer(01,"Alan","Vip",12 );
        System.out.println(a123);
        Sale sale1 = new Sale(1001, "Islam", 0.0, "2025-12-22");

        sale1.addItem(2500.0);
        sale1.addItem(1800.0);
        sale1.addItem(3200.0);

        System.out.println(sale1);
        System.out.println("Total amount: " + sale1.calculateTotal() + " KZT");
        Product product1 = new Product(101, "Milk 1L", 450.0, 30);
        System.out.println(product1);

    }
}

