package menu;

import dao.ProductDAO;
import model.Product;

import java.util.Scanner;

public class StoreMenu implements Menu {

    private Scanner scanner = new Scanner(System.in);
    private ProductDAO dao = new ProductDAO();

    @Override
    public void displayMenu() {
        System.out.println("""
            ===== STORE SYSTEM =====
            1. Add Electronic Product
            2. Add Food Product
            3. View All Products
            4. Update Product Price
            5. Delete Product
            0. Exit
            """);
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        int choice;
        do {
            displayMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addElectronic();
                case 2 -> addFood();
                case 3 -> viewAll();
                case 4 -> updatePrice();
                case 5 -> deleteProduct();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void addElectronic() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        dao.addElectronic(name, price);
    }

    private void addFood() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Expiration date: ");
        String date = scanner.nextLine();

        dao.addFood(name, price, date);
    }

    private void viewAll() {
        for (Product p : dao.getAll()) {
            System.out.println(p);
        }
    }

    private void updatePrice() {
        System.out.print("Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine());

        dao.updatePrice(id, price);
    }

    private void deleteProduct() {
        System.out.print("Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        dao.deleteById(id);
    }
}
