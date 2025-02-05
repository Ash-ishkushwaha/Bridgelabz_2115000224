class Product {
    private static double discount = 0.0;
    
    private final String productID;
    private String productName;
    private double price;
    private int quantity;
    
    public Product(String productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public static void updateDiscount(double newDiscount) {
        discount = newDiscount;
    }
    
    public void displayProductDetails() {
        if (this instanceof Product) {
            System.out.println("Product ID: " + this.productID);
            System.out.println("Product Name: " + this.productName);
            System.out.println("Price: " + this.price);
            System.out.println("Quantity: " + this.quantity);
            System.out.println("Discount: " + discount + "%");
        } else {
            System.out.println("Invalid product object.");
        }
    }
    
    public static void main(String[] args) {
        Product prod1 = new Product("P001", "Laptop", 1200.0, 5);
        Product prod2 = new Product("P002", "Smartphone", 800.0, 10);
        
        prod1.displayProductDetails();
        prod2.displayProductDetails();
        
        Product.updateDiscount(10.0);
        
        prod1.displayProductDetails();
        prod2.displayProductDetails();
    }
}