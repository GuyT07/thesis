/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

public class GreaterThan extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public GreaterThan() {
        name = "greater-than:";
        letter = 'F';
        expectedChildren = 2;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).isSameSubTree(child(1))) {
            if (child(0).name.equals("random-number:")
                    || child(0).toFullString().contains("random-action:")) {
                System.out.println("=> Greater than: same tree but not equal");
                return this;
            } else {
                System.out.println("=> Greater than: arguments are the same");
                return new FalseNode();
            }
        } else {
            return this;
        }
    }
}
