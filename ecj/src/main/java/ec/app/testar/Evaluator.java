package ec.app.testar;

import ec.app.testar.io.ArchiveReader;
import ec.app.testar.io.ResultWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class Evaluator implements ActionListener {
    private int nrOfRunsPerEval;
    private int sequenceLength;
    private int generation;
    private int indCounter = 0;
    private ResultWriter resultwriter = new ResultWriter();
    private TreeMap<String, Result> previousStrategies;
    private TestarRunner testar = new TestarRunner();
    private boolean isPaused = false;
    private StrategyWindow window;

    public Evaluator(int runs, int sequenceLength, String SUT) {
        nrOfRunsPerEval = runs;
        this.sequenceLength = sequenceLength;
        previousStrategies = new ArchiveReader().getArchive(SUT, sequenceLength);
    }

    public void setWindow(StrategyWindow window) {
        this.window = window;
    }

    public double evaluate(Strategy strategy, int generation, String runMode) {
        if (this.generation == generation) {
            indCounter += 1;
        } else {
            indCounter = 0;
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
        while (runNr < nrOfRunsPerEval && !maxReached) {
            if (isPaused) pause();
            window.updateFields(strategy, generation, indCounter, runNr);

            if (runMode.equals("Random")) {
                newResult = new Result();
            } else if (testar.runWith(strategy.getSimple(), sequenceLength)) {
                newResult = testar.getResult();
            } else {
                System.out.println("An error occurred.");
                isPaused = true;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "pause") {
            isPaused = !isPaused;
            window.togglePause(isPaused);
        } else if (e.getActionCommand() == "stop") {
            System.exit(0);
        }

    }

    private void pause() {
        while (isPaused) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
