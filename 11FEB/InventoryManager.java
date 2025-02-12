class Item {
    private String itemName;
    private int itemId;
    private int quantity;
    private double price;
    private Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public Item getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName + ", Item ID: " + itemId + ", Quantity: " + quantity + ", Price: " + price;
    }
}

class InventoryManagementSystem {
    private Item head;

    public InventoryManagementSystem() {
        this.head = null;
    }

    public void addItemAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.setNext(head);
        head = newItem;
    }

    public void addItemAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newItem);
        }
    }

    public void addItemAtPosition(int position, String itemName, int itemId, int quantity, double price) {
        if (position <= 0) {
            addItemAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        Item current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.getNext();
        }
        if (current != null) {
            newItem.setNext(current.getNext());
            current.setNext(newItem);
        }
    }

    public void removeItemById(int itemId) {
        if (head == null) return;
        if (head.getItemId() == itemId) {
            head = head.getNext();
            return;
        }
        Item current = head;
        while (current.getNext() != null && current.getNext().getItemId() != itemId) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public void updateItemQuantity(int itemId, int newQuantity) {
        Item item = searchItemById(itemId);
        if (item != null) {
            item.setQuantity(newQuantity);
        }
    }

    public Item searchItemById(int itemId) {
        Item current = head;
        while (current != null) {
            if (current.getItemId() == itemId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public Item searchItemByName(String itemName) {
        Item current = head;
        while (current != null) {
            if (current.getItemName().equalsIgnoreCase(itemName)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayAllItems() {
        Item current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        Item current = head;
        while (current != null) {
            totalValue += current.getPrice() * current.getQuantity();
            current = current.getNext();
        }
        return totalValue;
    }

    public void sortInventoryByName() {
        if (head == null || head.getNext() == null) return;
        head = mergeSortByName(head);
    }

    private Item mergeSortByName(Item head) {
        if (head == null || head.getNext() == null) return head;
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.getNext();
        middle.setNext(null);
        Item left = mergeSortByName(head);
        Item right = mergeSortByName(nextOfMiddle);
        return sortedMergeByName(left, right);
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head;
        Item fast = head.getNext();
        while (fast != null) {
            fast = fast.getNext();
            if (fast != null) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return slow;
    }

    private Item sortedMergeByName(Item left, Item right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.getItemName().compareTo(right.getItemName()) <= 0) {
            left.setNext(sortedMergeByName(left.getNext(), right));
            return left;
        } else {
            right.setNext(sortedMergeByName(left, right.getNext()));
            return right;
        }
    }

    public void sortInventoryByPrice() {
        if (head == null || head.getNext() == null) return;
        head = mergeSortByPrice(head);
    }

    private Item mergeSortByPrice(Item head) {
        if (head == null || head.getNext() == null) return head;
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.getNext();
        middle.setNext(null);
        Item left = mergeSortByPrice(head);
        Item right = mergeSortByPrice(nextOfMiddle);
        return sortedMergeByPrice(left, right);
    }

    private Item sortedMergeByPrice(Item left, Item right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.getPrice() <= right.getPrice()) {
            left.setNext(sortedMergeByPrice(left.getNext(), right));
            return left;
        } else {
            right.setNext(sortedMergeByPrice(left, right.getNext()));
            return right;
        }
    }
}

public class InventoryManager {
    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();
        inventory.addItemAtBeginning("Item1", 101, 10, 15.5);
        inventory.addItemAtEnd("Item2", 102, 5, 25.0);
        inventory.addItemAtPosition(1, "Item3", 103, 8, 12.0);

        System.out.println("Displaying all items:");
        inventory.displayAllItems();
        System.out.println();
        System.out.println("Updating quantity for Item ID 102:");
        inventory.updateItemQuantity(102, 10);
        inventory.displayAllItems();
        System.out.println();
        System.out.println("Searching for Item with ID 103:");
        System.out.println(inventory.searchItemById(103));
        System.out.println();
        System.out.println("Calculating total inventory value:");
        System.out.println("Total Value: " + inventory.calculateTotalValue());
        System.out.println();
        System.out.println("Sorting inventory by name:");
        inventory.sortInventoryByName();
        inventory.displayAllItems();
        System.out.println();
        System.out.println("Sorting inventory by price:");
        inventory.sortInventoryByPrice();
        inventory.displayAllItems();
        System.out.println();
        System.out.println("Removing Item with ID 101:");
        inventory.removeItemById(101);
        inventory.displayAllItems();
    }
}