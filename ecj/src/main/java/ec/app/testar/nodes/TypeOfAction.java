/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class TypeOfAction extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public TypeOfAction() {
        name = "type-of-action-of:";
        letter = '5';
        expectedChildren = 1;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("random-action-of-type:")
                || child(0).name.equals("random-unexecuted-action-of-type:")) {
            System.out.println("=> Type of action of: useless loop");
            StrategyNode result = child(0).child(0).clone();
            return result;
        }
        return this;
    }
}
