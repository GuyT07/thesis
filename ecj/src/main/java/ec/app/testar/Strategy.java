package ec.app.testar;

public class Strategy {
    static StrategyFactory factory = new StrategyFactory();
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

    public String getShortOriginal() {
        if (shortOriginal != "") {
            return shortOriginal;
        } else {
            return original;
        }
    }

    public String getSimple() {
        if (simple != "") {
            return simple;
        } else {
            return original;
        }

    }

    public String getShortSimple() {
        if (shortSimple != "") {
            return shortSimple;
        } else {
            return original;
        }
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

    public boolean didItChange() {
        return !shortOriginal.equals(shortSimple) && !shortSimple.equals("");
    }
}
