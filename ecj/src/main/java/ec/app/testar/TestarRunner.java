package ec.app.testar;

import ec.app.testar.io.StrategyWriter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class TestarRunner implements Serializable {
    private static Properties properties = Properties.getInstance();
    private String path = properties.getPathToMetricsDir();
    private int sequenceLength = properties.getSequenceLength();
    private StrategyWriter writer = new StrategyWriter();
    private int nrOfTries;
    private int counter = 1;
    private boolean didTestarRun = false;

    boolean runWith(final String strategy) {
        writer.writeStrategy(strategy);
        didTestarRun = false;

        nrOfTries = 0;
        while (!didTestarRun && nrOfTries < 100) {
            counter = getCounter(counter);
            run();
            nrOfTries++;
        }
        return nrOfTries < 5;
    }

    private void run() {
        try {
            final String cmd = "cmd /c cd " + properties.getPathToTestarDir() + " && START \"\" " + properties.getPathToJDK() + " -Dheadless=true  -DSUTConnector=desktop_gp_ecj -DStrategyFile=" + properties.getFileToWriteStrategyTo() + " -DSequenceLength=" + sequenceLength + " -Dcounter=" + counter + " -cp \"testar.jar;lib/*\" org.fruit.monkey.Main";
            final Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            waitForTestar();
            process.waitFor();
            process.destroy();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with trying to run TESTAR: " + e);
            e.printStackTrace();
        }
    }

    public int getCounter() {
        return didTestarRun ? counter : 0;
    }

    public void didNotRun() {
        didTestarRun = false;
    }

    public boolean didItRun() {
        return didTestarRun;
    }

    private int getCounter(int counter) {
        File csvFile = new File(path + "ecj_sequence_" + counter + ".csv");
        while (csvFile.exists()) {
            counter += 1;
            csvFile = new File(path + "ecj_sequence_" + counter + ".csv");
        }
        System.out.println("Counter: " + counter);
        return counter;
    }

    private void waitForTestar() {
        System.out.println("Waiting for Testar");
        int timer = 0;
        try {
            while (!new File(path + "ecj_sequence_" + counter + ".csv").exists()) {
                System.out.print(".");
                timer += 1;
                TimeUnit.SECONDS.sleep(1);
                if (timer >= sequenceLength + 100 * Math.pow(2, nrOfTries)) {
                    didTestarRun = false;
                    return;
                }
            }
            System.out.println(" Ready!");
            didTestarRun = true;
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with waiting.");
            e.printStackTrace();
        }
    }
}
