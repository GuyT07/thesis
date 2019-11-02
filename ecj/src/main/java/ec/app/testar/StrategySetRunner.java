package ec.app.testar;

import ec.app.testar.io.SimplifiedStrategyWriter;
import ec.app.testar.io.StrategyReader;

import java.io.Serializable;
import java.util.ArrayList;

public class StrategySetRunner implements Serializable {

    private static StrategyReader reader = new StrategyReader();
    private static SimplifiedStrategyWriter writer = new SimplifiedStrategyWriter();
    private static Evaluator evaluator;
    private static ArrayList<Strategy> strategies;

    public static void main(String[] args) {
        setup();
        strategies.forEach(strategy -> {
            evaluator.evaluate(strategy, 0);
            writer.writeResult(0, strategy);
        });
    }

    private static void setup() {
        evaluator = new Evaluator();
        strategies = reader.getStrategies();
    }

}
