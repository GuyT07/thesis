package ec.app.testar.strategies;

import ec.app.testar.Strategy;
import ec.app.testar.utils.MathUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public interface TestStrategy extends Serializable {

    Strategy getStrategy();

    ArrayList<Map<String, Double>> getResults();

    default double calculateFitnessValue(final Map<String, Double> result) {
        final double fitnessValue = 1 / result.get("UniqueStates");
        System.out.println("Fitness value: " + fitnessValue);
        return fitnessValue;
    }

    default double calculateFitnessValueMedian() {
        final double fitnessValue = 1 / MathUtils.getMedianOf(getResults(), "UniqueStates");
        System.out.println("Fitness value (median): " + fitnessValue);
        return fitnessValue;
    }

    default double calculateFitnessValueMean() {
        final double fitnessValue = MathUtils.getMeanOf(getResults(), "UniqueStates");
        System.out.println("Fitness value (mean): " + fitnessValue);
        return fitnessValue;
    }

    default boolean addExecutionToResults(final Map<String, Double> result) {
        return this.getResults().add(result);
    }

}
