package ec.app.testar;

import ec.EvolutionState;
import ec.Problem;
import ec.app.testar.nodes.DoubleData;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

import java.util.ArrayList;

public abstract class StrategyNode extends GPNode {

    private static final long serialVersionUID = 1L;
    public String name;
    public char letter;
    public int expectedChildren;
    public int complexity = 0;

    public void eval(final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem
    ) {
        DoubleData rd = ((DoubleData) (input));
        rd.x = 1;
    }

    public StrategyNode getSimplifiedNode() {
        return this.clone();
    }

    public StrategyNode getSimplifiedTree() {
        for (int i = 0; i < children.length; i++) {
            children[i] = child(i).getSimplifiedTree();
        }
        StrategyNode result = getSimplifiedNode();
        result.parent = this.parent;
        return result;
    }

    public boolean isSameSubTree(StrategyNode other) {

        if (this.nodeEquals(other)) {
            for (int i = 0; i < children.length; i++) {
                if (!child(i).isSameSubTree(other.child(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return name;
    }

    public char getLetter() {
        return letter;
    }

    public int expectedChildren() {
        return expectedChildren;
    }

    public int getDepth() {
        int maxChildDepth = 0;
        int thisChildDepth;
        for (int i = 0; i < children.length; i++) {
            thisChildDepth = child(i).getDepth();
            if (thisChildDepth > maxChildDepth) {
                maxChildDepth = thisChildDepth;
            }
        }
        return maxChildDepth + 1;
    }

    public int getComplexity() {
        int result = this.complexity;
        for (int i = 0; i < children.length; i++) {
            result += child(i).getComplexity();
        }
        return result;
    }

    public String toShortString() {
        final StringBuilder result = new StringBuilder("" + getLetter());
        for (int i = 0; i < children.length; i++) {
            result.append(child(i).toShortString());
        }
        return result.toString();
    }

    public StrategyNode child(int i) {
        return (StrategyNode) children[i];
    }

    public void setChild(int i, GPNode node) {
        children[i] = node;
        children[i].parent = this;
    }

    public String toFullString() {
        final StringBuilder result = new StringBuilder(toString());
        for (int i = 0; i < children.length; i++) {
            result.append(child(i).toFullString());
        }
        return result.toString();
    }

    @Override
    public StrategyNode clone() {
        return (StrategyNode) super.clone();
    }

    public void setChildren(ArrayList<StrategyNode> children) {
        this.children = new GPNode[expectedChildren()];
        for (int i = 0; i < children.size(); i++) {
            setChild(i, children.get(i));
        }
    }

}
