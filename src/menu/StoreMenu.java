package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreMenu implements Menu {
    private ArrayList<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("""
            ===== STORE SYSTEM =====
            1. Add Electronic Product
            2. Add Food Product
            3. View All Products
            4. Demonstrate Polymorphism
            5. Show Specific Information
            0. Exit
            """);
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        int choice;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addElectronic();
                    case 2 -> addFood();
                    case 3 -> viewAll();
                    case 4 -> demonstratePolymorphism();
                    case 5 -> showSpecificInfo();
                    case 0 -> System.out.println("Exit");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
    }

    private void addElectronic() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        products.add(new ElectronicProduct(products.size() + 1, name, price));
    }

    private void addFood() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Expiration Date: ");
        String date = scanner.nextLine();
        products.add(new FoodProduct(products.size() + 1, name, price, date));
    }

    private void viewAll() {
        products.forEach(System.out::println);
    }

    private void demonstratePolymorphism() {
        for (Product p : products) {
            System.out.println(p.getDescription());
        }
    }

    private void showSpecificInfo() {
        for (Product p : products) {
            if (p instanceof ElectronicProduct) {
                System.out.println(p.getName() + " is an Electronic device.");
            }
            if (p instanceof FoodProduct f) {
                System.out.println(f.getName() + " is Food. Expiry: " + f.getExpirationDate());
            }
        }
    }
}