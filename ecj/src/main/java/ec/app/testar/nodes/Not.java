/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package ec.app.testar.nodes;

import ec.app.testar.StrategyNode;

public class Not extends StrategyNode {

    private static final long serialVersionUID = 1L;

    public Not() {
        name = "not:";
        letter = 'I';
        expectedChildren = 1;
    }

    public StrategyNode getSimplifiedNode() {
        if (child(0).name.equals("true:")) {
            System.out.println("=> Not: child is true");
            return new FalseNode();
        } else if (child(0).name.equals("false:")) {
            System.out.println("=> Not: child is false");
            return new TrueNode();
        } else if (child(0).name.equals("not:")) {
            System.out.println("=> Not: double not");
            return child(0).child(0).clone();
        } else {
            return this;
        }
    }
}
