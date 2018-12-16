package ec.app.testar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class ResultsReader {
    String path = "output\\metrics\\";


    public TreeMap<String, String> getResults(int counter) {
        TreeMap<String, String> results = new TreeMap<String, String>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] keys = null;
        String[] values = null;

        File csvFile = new File(path + "ecj_sequence" + counter + ".csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            keys = line.split(cvsSplitBy);
            line = br.readLine();
            values = line.split(cvsSplitBy);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file to read.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            e.printStackTrace();
        }
        if (keys != null && values != null) {
            int i = 0;

            for (String k : keys) {
                String key = k.replaceAll("\\s+", "");
                String value = values[i].replaceAll("\\s+", "");
                results.put(key, value);

                i++;
            }
        }
        return results;
    }
}

