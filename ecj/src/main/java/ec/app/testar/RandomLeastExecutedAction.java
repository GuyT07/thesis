/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

public class RandomLeastExecutedAction extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public RandomLeastExecutedAction() {
        name = "random-least-executed-action:";
        letter = 'X';
        expectedChildren = 0;
    }

}
