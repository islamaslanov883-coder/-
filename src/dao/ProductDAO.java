package dao;

import db.DBConnection;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ================= CREATE =================

    public boolean addElectronic(String name, double price) {
        String sql = "INSERT INTO products(name, price, type) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, "ELECTRONIC");

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFood(String name, double price, String date) {
        String sql =
                "INSERT INTO products(name, price, type, expiration_date) VALUES (?, ?, ?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, "FOOD");
            ps.setString(4, date);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= READ =================

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRowToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= UPDATE =================
    // Requirement: returns boolean, update by id

    public boolean updatePrice(int id, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================
    // Requirement: returns boolean, delete by id

    public boolean deleteById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= SEARCH =================

    // Search by name (LIKE, %, case-insensitive)
    public List<Product> searchByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql =
                "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToProduct(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Search by price range (BETWEEN, ordered DESC)
    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> list = new ArrayList<>();
        String sql =
                "SELECT * FROM products WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToProduct(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Search by minimum price (>=, ordered DESC)
    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> list = new ArrayList<>();
        String sql =
                "SELECT * FROM products WHERE price >= ? ORDER BY price DESC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, minPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToProduct(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= HELPER =================

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        if ("ELECTRONIC".equals(rs.getString("type"))) {
            return new ElectronicProduct(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
            );
        } else {
            return new FoodProduct(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("expiration_date")
            );
        }
    }
}






































//package dao;
//
//import db.DBConnection;
//import model.*;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductDAO {
//
//    // ================= CREATE =================
//
//    public boolean addElectronic(String name, double price) {
//        String sql = "INSERT INTO products(name, price, type) VALUES (?, ?, ?)";
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, name);
//            ps.setDouble(2, price);
//            ps.setString(3, "ELECTRONIC");
//
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean addFood(String name, double price, String date) {
//        String sql =
//                "INSERT INTO products(name, price, type, expiration_date) VALUES (?, ?, ?, ?)";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, name);
//            ps.setDouble(2, price);
//            ps.setString(3, "FOOD");
//            ps.setString(4, date);
//
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // ================= READ =================
//
//    public List<Product> getAll() {
//        List<Product> list = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                list.add(mapRowToProduct(rs));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // ================= UPDATE =================
//    // Requirement: returns boolean, update by id
//
//    public boolean updatePrice(int id, double newPrice) {
//        String sql = "UPDATE products SET price = ? WHERE id = ?";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setDouble(1, newPrice);
//            ps.setInt(2, id);
//
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // ================= DELETE =================
//    // Requirement: returns boolean, delete by id
//
//    public boolean deleteById(int id) {
//        String sql = "DELETE FROM products WHERE id = ?";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // ================= SEARCH =================
//
//    // Search by name (LIKE, %, case-insensitive)
//    public List<Product> searchByName(String name) {
//        List<Product> list = new ArrayList<>();
//        String sql =
//                "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?)";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, "%" + name + "%");
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    list.add(mapRowToProduct(rs));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // Search by price range (BETWEEN, ordered DESC)
//    public List<Product> searchByPriceRange(double min, double max) {
//        List<Product> list = new ArrayList<>();
//        String sql =
//                "SELECT * FROM products WHERE price BETWEEN ? AND ? ORDER BY price DESC";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setDouble(1, min);
//            ps.setDouble(2, max);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    list.add(mapRowToProduct(rs));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // Search by minimum price (>=, ordered DESC)
//    public List<Product> searchByMinPrice(double minPrice) {
//        List<Product> list = new ArrayList<>();
//        String sql =
//                "SELECT * FROM products WHERE price >= ? ORDER BY price DESC";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setDouble(1, minPrice);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    list.add(mapRowToProduct(rs));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // ================= HELPER =================
//
//    private Product mapRowToProduct(ResultSet rs) throws SQLException {
//        if ("ELECTRONIC".equals(rs.getString("type"))) {
//            return new ElectronicProduct(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getDouble("price")
//            );
//        } else {
//            return new FoodProduct(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getDouble("price"),
//                    rs.getString("expiration_date")
//            );
//        }
//    }
//}