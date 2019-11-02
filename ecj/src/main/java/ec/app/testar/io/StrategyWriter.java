package ec.app.testar.io;

import ec.app.testar.Properties;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

public class StrategyWriter implements Serializable {
    private static Properties properties = Properties.getInstance();

    public void writeStrategy(String strategy) {
        try (final PrintWriter out = new PrintWriter(properties.getFileToWriteStrategyTo())) {
            out.print(strategy);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file strategy.txt to write the strategy in.");
            e.printStackTrace();
        }
    }
}
