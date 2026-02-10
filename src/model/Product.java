package model;

public class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int quantity;
    protected String category;
    protected String description;

    public Product(int productId, String name, double price, int quantity) {
        this(productId, name, price, quantity, "General", "");
    }

    public Product(int productId, String name, double price, int quantity, String category, String description) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
        setDescription(description);
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    public void setProductId(int productId) {
        if (productId <= 0) throw new IllegalArgumentException("Product ID must be positive");
        this.productId = productId;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }

    public void use() {
        System.out.println(name + " is used as a regular product.");
    }

    @Override
    public String toString() {
        return "[" + category + "] " + name + " (ID: " + productId + ") - " + price + " KZT, Qty: " + quantity;
    }
}
