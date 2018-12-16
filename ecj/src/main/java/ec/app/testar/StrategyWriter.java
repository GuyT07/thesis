package ec.app.testar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StrategyWriter {
    private static final String path = "settings\\";

    public void writeStrategy(String strategy) {
        try (PrintWriter out = new PrintWriter(path + "strategy.txt");) {

            out.print(strategy);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file strategy.txt to write the strategy in.");
            e.printStackTrace();
        }
    }
}
