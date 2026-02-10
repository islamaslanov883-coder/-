package model;

public class Customer {
    private String fullName;
    private double totalSpent;
    private int loyaltyPoints;
    private String email;

    public Customer(String fullName, double totalSpent, int loyaltyPoints, String email) {
        this.fullName = fullName;
        setTotalSpent(totalSpent);
        setLoyaltyPoints(loyaltyPoints);
        setEmail(email);
    }

    public Customer() {
        this.fullName = "Guest";
        this.totalSpent = 0.0;
        this.loyaltyPoints = 0;
        this.email = "no@email.com";
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = (totalSpent >= 0) ? totalSpent : 0;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = (loyaltyPoints >= 0) ? loyaltyPoints : 0;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("⚠️ Warning: Invalid email! Adding default.");
            this.email = "default@store.com";
        }
    }

    public String getFullName() { return fullName; }
    public double getTotalSpent() { return totalSpent; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public String getEmail() { return email; }

    public boolean isVIP() { return loyaltyPoints > 100; }

    @Override
    public String toString() {
        return "model.Customer{name='" + fullName + "', spent=" + totalSpent + ", points=" + loyaltyPoints + ", email='" + email + "'}";
    }
}
