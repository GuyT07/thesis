package ec.app.testar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Result {
    public static final String[] allMetrics = {
            "maxpath", "graph-states", "graph-actions",
            "minCvg(%)", "maxCvg(%)", "abstract-states",
            "test-actions", "SUTRAM(KB)", "SUTCPU(%)",
            "TestRAM(MB)", "TestCPU(s)", "FAILS", "random-actions"};
    private static int MAX_EXECUTIONS = 20;
    private Double fitness;
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

    private void calculateFitness() {
        double fitfactor = medianResult.get("maxpath") + medianResult.get("graph-states");
        // Calculate that into a value with 0 as optimum
        fitness = 10 / Math.sqrt(Math.max(1.0, fitfactor));
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

    public Double getFitness() {
        return fitness;
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
        string.append(fitness);
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

        calculateFitness();
        if (executions == MAX_EXECUTIONS) {
            allResults.clear();
            allResults.add(medianResult);
        }
    }

}
