package ec.app.testar;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestarRunner {
    String path = "output\\metrics\\";
    ResultsReader reader = new ResultsReader();
    int sequenceLength;
    StrategyWriter writer = new StrategyWriter();
    int nrOfTries;
    private Result result;
    private int counter = 1;
    private boolean didTestarRun = false;

    public boolean runWith(String strategy, int sequenceLength) {
        writer.writeStrategy(strategy);
        this.sequenceLength = sequenceLength;
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
            Process process = Runtime.getRuntime().exec("cmd /c start run.bat -Dheadless=true -DTG=tree-based_strategy -Dstrategy=strategy.txt -DSequenceLength=" + sequenceLength + " -Dcounter=" + counter);
            process.waitFor();
            waitForTestar();
            process.waitFor();
            process.destroy();

        } catch (IOException e) {
            System.out.println("Something went wrong with trying to run TESTAR: " + e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with trying to run TESTAR: " + e);
            e.printStackTrace();
        }

    }

    public Result getResult() {
        result = new Result(reader.getResults(counter));
        return result;
    }

    public int getCounter() {
        if (didTestarRun) {
            return counter;
        } else {
            return 0;
        }
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
