package menu;

import model.*;
import database.ProductDAO;
import java.util.List;
import java.util.Scanner;

public class GroceryMenu implements Menu {

    private Scanner scanner;
    private ProductDAO productDAO;

    public GroceryMenu() {
        this.scanner = new Scanner(System.in);
        this.productDAO = new ProductDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  GROCERY STORE MANAGEMENT SYSTEM v2.0 ║");
        System.out.println("║  Fully Database-Driven 🗄️            ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║             MAIN MENU                  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ PRODUCT MANAGEMENT ───────────────────┐");
        System.out.println("│ 1. Add Product                         │");
        System.out.println("│ 2. View All Products                    │");
        System.out.println("│ 3. Update Product                       │");
        System.out.println("│ 4. Delete Product                       │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 5. Search by Name                       │");
        System.out.println("│ 6. Search by Price Range                │");
        System.out.println("│ 7. Products Above Minimum Price         │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│ 8. Polymorphism Demo                    │");
        System.out.println("│ 0. Exit                                 │");
        System.out.println("└────────────────────────────────────────┘");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n👉 Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: viewAllProducts(); break;
                    case 3: updateProduct(); break;
                    case 4: deleteProduct(); break;
                    case 5: searchByName(); break;
                    case 6: searchByPriceRange(); break;
                    case 7: searchByMinPrice(); break;
                    case 8: demonstratePolymorphism(); break;
                    case 0:
                        running = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system!      ║");
                        System.out.println("║  Goodbye! 👋                          ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        break;
                    default: System.out.println("❌ Invalid choice! Please select 0-8.");
                }

                if (choice != 0) pressEnterToContinue();

            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void addProduct() {
        try {
            System.out.println("\n┌─ ADD PRODUCT ──────────────────────────┐");
            System.out.print("│ Enter Product ID: ");
            int id = scanner.nextInt(); scanner.nextLine();

            System.out.print("│ Enter Product Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Price (KZT): ");
            double price = scanner.nextDouble(); scanner.nextLine();

            System.out.print("│ Enter Quantity: ");
            int quantity = scanner.nextInt(); scanner.nextLine();

            System.out.print("│ Enter Category (Fresh / Packaged / General): ");
            String category = scanner.nextLine();

            System.out.print("│ Enter Description: ");
            String description = scanner.nextLine();

            Product product;
            if ("Fresh".equalsIgnoreCase(category)) {
                product = new FreshProduct(id, name, price, quantity, category, description);
            } else if ("Packaged".equalsIgnoreCase(category)) {
                product = new PackagedProduct(id, name, price, quantity, category, description);
            } else {
                product = new Product(id, name, price, quantity, category, description);
            }

            if (productDAO.insertProduct(product)) {
                System.out.println("✅ Product added successfully!");
            } else {
                System.out.println("❌ Failed to add product.");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        displayProductList(products, "All Products");
    }

    private void updateProduct() {
        System.out.println("\n┌─ UPDATE PRODUCT ──────────────────────┐");
        System.out.print("│ Enter Product ID to update: ");
        try {
            int id = scanner.nextInt(); scanner.nextLine();
            Product existing = productDAO.getProductById(id);
            if (existing == null) {
                System.out.println("❌ No product found with ID: " + id);
                return;
            }

            System.out.println("│ Current: " + existing.toString());
            System.out.print("│ New Name [" + existing.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) newName = existing.getName();

            System.out.print("│ New Price [" + existing.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

            System.out.print("│ New Quantity [" + existing.getQuantity() + "]: ");
            String qtyInput = scanner.nextLine();
            int newQuantity = qtyInput.isEmpty() ? existing.getQuantity() : Integer.parseInt(qtyInput);

            System.out.print("│ New Category [" + existing.getCategory() + "]: ");
            String newCategory = scanner.nextLine();
            if (newCategory.isEmpty()) newCategory = existing.getCategory();

            System.out.print("│ New Description [" + existing.getDescription() + "]: ");
            String newDesc = scanner.nextLine();
            if (newDesc.isEmpty()) newDesc = existing.getDescription();

            Product updated;
            if ("Fresh".equalsIgnoreCase(newCategory)) {
                updated = new FreshProduct(id, newName, newPrice, newQuantity, newCategory, newDesc);
            } else if ("Packaged".equalsIgnoreCase(newCategory)) {
                updated = new PackagedProduct(id, newName, newPrice, newQuantity, newCategory, newDesc);
            } else {
                updated = new Product(id, newName, newPrice, newQuantity, newCategory, newDesc);
            }

            if (productDAO.updateProduct(updated)) {
                System.out.println("✅ Product updated successfully!");
            } else {
                System.out.println("❌ Update failed.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deleteProduct() {
        System.out.println("\n┌─ DELETE PRODUCT ──────────────────────┐");
        System.out.print("│ Enter Product ID to delete: ");
        try {
            int id = scanner.nextInt(); scanner.nextLine();
            Product product = productDAO.getProductById(id);
            if (product == null) {
                System.out.println("❌ No product found with ID: " + id);
                return;
            }

            System.out.println("│ Product: " + product.toString());
            System.out.print("⚠️ Confirm deletion (yes/no): ");
            String confirm = scanner.nextLine();
            if ("yes".equalsIgnoreCase(confirm)) {
                if (productDAO.deleteProduct(id)) System.out.println("✅ Product deleted.");
                else System.out.println("❌ Deletion failed.");
            } else System.out.println("❌ Deletion cancelled.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Product> results = productDAO.searchByName(name);
        displayProductList(results, "Search Results for '" + name + "'");
    }

    private void searchByPriceRange() {
        try {
            System.out.print("Enter min price: ");
            double min = scanner.nextDouble();
            System.out.print("Enter max price: ");
            double max = scanner.nextDouble();
            scanner.nextLine();
            List<Product> results = productDAO.searchByPriceRange(min, max);
            displayProductList(results, "Price Range: " + min + " - " + max);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void searchByMinPrice() {
        try {
            System.out.print("Enter minimum price: ");
            double min = scanner.nextDouble(); scanner.nextLine();
            List<Product> results = productDAO.searchByMinPrice(min);
            displayProductList(results, "Price >= " + min);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\nPolymorphism Demo:");
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            p.use();
        }
    }

    private void displayProductList(List<Product> list, String title) {
        System.out.println("\n╔════════════════ " + title + " ═════════════════╗");
        if (list.isEmpty()) System.out.println("No products found.");
        else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
        }
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    private void pressEnterToContinue() {
        System.out.println("[Press Enter to continue...]");
        scanner.nextLine();}
}
