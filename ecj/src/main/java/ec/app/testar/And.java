/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

import ec.EvolutionState;
import ec.Problem;
import ec.app.testar.exceptions.ShouldHaveAtLeastTwoChildrenException;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;

public class And extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public And() {
        name = "and:";
        letter = 'A';
        expectedChildren = 2;
    }

    public void eval(final EvolutionState state, final int thread,
                     final GPData input, final ADFStack stack,
                     final GPIndividual individual, final Problem problem) {
        try {
            DoubleData rd = ((DoubleData) (input));
            if (children.length < 2) {
                throw new ShouldHaveAtLeastTwoChildrenException("There should be at least 2 children");
            }

            if (children[0].nodeEquals(children[1])) {
                rd.x = 0;
            } else {
                children[0].eval(state, thread, input, stack, individual, problem);
                children[1].eval(state, thread, input, stack, individual, problem);
            }

        } catch (ShouldHaveAtLeastTwoChildrenException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public StrategyNode getSimplifiedNode() {
        final String firstChild = child(0).name;
        final String secondChild = child(1).name;

        if (firstChild.equals("true:") && secondChild.equals("true:")) {
            System.out.println("=> And: Both of the children are true");
            return new TrueNode();
        } else if (firstChild.equals("false:") || secondChild.equals("false:")) {
            System.out.println("=> And: One of the children is false");
            return new FalseNode();
        } else if (child(0).isSameSubTree(child(1))) {
            System.out.println("=> And: Children are the same");
            return child(0).clone();
        } else if (firstChild.equals("true:")) {
            System.out.println("=> And: first child is true, return second");
            return child(1).clone();
        } else if (secondChild.equals("true:")) {
            System.out.println("=> And: second child is true, return first");
            return child(0).clone();
        } else if (firstChild.equals("not:") && child(0).child(0).isSameSubTree(child(1))) {
            System.out.println("=> And: contradictory children, return false");
            return new FalseNode();
        } else if (secondChild.equals("not:") && child(1).child(0).isSameSubTree(child(0))) {
            System.out.println("=> And: contradictory children, return false");
            return new FalseNode();
        } else {
            return this;
        }
    }
}
