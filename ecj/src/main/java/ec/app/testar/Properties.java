package ec.app.testar;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {

    private static Properties instance;
    private int numberOfRuns;
    private int maxNumberOfRuns;
    private int sequenceLength;
    private String runMode;
    private String SUT;
    private String pathToStrategyList;
    private String fileToWriteStrategyTo;
    private String pathToJDK;
    private String pathToTestarDir;

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
            fileToWriteStrategyTo = pathToTestarDir + defaultProps.getProperty("fileToWriteStrategyTo");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read properties (evolution.properties)");
        }
    }

    public static Properties getInstance() {
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


}
