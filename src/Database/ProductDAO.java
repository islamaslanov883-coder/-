package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO products (name, price, quantity, category, description) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setString(4, product.getCategory());
            stmt.setString(5, product.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally { DatabaseConnection.closeConnection(conn); }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY product_id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(extractProductFromResultSet(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DatabaseConnection.closeConnection(conn); }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extractProductFromResultSet(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DatabaseConnection.closeConnection(conn); }
        return null;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, price=?, quantity=?, category=?, description=? WHERE product_id=?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setString(4, product.getCategory());
            stmt.setString(5, product.getDescription());
            stmt.setInt(6, product.getProductId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
        finally { DatabaseConnection.closeConnection(conn); }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE product_id=?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
        finally { DatabaseConnection.closeConnection(conn); }
    }

    public List<Product> searchByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name ILIKE ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DatabaseConnection.closeConnection(conn); }
        return list;
    }

    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DatabaseConnection.closeConnection(conn); }
        return list;
    }

    public List<Product> searchByMinPrice(double min) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price >= ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DatabaseConnection.closeConnection(conn); }
        return list;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        String category = rs.getString("category");
        String description = rs.getString("description");

        if ("Fresh".equalsIgnoreCase(category)) return new FreshProduct(id, name, price, quantity, category, description);
        if ("Packaged".equalsIgnoreCase(category)) return new PackagedProduct(id, name, price, quantity, category, description);
        return new Product(id, name, price, quantity, category, description);
    }

    public void demonstratePolymorphism() {
        List<Product> products = getAllProducts();
        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM DEMO");
        System.out.println("========================================");
        if (products.isEmpty()) {
            System.out.println("No products to demonstrate.");
        } else {
            for (Product p : products) {
                p.use();
            }
        }
        System.out.println("========================================\n");
    }
}
