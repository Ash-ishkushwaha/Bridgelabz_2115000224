class Vehicle {
    int maxSpeed;
    String model;

    Vehicle(int maxSpeed, String model) {
        this.maxSpeed = maxSpeed;
        this.model = model;
    }

    void displayInfo() {
        System.out.println("Model: " + model);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
}

interface Refuelable {
    void refuel();
}

class ElectricVehicle extends Vehicle {
    ElectricVehicle(int maxSpeed, String model) {
        super(maxSpeed, model);
    }

    void charge() {
        System.out.println(model + " is charging.");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        charge();
    }
}

class PetrolVehicle extends Vehicle implements Refuelable {
    PetrolVehicle(int maxSpeed, String model) {
        super(maxSpeed, model);
    }

    @Override
    public void refuel() {
        System.out.println(model + " is refueling.");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        refuel();
    }
}

public class VehicleManagement {
    public static void main(String[] args) {
        ElectricVehicle electricVehicle = new ElectricVehicle(150, "Hyundai ");
        PetrolVehicle petrolVehicle = new PetrolVehicle(180, "Ford ");

        electricVehicle.displayInfo();
        System.out.println();
        petrolVehicle.displayInfo();
    }
}