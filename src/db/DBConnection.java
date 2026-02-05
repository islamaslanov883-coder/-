package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/OOP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Cannot connect to DB");
        }
    }
}











//    // DELETE
//    public void deleteById(int id) {
//        String sql = "DELETE FROM products WHERE id = ?";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}










//    // UPDATE
//    public void updatePrice(int id, double newPrice) {
//        String sql = "UPDATE products SET price = ? WHERE id = ?";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setDouble(1, newPrice);
//            ps.setInt(2, id);
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



//// READ
//    public ArrayList<Product> getAll() {
//        ArrayList<Product> list = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//
//        try (Connection c = DBConnection.getConnection();
//             Statement st = c.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//
//            while (rs.next()) {
//                if ("ELECTRONIC".equals(rs.getString("type"))) {
//                    list.add(new ElectronicProduct(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getDouble("price")
//                    ));
//                } else {
//                    list.add(new FoodProduct(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getDouble("price"),
//                            rs.getString("expiration_date")
//                    ));
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // UPDATE
//    public void updatePrice(int id, double newPrice) {
//        String sql = "UPDATE products SET price = ? WHERE id = ?";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setDouble(1, newPrice);
//            ps.setInt(2, id);
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




//// CREATE
//    public void addElectronic(String name, double price) {
//        String sql = "INSERT INTO products(name, price, type) VALUES (?, ?, ?)";
//
//        try (Connection c = DBConnection.getConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, name);
//            ps.setDouble(2, price);
//            ps.setString(3, "ELECTRONIC");
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addFood(String name, double price, String date) {
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
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // READ
//    public ArrayList<Product> getAll() {
//        ArrayList<Product> list = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//
//        try (Connection c = DBConnection.getConnection();
//             Statement st = c.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//
//            while (rs.next()) {
//                if ("ELECTRONIC".equals(rs.getString("type"))) {
//                    list.add(new ElectronicProduct(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getDouble("price")
//                    ));
//                } else {
//                    list.add(new FoodProduct(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getDouble("price"),
//                            rs.getString("expiration_date")
//                    ));
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }