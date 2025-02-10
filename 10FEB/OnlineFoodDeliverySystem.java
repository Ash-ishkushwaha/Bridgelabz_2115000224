abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract double calculateTotalPrice();

    public void getItemDetails() {
        System.out.println("Item Name: " + itemName);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }
}

interface Discountable {
    double applyDiscount(double total);
    String getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }

    @Override
    public String getDiscountDetails() {
        return "10% discount applied.";
    }
}

class NonVegItem extends FoodItem implements Discountable {
    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (getPrice() * getQuantity()) + (getPrice() * getQuantity() * 0.05);
    }

    @Override
    public double applyDiscount(double total) {
        return total * 0.95;
    }

    @Override
    public String getDiscountDetails() {
        return "5% discount applied.";
    }
}

public class OnlineFoodDeliverySystem {
    public static void main(String[] args) {
        FoodItem vegItem = new VegItem("Paneer Butter Masala", 200, 2);
        FoodItem nonVegItem = new NonVegItem("Chicken Biryani", 250, 3);

        FoodItem[] items = {vegItem, nonVegItem};

        for (FoodItem item : items) {
            item.getItemDetails();
            double totalPrice = item.calculateTotalPrice();
            System.out.println("Total Price: " + totalPrice);

            if (item instanceof Discountable) {
                Discountable discountable = (Discountable) item;
                double discountedPrice = discountable.applyDiscount(totalPrice);
                System.out.println(discountable.getDiscountDetails());
                System.out.println("Price after Discount: " + discountedPrice);
            }

            System.out.println("--------------------------");
        }
    }
}