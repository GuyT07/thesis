/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar;

public class Escape extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public Escape() {
        name = "escape:";
        letter = '-';
        expectedChildren = 0;
    }

}
