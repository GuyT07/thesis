/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class Equals extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public Equals() {
        name = "equals:";
        letter = 'E';
        expectedChildren = 2;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("random-number:")
                && child(1).name.equals("random-number:")) {
            System.out.println("=> Equals: random is random, unlikely, but not equal");
            return this;
        } else if (child(0).isSameSubTree(child(1))) {
            if (child(0).toFullString().equals("number-of-actions-of-type:type-of-action-of:random-action:")) {
                System.out.println("=> Equals: Same trees but not equal");
                return this;
            } else {
                System.out.println("=> Equals: arguments are the same");
                return new TrueNode();
            }
        } else {
            return this;
        }
    }
}
