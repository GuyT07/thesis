package ec.app.testar.io;

import ec.app.testar.Properties;
import ec.app.testar.Strategy;

import java.io.*;
import java.util.ArrayList;

public class StrategyReader {

    private static Properties properties = Properties.getInstance();
    private static File file = new File(properties.getPathToStrategyList());
    private ArrayList<Strategy> strategies = new ArrayList<>();

    public StrategyReader() {
    }

    public ArrayList<Strategy> getStrategies() {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                strategies.add(new Strategy(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file to read.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            e.printStackTrace();
        }

        return strategies;
    }


}
