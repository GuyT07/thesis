package ec.app.testar;

import ec.app.testar.io.SimplifiedStrategyWriter;
import ec.app.testar.io.StrategyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class StrategySetRunner {
    static File file = new File("..\\ecjapp\\strategylist.txt");
    private static StrategyReader reader = new StrategyReader(file);
    private static SimplifiedStrategyWriter writer = new SimplifiedStrategyWriter();
    private static int numberOfRuns;
    private static int sequenceLength;
    private static String runMode;
    private static StrategyWindow window;
    private static Evaluator evaluator;
    private static ArrayList<Strategy> strategies;


    public static void main(String[] args) {
        setup();

        for (Strategy strategy : strategies) {
            evaluator.evaluate(strategy, 0, runMode);
            writer.writeResult(0, strategy);
        }
    }

    private static void setup() {
        Properties defaultProps = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("..\\ecjapp\\evolution.properties");
            defaultProps.load(in);
            in.close();
            numberOfRuns = Integer.parseInt(defaultProps.getProperty("numberOfRuns"));
            int maxNumberOfRuns = Integer.parseInt(defaultProps.getProperty("maxNumberOfRuns"));
            sequenceLength = Integer.parseInt(defaultProps.getProperty("sequenceLength"));
            runMode = defaultProps.getProperty("runMode");
            String SUT = defaultProps.getProperty("SUT");

            evaluator = new Evaluator(numberOfRuns, sequenceLength, SUT);
            window = new StrategyWindow(evaluator);
            evaluator.setWindow(window);
            Result.setMax(maxNumberOfRuns);
        } catch (IOException e) {
            e.printStackTrace();
        }
        strategies = reader.getStrategies();

        window.setVisible(true);
    }

}
