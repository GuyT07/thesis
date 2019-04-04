/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class Or extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public Or() {
        name = "or:";
        letter = 'S';
        expectedChildren = 2;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("false:") && child(1).name.equals("false:")) {
            System.out.println("=> Or: Both children are false");
            return new FalseNode();
        } else if (child(0).name.equals("true:")
                || child(1).name.equals("true:")) {
            System.out.println("=> Or: One of the children is true");
            return new TrueNode();
        } else if (child(0).name.equals("false:")) {
            System.out
                    .println("=> Or: the first child is false, return the second");
            return child(1).clone();
        } else if (child(1).name.equals("false:")) {
            System.out
                    .println("=> Or: the second child is false, return the first");
            return child(0).clone();
        } else if (child(0).isSameSubTree(child(1))) {
            System.out.println("=> Or: Children are the same");
            return child(0).clone();
        } else if (child(0).name.equals("not:") && child(0).child(0).isSameSubTree(child(1))) {
            System.out.println("=> Or: contradictory children, return true");
            return new TrueNode();
        } else if (child(1).name.equals("not:") && child(1).child(0).isSameSubTree(child(0))) {
            System.out.println("=> Or: contradictory children, return true");
            return new TrueNode();
        } else {
            return this;
        }
    }
}
