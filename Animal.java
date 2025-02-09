class Animal {
    String name;
    int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void makeSound() {
        System.out.println(" animal sound");
    }
}

class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + "Dog Sounds");
    }
}

class Cat extends Animal {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + " Car Sound");
    }
}

class Bird extends Animal {
    Bird(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + "Sound");
    }
}

public class AnimalHierarchy {
    public static void main(String[] args) {
        Animal myDog = new Dog("DOG", 3);
        Animal myCat = new Cat("CAT", 2);
        Animal myBird = new Bird("BIRD", 1);

        myDog.makeSound();
        myCat.makeSound();
        myBird.makeSound();
    }
}