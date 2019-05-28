package ec.app.testar.io;

import ec.app.testar.Metric;
import ec.app.testar.Properties;
import ec.app.testar.Strategy;
import ec.app.testar.TestarRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultWriter {
    private File file;

    public ResultWriter() {
        int i = 0;
        while (file == null || file.exists()) {
            i++;
            Properties properties = Properties.getInstance();
            file = new File(properties.getPathToMetricsDir() + File.separator + "testresult" + i + ".csv");
        }
        try {
            file.createNewFile();
            PrintWriter out = new PrintWriter(new FileWriter(file));
            String header = "Subpopulation,Counter,Strategy,Short strategy,Simplified strategy,";
            for (String metric : Metric.headers)
                header += metric + ",";
            header += "fitness,timestamp";
            out.println(header);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in creating the results file.");
            e.printStackTrace();
        }

    }

    public void writeResult(int generation, TestarRunner testar, Strategy strategy, Result result) {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            String resultline = generation + "," + testar.getCounter() + "," + strategy.getOriginal() + "," + strategy.getShortOriginal() + "," + strategy.getShortSimple() + ",";
            resultline += result.toString(testar.didItRun());
            resultline += "," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            out.println(resultline);
            out.close();
        } catch (IOException e) {
            System.out.println("Something went wrong in writing the results.");
            e.printStackTrace();
        }


    }
}
