import java.util.ArrayList;
import java.util.List;

abstract class ProductCategory {
    private String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

class BookCategory extends ProductCategory {
    public BookCategory() {
        super("Books");
    }
}

class ClothingCategory extends ProductCategory {
    public ClothingCategory() {
        super("Clothing");
    }
}

class GadgetCategory extends ProductCategory {
    public GadgetCategory() {
        super("Gadgets");
    }
}

class Product<T extends ProductCategory> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " (" + category.getCategoryName() + ") - " + price;
    }
}

class Marketplace {
    private List<Product<? extends ProductCategory>> catalog = new ArrayList<>();

    public void addProduct(Product<? extends ProductCategory> product) {
        catalog.add(product);
    }

    public void showCatalog() {
        for (Product<? extends ProductCategory> product : catalog) {
            System.out.println(product);
        }
    }

    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double newPrice = product.getPrice() * (1 - percentage / 100);
        product.setPrice(newPrice);
    }
}

public class OnlineMarketplace {
    public static void main(String[] args) {
        Product<BookCategory> book = new Product<>("Java Programming", 39.99, new BookCategory());
        Product<ClothingCategory> shirt = new Product<>("T-Shirt", 19.99, new ClothingCategory());
        Product<GadgetCategory> phone = new Product<>("Smartphone", 499.99, new GadgetCategory());

        Marketplace marketplace = new Marketplace();
        marketplace.addProduct(book);
        marketplace.addProduct(shirt);
        marketplace.addProduct(phone);

        marketplace.showCatalog();

        Marketplace.applyDiscount(book, 10);
        Marketplace.applyDiscount(shirt, 15);
        Marketplace.applyDiscount(phone, 5);

        marketplace.showCatalog();
    }
}