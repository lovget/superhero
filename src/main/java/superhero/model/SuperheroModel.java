package superhero.model;

import java.util.ArrayList;
import java.util.List;

/** Stores application state, validates input and calculates the game level. */
public class SuperheroModel {

    private int height;
    private double weight;
    private int age;
    private int pullUps;
    private double sleepHours;
    private String level;
    private boolean hasData;

    /** Listener used by the active model to notify its views. */
    public interface ModelListener {
        void onModelChanged();
    }

    private final List<ModelListener> listeners = new ArrayList<>();

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    /**
     * Validates and saves all values as one state change. Invalid values do not
     * replace the previous successful state.
     */
    public void setData(int height, double weight, int age, int pullUps, double sleepHours) {
        validateData(height, weight, age, pullUps, sleepHours);

        this.height = height;
        this.weight = weight;
        this.age = age;
        this.pullUps = pullUps;
        this.sleepHours = sleepHours;
        calculateLevel();
        hasData = true;
        notifyListeners();
    }

    private void validateData(int height, double weight, int age, int pullUps, double sleepHours) {
        if (height < 100 || height > 250) {
            throw new IllegalArgumentException("Рост должен быть от 100 до 250 см.");
        }
        if (weight < 30 || weight > 300) {
            throw new IllegalArgumentException("Вес должен быть от 30 до 300 кг.");
        }
        if (age < 5 || age > 120) {
            throw new IllegalArgumentException("Возраст должен быть от 5 до 120 лет.");
        }
        if (pullUps < 0 || pullUps > 100) {
            throw new IllegalArgumentException("Количество подтягиваний должно быть от 0 до 100.");
        }
        if (sleepHours < 0 || sleepHours > 24) {
            throw new IllegalArgumentException("Количество часов сна должно быть от 0 до 24.");
        }
    }

    /** Calculates a simple game score; it is not a medical assessment. */
    private void calculateLevel() {
        int points = 0;

        // Points for pull-ups: 0-4, 5-9, 10-19, 20 and more.
        if (pullUps >= 20) {
            points += 3;
        } else if (pullUps >= 10) {
            points += 2;
        } else if (pullUps >= 5) {
            points += 1;
        }

        // Points for sleep. A short sleep always results in an urgent vacation.
        if (sleepHours < 5) {
            points -= 2;
        } else if (sleepHours < 6) {
            points += 0;
        } else if (sleepHours < 7) {
            points += 1;
        } else if (sleepHours <= 9) {
            points += 3;
        } else {
            points += 1;
        }

        // Points for age.
        if (age <= 17) {
            points += 1;
        } else if (age <= 40) {
            points += 2;
        } else if (age <= 60) {
            points += 1;
        }

        // BMI is used only as a conditional game indicator, without diagnoses.
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        if (bmi >= 18.5 && bmi <= 25) {
            points += 2;
        } else if (bmi >= 17 && bmi <= 30) {
            points += 1;
        }

        if (sleepHours < 5) {
            level = "Нужно срочно в отпуск";
        } else if (points >= 8) {
            level = "Легенда";
        } else if (points >= 5) {
            level = "Герой";
        } else {
            level = "Стажёр";
        }
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    public boolean hasData() { return hasData; }
    public int getHeight() { return height; }
    public double getWeight() { return weight; }
    public int getAge() { return age; }
    public int getPullUps() { return pullUps; }
    public double getSleepHours() { return sleepHours; }
    public String getLevel() { return level; }
}