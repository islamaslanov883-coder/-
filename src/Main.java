import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewProducts(); break;
                case 3: addCustomer(); break;
                case 4: viewCustomers(); break;
                case 5: addSale(); break;
                case 6: viewSales(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid choice!"); break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== STORE SYSTEM ===");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Add Customer");
        System.out.println("4. View All Customers");
        System.out.println("5. Add Sale");
        System.out.println("6. View All Sales");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter price: "); double price = scanner.nextDouble();
        System.out.print("Enter quantity: "); int qty = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter type (food/electronic): "); String type = scanner.nextLine();
        if (type.equalsIgnoreCase("food")) {
            System.out.print("Enter expiration date: "); String exp = scanner.nextLine();
            products.add(new FoodProduct(products.size()+1, name, price, qty, exp));
        } else {
            System.out.print("Enter warranty months: "); int w = scanner.nextInt(); scanner.nextLine();
            products.add(new ElectronicProduct(products.size()+1, name, price, qty, w));
        }
        System.out.println("Product added.");
    }

    private static void viewProducts() {
        for (Product p : products) System.out.println(p.getDescription());
    }

    private static void addCustomer() {
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter membership level: "); String m = scanner.nextLine();
        System.out.print("Enter total purchases: "); double t = scanner.nextDouble(); scanner.nextLine();
        customers.add(new Customer(customers.size()+1, name, m, t));
        System.out.println("Customer added.");
    }

    private static void viewCustomers() {
        for (Customer c : customers) System.out.println(c);
    }

    private static void addSale() {
        System.out.print("Enter customer name: "); String name = scanner.nextLine();
        System.out.print("Enter sale amount: "); double amt = scanner.nextDouble(); scanner.nextLine();
        sales.add(new Sale(sales.size()+1, name, amt, "2026-01-08"));
        System.out.println("Sale added.");
    }

    private static void viewSales() {
        for (Sale s : sales) System.out.println(s);
    }
}

