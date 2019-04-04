/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class IfThenElse extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public IfThenElse() {
        name = "if-then-else:";
        letter = 'G';
        expectedChildren = 3;
        complexity = 1;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(1).isSameSubTree(child(2))) {
            System.out.println("=> If: Same children!");
            return child(1).clone();
        } else if (child(0).name.equals("true:")) {
            System.out.println("=> If: the boolean is true");
            return child(1).clone();
        } else if (child(0).name.equals("false:")) {
            System.out.println("=> If: the boolean is false");
            return child(2).clone();
        } else {
            return this;
        }
    }
}
