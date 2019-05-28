package ec.app.testar.strategies;

import ec.app.testar.Strategy;
import ec.app.testar.utils.MathUtils;

import java.util.ArrayList;
import java.util.Map;

public interface TestStrategy {

    public Strategy getStrategy();

    public default double calculateFitnessValue() {
        return 1 / MathUtils.getMeanOf(getResults(), "UniqueStates");
    }

    public ArrayList<Map<String, Double>> getResults();

    public default boolean addExecutionToResults(final Map<String, Double> result) {
        return this.getResults().add(result);
    }

}
