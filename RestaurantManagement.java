class Person {
    String name;
    int id;

    Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}

interface Worker {
    void performDuties();
}

class Chef extends Person implements Worker {
    Chef(String name, int id) {
        super(name, id);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is preparing food.");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        performDuties();
    }
}

class Waiter extends Person implements Worker {
    Waiter(String name, int id) {
        super(name, id);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is serving food.");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        performDuties();
    }
}

public class RestaurantManagement {
    public static void main(String[] args) {
        Chef chef = new Chef("Chef1", 101);
        Waiter waiter = new Waiter("Waiter1", 102);

        chef.displayInfo();
        System.out.println();
        waiter.displayInfo();
    }
}