package ec.app.testar.io;

import ec.app.testar.Properties;
import ec.app.testar.Strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimplifiedStrategyWriter {
    private static Properties properties = Properties.getInstance();
    private static final String path = properties.getPathToSimplifiedStrategy();
    private File file;

    public SimplifiedStrategyWriter() {
        int i = 0;
        while (file == null || file.exists()) {
            i++;
            file = new File(path + File.separator + "simplestrategies" + i + ".csv");
        }
        try {
            file.createNewFile();
            PrintWriter out = new PrintWriter(new FileWriter(file));
            String header = "Generation,Original strategy,Short original strategy,"
                    + "simplified strategy,Short simplified strategy,Original Depth,"
                    + "Simplified Depth,Original Complexity,Simple Complexity";
            out.println(header);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in creating the results file.");
            e.printStackTrace();
        }

    }

    public void writeResult(final int generation, final Strategy strategy) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            String resultLine = generation + ","
                    + strategy.getOriginal() + ","
                    + strategy.getShortOriginal() + ","
                    + strategy.getSimple() + ","
                    + strategy.getShortSimple() + ","
                    + strategy.getOriginalDepth() + ","
                    + strategy.getSimpleDepth() + ","
                    + strategy.getOriginalComplexity() + ","
                    + strategy.getSimpleComplexity();
            out.println(resultLine);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in writing the results.");
            e.printStackTrace();
        }

    }
}
