package ec.app.testar.io;

import ec.app.testar.Strategy;

import java.io.*;
import java.util.ArrayList;

public class StrategyReader {
    File file;
    ArrayList<Strategy> strategies = new ArrayList<>();

    public StrategyReader(File file) {
        this.file = file;
    }

    public ArrayList<Strategy> getStrategies() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\ecj\\" + file));
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
