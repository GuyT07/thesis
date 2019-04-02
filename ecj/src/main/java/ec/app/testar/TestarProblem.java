/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestarProblem extends GPProblem implements SimpleProblemForm {
    private static final long serialVersionUID = 1;

    private String runMode;

    private Evaluator evaluator;
    private SimplifiedStrategyWriter writer = new SimplifiedStrategyWriter();
    private StrategyWindow window;


    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        if (!(input instanceof DoubleData)) {
            state.output.fatal("GPData class must subclass from " + DoubleData.class, base.push(P_DATA), null);
        }
        final Properties defaultProps = new Properties();
        final FileInputStream in;
        try {
            in = new FileInputStream("evolution.properties");
            defaultProps.load(in);
            in.close();
            int numberOfRuns = Integer.parseInt(defaultProps.getProperty("numberOfRuns"));
            int maxNumberOfRuns = Integer.parseInt(defaultProps.getProperty("maxNumberOfRuns"));
            int sequenceLength = Integer.parseInt(defaultProps.getProperty("sequenceLength"));
            runMode = defaultProps.getProperty("runMode");
            String SUT = defaultProps.getProperty("SUT");

            evaluator = new Evaluator(numberOfRuns, sequenceLength, SUT);
            window = new StrategyWindow(evaluator);
            evaluator.setWindow(window);
            Result.setMax(maxNumberOfRuns);
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setVisible(true);

    }

    public void evaluate(final EvolutionState state, final Individual ind, final int subPopulation, final int threadNum) {
        window.setVisible(true);
        final GPIndividual gpind = (GPIndividual) ind;
        final StrategyNode mainNode = (StrategyNode) gpind.trees[0].child.clone();
        final Strategy strategy = new Strategy(mainNode);

        writer.writeResult(state.generation, strategy);

        double fitness = evaluator.evaluate(strategy, state.generation, runMode);

        ((KozaFitness) ind.fitness).setStandardizedFitness(state, fitness);
        ind.evaluated = true;
    }

}
