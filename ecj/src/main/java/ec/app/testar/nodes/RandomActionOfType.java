/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class RandomActionOfType extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public RandomActionOfType() {
        name = "random-action-of-type:";
        letter = 'V';
        expectedChildren = 1;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("type-of-action-of:")
                && child(0).child(0).name.equals("random-action:")) {
            System.out.println("=> Random action of type: type of random");
            return child(0).child(0).clone();
        }
        return this;
    }
}
