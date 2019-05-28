/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

import ec.EvolutionState;
import ec.Individual;
import ec.app.testar.io.SimplifiedStrategyWriter;
import ec.app.testar.nodes.DoubleData;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;

public class TestarProblem extends GPProblem implements SimpleProblemForm {
    private static final long serialVersionUID = 1;

    private Evaluator evaluator = new Evaluator();
    private SimplifiedStrategyWriter writer = new SimplifiedStrategyWriter();

    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        if (!(input instanceof DoubleData)) {
            state.output.fatal("GPData class must subclass from " + DoubleData.class, base.push(P_DATA), null);
        }
    }

    public void evaluate(final EvolutionState state, final Individual ind, final int subPopulation, final int threadNum) {
        if (!ind.evaluated) { // don't bother reevaluating
            final GPIndividual individual = (GPIndividual) ind;
            final StrategyNode mainNode = (StrategyNode) individual.trees[0].child.clone();
            final Strategy strategy = new Strategy(mainNode);

            double fitness = evaluator.evaluate(strategy, state.generation);

            writer.writeResult(state.generation, strategy);

            ((KozaFitness) ind.fitness).setStandardizedFitness(state, fitness);
            ind.evaluated = true;
        }
    }

}
