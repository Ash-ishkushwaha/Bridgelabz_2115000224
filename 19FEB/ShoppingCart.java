import java.util.*;

class ShoppingCart {
    private Map<String, Double> productMap = new HashMap<>();
    private LinkedHashMap<String, Double> orderedProductMap = new LinkedHashMap<>();
    private TreeMap<Double, String> sortedProductMap = new TreeMap<>();

    public void addProduct(String product, double price) {
        productMap.put(product, price);
        orderedProductMap.put(product, price);
        sortedProductMap.put(price, product);
    }

    public void displayProductsInOrder() {
        System.out.println("Products in Order of Addition:");
        for (Map.Entry<String, Double> entry : orderedProductMap.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    public void displayProductsSortedByPrice() {
        System.out.println("Products Sorted by Price:");
        for (Map.Entry<Double, String> entry : sortedProductMap.entrySet()) {
            System.out.println(entry.getValue() + entry.getKey());
        }
    }

    public void displayTotalPrice() {
        double total = 0;
        for (double price : productMap.values()) {
            total += price;
        }
        System.out.println(total);
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1200);
        cart.addProduct("Mouse", 250);
        cart.addProduct("Keyboard", 455);
        cart.addProduct("Monitor", 3000);

        cart.displayProductsInOrder();
        cart.displayProductsSortedByPrice();
        cart.displayTotalPrice();
    }
}
