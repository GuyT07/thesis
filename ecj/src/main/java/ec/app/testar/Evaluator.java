package ec.app.testar;

import ec.app.testar.io.ArchiveReader;
import ec.app.testar.io.ResultWriter;

import java.util.TreeMap;

public class Evaluator {

    private int generation;
    private ResultWriter resultwriter = new ResultWriter();
    private TreeMap<String, Result> previousStrategies;
    private TestarRunner testar = new TestarRunner();
    private Properties properties = Properties.getInstance();

    public Evaluator() {
        this.previousStrategies = new ArchiveReader().getArchive();
    }

    public double evaluate(final Strategy strategy, final int generation) {
        if (this.generation != generation) {
            this.generation = generation;
        }
        Result result = null;
        double fitness;
        boolean isFirstRun = true;
        int runNr = 0;
        boolean maxReached = false;
        Result newResult;

        if (previousStrategies.keySet().contains(strategy.getShortSimple())) {
            isFirstRun = false;
            result = previousStrategies.get(strategy.getShortSimple());
            maxReached = result.maxReached();
        }
        if (maxReached) {
            testar.didNotRun();
            resultwriter.writeResult(generation, testar, strategy, result);
        }
        while (runNr < this.properties.getNumberOfRuns() && !maxReached) {

            if (this.properties.getRunMode().equals("Random")) {
                newResult = new Result();
            } else if (testar.runWith(strategy.getSimple())) {
                newResult = testar.getResult();
            } else {
                System.out.println("An error occurred.");
                break;
            }

            if (!isFirstRun) {
                result.addResult(newResult);
            } else {
                result = newResult;
                previousStrategies.put(strategy.getShortSimple(), result);
                isFirstRun = false;
            }

            fitness = result.getFitness();
            resultwriter.writeResult(generation, testar, strategy, result);
            runNr++;
            maxReached = result.maxReached();
        }

        fitness = result.getFitness();
        return fitness;
    }
}
