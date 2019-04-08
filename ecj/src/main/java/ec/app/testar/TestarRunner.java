package ec.app.testar;

import ec.app.testar.io.ResultsReader;
import ec.app.testar.io.StrategyWriter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestarRunner {
    private String path = "output" + File.separator + "metrics" + File.separator;
    private ResultsReader reader = new ResultsReader();
    private int sequenceLength;
    private StrategyWriter writer = new StrategyWriter();
    private int nrOfTries;
    private int counter = 1;
    private boolean didTestarRun = false;
    private Properties properties = Properties.getInstance();

    boolean runWith(final String strategy) {
        writer.writeStrategy(strategy);
        this.sequenceLength = this.properties.getSequenceLength();
        didTestarRun = false;

        nrOfTries = 0;
        while (!didTestarRun && nrOfTries < 5) {
            counter = getCounter(counter);
            run();
            nrOfTries++;
        }
        return nrOfTries < 5;
    }

    private void run() {
        try {
            System.out.print("cmd /c start java -jar " + this.properties.getPathToTestarExecutable() + " -Dsse=desktop_gp_ecj -Dstrategy=strategy.txt -DSequenceLength=" + sequenceLength + " -Dcounter=" + counter);
            final Process process = Runtime.getRuntime().exec("cmd /c start " + this.properties.getPathToTestarExecutable() + " -Dsse=desktop_gp_ecj -Dstrategy=strategy.txt -DSequenceLength=" + sequenceLength + " -Dcounter=" + counter);
            process.waitFor();
            waitForTestar();
            process.waitFor();
            process.destroy();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with trying to run TESTAR: " + e);
            e.printStackTrace();
        }
    }

    public Result getResult() {
        return new Result(reader.getResults(counter));
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
        File csvFile = new File(path + "ecj_sequence" + counter + ".csv");
        while (csvFile.exists()) {
            counter += 1;
            csvFile = new File(path + "ecj_sequence" + counter + ".csv");
        }
        System.out.println("Counter: " + counter);
        return counter;
    }

    private void waitForTestar() {
        File csvFile = new File(path + "ecj_sequence" + counter + ".csv");
        int timer = 0;
        System.out.print("Waiting for Testar");
        try {
            while (!csvFile.exists()) {
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
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with waiting.");
            e.printStackTrace();
        }
    }
}
