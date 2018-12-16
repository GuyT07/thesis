/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

public class RandomUnexecutedActionOfType extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public RandomUnexecutedActionOfType() {
        name = "random-unexecuted-action-of-type:";
        letter = '2';
        expectedChildren = 1;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("type-of-action-of:")
                && child(0).child(0).name.equals("random-unexecuted-action:")) {
            System.out.println("=> Random unexecuted action of type: type of random");
            return child(0).child(0).clone();
        }
        if (child(0).name.equals("type-of-action-of:")
                && child(0).child(0).name.equals("random-unexecuted-action of type:")) {
            System.out.println("=> Random unexecuted action of type: useless loop");
            return child(0).child(0).clone();
        }
        return this;
    }
}
