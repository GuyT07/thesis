package ec.app.testar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Result {
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
    private static int MAX_EXECUTIONS = 20;
    private Double fitnessValue;
    private int executions = 0;
    private TreeMap<String, Double> avgResult = new TreeMap<>();
    private TreeMap<String, Double> medianResult = new TreeMap<>();
    private ArrayList<TreeMap<String, Double>> allResults = new ArrayList<>();

    Result() {
        TreeMap<String, Double> thisResult = new TreeMap<>();
        for (String key : allMetrics) {
            thisResult.put(key, Math.random() * 100);
        }
        addExecution(thisResult);
    }

    public Result(TreeMap<String, String> results) {
        TreeMap<String, Double> thisResult = new TreeMap<>();
        for (String key : allMetrics) {
            thisResult.put(key, Double.parseDouble(results.get(key)));
        }
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
        fitnessValue = medianResult.get("UniqueStates") * ((medianResult.get("Severity") + 1));
    }

    private void calculateAverage() {
        for (String metric : allMetrics) {
            Double sum = 0.0;
            for (TreeMap<String, Double> oneResult : allResults) {
                sum += oneResult.get(metric);
            }
            avgResult.replace(metric, sum / allResults.size());
        }
    }

    private void calculateMedian() {
        int nrResults = allResults.size();
        int medianNr = nrResults / 2;
        for (String metric : allMetrics) {
            List<Double> values = new ArrayList<Double>();
            for (TreeMap<String, Double> oneResult : allResults) {
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
        TreeMap<String, Double> printResult;

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

    private void addExecution(TreeMap<String, Double> thisResult) {
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
