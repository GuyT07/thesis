package ec.app.testar.strategies;

import ec.app.testar.Strategy;

import java.util.ArrayList;
import java.util.Map;

public class GPStrategy implements TestStrategy {

    private Strategy strategy;
    private ArrayList<Map<String, Double>> results = new ArrayList<>();

    public GPStrategy(final Strategy strategy) {
        System.out.println("Strategy mode: GPStrategy");
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public ArrayList<Map<String, Double>> getResults() {
        return results;
    }
}
