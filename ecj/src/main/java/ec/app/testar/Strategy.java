package ec.app.testar;

import java.io.Serializable;

public class Strategy implements Serializable {

    private static StrategyFactory factory = new StrategyFactory();
    private String original = "";
    private String shortOriginal = "";
    private String simple = "";
    private String shortSimple = "";
    private int originalDepth = 0;
    private int simpleDepth = 0;
    private int originalComplexity = 0;
    private int simpleComplexity = 0;

    public Strategy(final String original) {
        StrategyNode originalTree = factory.makeStrategy(original);
        fillStrings(originalTree);
    }

    public Strategy(final StrategyNode originalTree) {
        fillStrings(originalTree);
    }

    private void fillStrings(final StrategyNode originalTree) {
        this.original = originalTree.toFullString();
        this.shortOriginal = originalTree.toShortString();
        this.originalDepth = originalTree.getDepth();
        this.originalComplexity = originalTree.getComplexity() + 1;

        StrategyNode simpleTree = originalTree.getSimplifiedTree();
        this.simple = simpleTree.toFullString();
        this.shortSimple = simpleTree.toShortString();
        this.simpleDepth = simpleTree.getDepth();
        this.simpleComplexity = simpleTree.getComplexity() + 1;
    }

    public String getOriginal() {
        return original;
    }

    public String getShortOriginal() {
        return !shortOriginal.equals("") ? shortOriginal : original;
    }

    public String getSimple() {
        return !simple.equals("") ? simple : original;
    }

    public String getShortSimple() {
        return !shortSimple.equals("") ? shortSimple : original;
    }

    public int getOriginalDepth() {
        return originalDepth;
    }

    public int getSimpleDepth() {
        return simpleDepth;
    }

    public int getOriginalComplexity() {
        return originalComplexity;
    }

    public int getSimpleComplexity() {
        return simpleComplexity;
    }

    boolean didItChange() {
        return !shortOriginal.equals(shortSimple) && !shortSimple.equals("");
    }
}
