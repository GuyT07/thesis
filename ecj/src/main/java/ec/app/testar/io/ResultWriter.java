package ec.app.testar.io;

import ec.app.testar.Metric;
import ec.app.testar.Properties;
import ec.app.testar.Strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultWriter {
    private static final Properties properties = Properties.getInstance();
    private File file;

    public ResultWriter() {
        int i = 0;
        while (file == null || file.exists()) {
            i++;
            file = new File(properties.getPathToMetricsDir() + File.separator + "testresult" + i + ".csv");
        }
        try {
            if (!file.createNewFile()) {
                throw new RuntimeException("File already exists");
            }
            PrintWriter out = new PrintWriter(new FileWriter(file));
            StringBuilder header = new StringBuilder("Subpopulation,Counter,Strategy,Short strategy,Simplified strategy,");
            for (String metric : Metric.headers)
                header.append(metric).append(",");
            header.append("fitness, fitness median, fitnes mean, timestamp");
            out.println(header);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in creating the results file.");
            e.printStackTrace();
        }

    }

    public void writeResult(final String resultString, final int generation, final int counter, final Strategy strategy) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            String resultline = generation + "," + counter + "," + strategy.getOriginal() + "," + strategy.getShortOriginal() + "," + strategy.getShortSimple() + ",";
            resultline += resultString;
            resultline += "," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            out.println(resultline);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in writing the results.");
            e.printStackTrace();
        }


    }
}
