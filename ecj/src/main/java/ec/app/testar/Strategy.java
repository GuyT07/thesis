package ec.app.testar;

public class Strategy {
    private static StrategyFactory factory = new StrategyFactory();
    private String original = "";
    private String shortOriginal = "";
    private String simple = "";
    private String shortSimple = "";
    private int originalDepth = 0;
    private int simpleDepth = 0;
    private int originalComplexity = 0;
    private int simpleComplexity = 0;

    public Strategy(String original) {
        StrategyNode originalTree = factory.makeStrategy(original);
        fillStrings(originalTree);
    }

    public Strategy(StrategyNode originalTree) {
        fillStrings(originalTree);
    }

    private void fillStrings(StrategyNode originalTree) {
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

    String getShortOriginal() {
        return !shortOriginal.equals("") ? shortOriginal : original;
    }

    public String getSimple() {
        return !simple.equals("") ? simple : original;
    }

    String getShortSimple() {
        return !shortSimple.equals("") ? shortSimple : original;
    }

    int getOriginalDepth() {
        return originalDepth;
    }

    int getSimpleDepth() {
        return simpleDepth;
    }

    int getOriginalComplexity() {
        return originalComplexity;
    }

    int getSimpleComplexity() {
        return simpleComplexity;
    }

    boolean didItChange() {
        return !shortOriginal.equals(shortSimple) && !shortSimple.equals("");
    }
}
