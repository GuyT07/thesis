package ec.app.testar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static Properties properties = Properties.getInstance();
    public static final String[] allMetrics = {
            "Sequence", // Sequence
            "Duration", // Time it took to execute sequence
            "States", // # of abstract states visited
            "Actions", // # of actions executed
            "UniqueStates", // # of unique states visited
            "UniqueActions", // # of unique actions executed
            "NotFoundActions", // # of actions that were selected but not found (eg. click action is selected, only type actions are available)
            "IrregularActions",
            "Severity"
    };
    private static int MAX_EXECUTIONS = properties.getMaxNumberOfRuns();
    private Double fitnessValue;
    private int executions = 0;
    private Map<String, Double> avgResult = new HashMap<>();
    private Map<String, Double> medianResult = new HashMap<>();
    private ArrayList<Map<String, Double>> allResults = new ArrayList<>();

    Result() {
        final Map<String, Double> thisResult = new HashMap<>();
        // TODO: Just why?
        Arrays.stream(allMetrics)
                .forEach(key -> thisResult.put(key, Math.random() * 100));
        addExecution(thisResult);
    }

    public Result(final Map<String, String> results) {
        final Map<String, Double> thisResult = new HashMap<>();
        Arrays.stream(allMetrics)
                .forEach((key) -> thisResult.put(key, Double.parseDouble(results.get(key))));
        addExecution(thisResult);
    }

    public static void setMax(int max) {
        MAX_EXECUTIONS = max;
    }

    public void addResult(Result result2) {
        if (executions >= MAX_EXECUTIONS) {
            System.out.println("Max executions reached");
        } else {
            result2.allResults.forEach(this::addExecution);
        }
    }

    private void setFitnessValue() {
        fitnessValue = 1 / (medianResult.get("UniqueStates"));
    }

    private void calculateAverage() {
        Arrays.stream(allMetrics)
                .forEach((metric) -> {
                    final double sum = allResults.stream()
                            .mapToDouble((singleResult) -> singleResult.get(metric))
                            .sum();
                    avgResult.replace(metric, sum / allResults.size());
                });
    }

    private void calculateMedian() {
        int nrResults = allResults.size();
        int medianNr = nrResults / 2;
        for (String metric : allMetrics) {
            List<Double> values = new ArrayList<>();
            for (Map<String, Double> oneResult : allResults) {
                values.add(oneResult.get(metric));
            }
            Collections.sort(values);
            medianResult.replace(metric, values.get(medianNr));
        }
    }

    public boolean maxReached() {
        return executions == MAX_EXECUTIONS;
    }

    public Double getFitnessValue() {
        return fitnessValue;
    }

    public String toString(boolean didTestarRun) {
        StringBuilder string = new StringBuilder();
        Map<String, Double> printResult;

        if (didTestarRun) {
            printResult = allResults.get(allResults.size() - 1);
        } else {
            printResult = medianResult;
        }
        for (String key : allMetrics) {
            string.append(printResult.get(key)).append(",");
        }
        string.append(fitnessValue);
        return string.toString();
    }

    private void addExecution(final Map<String, Double> thisResult) {
        allResults.add(thisResult);
        executions++;
        if (executions == 1) {
            avgResult = thisResult;
            medianResult = thisResult;
        } else {
            calculateAverage();
            calculateMedian();
        }

        setFitnessValue();
        if (executions == MAX_EXECUTIONS) {
            allResults.clear();
            allResults.add(medianResult);
        }
    }

}
