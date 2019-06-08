package ec.app.testar;

import ec.app.testar.io.ResultWriter;
import ec.app.testar.io.TestarResultsReader;
import ec.app.testar.strategies.GPStrategy;
import ec.app.testar.strategies.RandomStrategy;
import ec.app.testar.strategies.TestStrategy;

import java.util.Arrays;
import java.util.Map;

public class Evaluator {
    private static final Properties properties = Properties.getInstance();
    private final TestarRunner testarRunner = new TestarRunner();
    private TestarResultsReader reader = new TestarResultsReader();
    private ResultWriter writer = new ResultWriter();

    public Evaluator() { }

    public double evaluate(final Strategy strategy, final int generation) {
        final TestStrategy testStrategy = this.getCorrectStrategy(strategy);
        int currentRun = 0;
        double fitnessValue = 0;
        Map<String, Double> result = null;

        while (currentRun < properties.getMaxNumberOfRuns()) {
            // run testar with number of runs
            testarRunner.runWith(testStrategy.getStrategy().getSimple());
            result = this.getResult();
            testStrategy.addExecutionToResults(result);
            fitnessValue = testStrategy.calculateFitnessValue();
            currentRun++;
            if (currentRun != properties.getMaxNumberOfRuns()) {
                writer.writeResult(this.toString(result, fitnessValue, 0.0, 0.0), generation, testarRunner.getCounter(), strategy);
            }
            fitnessValue = testStrategy.calculateFitnessValue();
        }

        writer.writeResult(
                this.toString(
                        result,
                        fitnessValue,
                        testStrategy.calculateFitnessValueMedian(),
                        testStrategy.calculateFitnessValueMean()
                ),
                generation,
                testarRunner.getCounter(),
                strategy
        );

        return fitnessValue;
    }

    private TestStrategy getCorrectStrategy(final Strategy strategy) {
        switch (properties.getRunMode().toLowerCase()) {
            case "random":
                return new RandomStrategy(strategy);
            case "gp":
                return new GPStrategy(strategy);
            default:
                throw new IllegalArgumentException("Run mode not supported");
        }
    }

    private Map<String, Double> getResult() {
        return reader.getResults(this.testarRunner.getCounter());
    }

    public String toString(final Map<String, Double> result, final double fitnessValue, final double fitnessMedian, final double fitnessMean) {
        final StringBuilder string = new StringBuilder();
        Arrays.stream(Metric.headers).forEach(key -> string.append(result.get(key)).append(","));
        string.append(fitnessValue);
        string.append(fitnessMedian);
        string.append(fitnessMean);
        return string.toString();
    }
}
