package ec.app.testar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Properties {

    private static Properties instance;
    private final int numberOfRuns;
    private final int maxNumberOfRuns;
    private final int sequenceLength;
    private final String runMode;
    private final String SUT;
    private final String pathToStrategyList;
    private final String fileToWriteStrategyTo;
    private final String pathToJDK;
    private final String pathToTestarDir;
    private final String pathToMetricsDir;
    private final String pathToSimplifiedStrategy;
    private final String pathToArchive;

    private Properties() {
        final java.util.Properties defaultProps = new java.util.Properties();
        final FileInputStream in;
        try {
            in = new FileInputStream("evolution.properties");
            defaultProps.load(in);
            in.close();
            numberOfRuns = Integer.parseInt(defaultProps.getProperty("numberOfRuns"));
            maxNumberOfRuns = Integer.parseInt(defaultProps.getProperty("maxNumberOfRuns"));
            sequenceLength = Integer.parseInt(defaultProps.getProperty("sequenceLength"));
            runMode = defaultProps.getProperty("runMode");
            SUT = defaultProps.getProperty("SUT");
            pathToTestarDir = System.getenv("USERPROFILE") + defaultProps.getProperty("pathToTestarDir");
            pathToJDK = defaultProps.getProperty("pathToJDK");
            pathToStrategyList = pathToTestarDir + defaultProps.getProperty("pathToStrategyList");
            pathToSimplifiedStrategy = pathToTestarDir + defaultProps.getProperty("pathToSimplifiedStrategy");
            pathToArchive = pathToTestarDir + defaultProps.getProperty("pathToArchive");
            fileToWriteStrategyTo = pathToTestarDir + defaultProps.getProperty("fileToWriteStrategyTo");
            pathToMetricsDir = pathToTestarDir + File.separator + "resources" + File.separator + "output" + File.separator + "metrics" + File.separator;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read properties (evolution.properties)");
        }
    }

    public static synchronized Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    int getNumberOfRuns() {
        return numberOfRuns;
    }

    public int getMaxNumberOfRuns() {
        return maxNumberOfRuns;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    String getRunMode() {
        return runMode;
    }

    public String getSUT() {
        return SUT;
    }

    public String getPathToJDK() {
        return pathToJDK;
    }

    public String getPathToTestarDir() {
        return pathToTestarDir;
    }

    public String getPathToStrategyList() {
        return pathToStrategyList;
    }

    public String getFileToWriteStrategyTo() {
        return fileToWriteStrategyTo;
    }

    public String getPathToMetricsDir() {
        return pathToMetricsDir;
    }

    public String getPathToSimplifiedStrategy() {
        return pathToSimplifiedStrategy;
    }

    public String getPathToArchive() {
        return pathToArchive;
    }
}
