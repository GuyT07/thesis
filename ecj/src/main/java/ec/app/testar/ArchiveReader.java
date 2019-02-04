package ec.app.testar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class ArchiveReader {
    private String path = "..\\ecjapp\\";

    public TreeMap<String, Result> getArchive(String SUT, int sequenceLength) {
        TreeMap<String, Result> archive = new TreeMap<>();
        TreeMap<String, String> lineValues = new TreeMap<>();
        Result result;
        String strategy;

        BufferedReader br;
        String line;
        String cvsSplitBy = ",";
        String[] keys;
        String[] values;
        boolean existing;
        boolean full;

        File csvFile = new File(path + "archive_" + SUT + "_" + sequenceLength + ".csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            keys = line.split(cvsSplitBy);
            System.out.println(keys);
            while ((line = br.readLine()) != null) {
                values = line.split(cvsSplitBy);
                System.out.println(values);
                if (keys != null && values != null) {
                    int i = 0;

                    for (String key : keys) {
                        String value = values[i];
                        lineValues.put(key, value);

                        i++;
                    }
                }
                if (!(lineValues.get("Counter").equals("0"))) {
                    strategy = lineValues.get("Simplified strategy");
                    result = new Result(lineValues);
                    existing = archive.containsKey(strategy);
                    full = existing ? archive.get(strategy).maxReached() : false;

                    if (existing && !full) {
                        archive.get(strategy).addResult(result);
                    } else if (!existing) {
                        archive.put(strategy, result);
                    }
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file to read.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            e.printStackTrace();
        }

        return archive;
    }
}

