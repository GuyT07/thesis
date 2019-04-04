/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class RandomActionOfTypeOtherThan extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public RandomActionOfTypeOtherThan() {
        name = "random-action-of-type-other-than:";
        letter = 'W';
        expectedChildren = 1;
    }

}
