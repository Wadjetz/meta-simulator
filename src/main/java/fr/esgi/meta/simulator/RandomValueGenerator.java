package fr.esgi.meta.simulator;

import java.util.Random;

/**
 * Generate a random value each time getValue is called, between the two provided bounds (inclusive)
 *
 * Created by Vuzi on 17/01/2016.
 */
public class RandomValueGenerator {

    private final double min;
    private final double max;

    Random r = new Random();

    public RandomValueGenerator(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getValue() {
        return min + (max - min) * r.nextDouble();
    }

    public double getValueMin() {
        return min;
    }

    public double getValueMax() {
        return max;
    }
}
