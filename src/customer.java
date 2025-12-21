public class customer {

    // 1. PRIVATE FIELDS (minimum 4)
    private int customerId;
    private String name;
    private String membershipLevel; // Regular / VIP
    private double totalPurchases;

    // 2. CONSTRUCTOR WITH PARAMETERS
    public customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        this.customerId = customerId;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.totalPurchases = totalPurchases;
    }

    // 3. DEFAULT CONSTRUCTOR (optional but recommended)
    public customer() {
        this.customerId = 0;
        this.name = "Unknown Customer";
        this.membershipLevel = "Regular";
        this.totalPurchases = 0.0;
    }

    // 4. GETTERS
    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public double getTotalPurchases() {
        return totalPurchases;
    }

    // 5. SETTERS
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setTotalPurchases(double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    // 6. ADDITIONAL METHODS WITH LOGIC (minimum 2)

    // Method 1: Add purchase amount
    public void addPurchase(double amount) {
        if (amount > 0) {
            totalPurchases += amount;
        }
    }

    // Method 2: Check if customer is VIP
    public boolean isVIP() {
        return totalPurchases >= 100000;
    }

    // 7. toString() METHOD
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                '}';
    }
}
