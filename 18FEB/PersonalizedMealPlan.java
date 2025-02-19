import java.util.ArrayList;
import java.util.List;

interface MealPlan {
    String getMealType();
}

class VegetarianMeal implements MealPlan {
    @Override
    public String getMealType() {
        return "Vegetarian Meal";
    }
}

class VeganMeal implements MealPlan {
    @Override
    public String getMealType() {
        return "Vegan Meal";
    }
}

class KetoMeal implements MealPlan {
    @Override
    public String getMealType() {
        return "Keto Meal";
    }
}

class HighProteinMeal implements MealPlan {
    @Override
    public String getMealType() {
        return "High-Protein Meal";
    }
}

class Meal<T extends MealPlan> {
    private String mealName;
    private T mealType;

    public Meal(String mealName, T mealType) {
        this.mealName = mealName;
        this.mealType = mealType;
    }

    public String getMealName() {
        return mealName;
    }

    public T getMealType() {
        return mealType;
    }

    @Override
    public String toString() {
        return mealName + " - " + mealType.getMealType();
    }
}

class MealPlanGenerator {
    private List<Meal<? extends MealPlan>> mealPlans = new ArrayList<>();

    public void addMeal(Meal<? extends MealPlan> meal) {
        mealPlans.add(meal);
    }

    public void displayMealPlans() {
        for (Meal<? extends MealPlan> meal : mealPlans) {
            System.out.println(meal);
        }
    }

    public static <T extends MealPlan> Meal<T> generateMeal(String mealName, T mealType) {
        return new Meal<>(mealName, mealType);
    }
}

public class PersonalizedMealPlan {
    public static void main(String[] args) {
        Meal<VegetarianMeal> vegMeal = MealPlanGenerator.generateMeal("Paneer", new VegetarianMeal());
        Meal<VeganMeal> veganMeal = MealPlanGenerator.generateMeal("Paneer", new VeganMeal());
        Meal<KetoMeal> ketoMeal = MealPlanGenerator.generateMeal("Paneer", new KetoMeal());
        Meal<HighProteinMeal> proteinMeal = MealPlanGenerator.generateMeal("Protein",
                new HighProteinMeal());

        MealPlanGenerator generator = new MealPlanGenerator();
        generator.addMeal(vegMeal);
        generator.addMeal(veganMeal);
        generator.addMeal(ketoMeal);
        generator.addMeal(proteinMeal);

        generator.displayMealPlans();
    }
}