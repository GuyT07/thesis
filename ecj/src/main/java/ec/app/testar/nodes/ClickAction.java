/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class ClickAction extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public ClickAction() {
        name = "click-action:";
        letter = 'B';
        expectedChildren = 0;
    }

}
