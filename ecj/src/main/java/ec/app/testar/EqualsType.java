/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

public class EqualsType extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public EqualsType() {
        name = "equalstype:";
        letter = '8';
        expectedChildren = 2;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).isSameSubTree(child(1))) {
            if (child(0)
                    .toFullString()
                    .equals("number-of-actions-of-type:type-of-action-of:random-action:")) {
                System.out.println("=> Equalstype: same tree but not equal");
                return this;
            }
            System.out.println("=> Equalstype: arguments are the same");
            return new TrueNode();
        } else if (child(0).children.length == 0
                && child(1).children.length == 0) {
            System.out.println("=> Equalstype: two unequal types");
            return new FalseNode();
        } else {
            return this;
        }
    }
}
