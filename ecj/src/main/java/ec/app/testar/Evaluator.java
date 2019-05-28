package ec.app.testar;

import ec.app.testar.strategies.GPStrategy;
import ec.app.testar.strategies.RandomStrategy;
import ec.app.testar.strategies.TestStrategy;

public class Evaluator {

    private int generation;
    private int currentRun = 0;
    private static final Properties properties = Properties.getInstance();
    private final TestarRunner testarRunner = new TestarRunner();

    public Evaluator() { }

    public double evaluate(final Strategy strategy, final int generation) {
        this.generation = generation;
        final TestStrategy testStrategy;

        switch (properties.getRunMode().toLowerCase()) {
            case "random": testStrategy = new RandomStrategy(strategy);
                break;
            case "gp": testStrategy = new GPStrategy(strategy);
                break;
            default: throw new IllegalArgumentException("Run mode not supported");
        }

        while (currentRun <= properties.getMaxNumberOfRuns()) {
            // run testar with number of runs
            testarRunner.runWith(testStrategy.getStrategy().getSimple());
            currentRun++;
        }

        return testStrategy.calculateFitnessValue();
    }
}
