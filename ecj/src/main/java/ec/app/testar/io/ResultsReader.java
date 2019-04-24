package ec.app.testar.io;

import ec.app.testar.Properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;

public class ResultsReader {
    private Properties properties = Properties.getInstance();
    private String path = properties.getPathToMetricsDir();

    public TreeMap<String, String> getResults(final int counter) {
        TreeMap<String, String> results = new TreeMap<>();

        final BufferedReader br;
        final String cvsSeparator = ",";
        String line;
        String[] keys = null;
        String[] values = null;
        Optional<Path> filePath = Optional.empty();


        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            filePath = paths
                    .filter(Files::isRegularFile)
                    .filter(file -> file.endsWith("ecj_sequence_" + counter + ".csv"))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            line = Files.readAllLines(filePath.orElseThrow(RuntimeException::new).toAbsolutePath()).get(0);
            keys = line.split(cvsSeparator);
            line = Files.readAllLines(filePath.orElseThrow(RuntimeException::new).toAbsolutePath()).get(1);
            values = line.split(cvsSeparator);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file");
        }

        int i = 0;

        for (String k : keys) {
            String key = k.replaceAll("\\s+", "");
            String value = values[i].replaceAll("\\s+", "");
            results.put(key, value);

            i++;
        }
        return results;
    }
}

