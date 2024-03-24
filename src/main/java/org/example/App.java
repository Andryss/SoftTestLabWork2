package org.example;

import org.example.log.LnCalc;
import org.example.log.LogCalc;
import org.example.system.Equation;
import org.example.trig.CosCalc;
import org.example.trig.SecCalc;
import org.example.trig.SinCalc;
import org.example.util.CsvWriter;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        SinCalc sinCalc = new SinCalc();
        CosCalc cosCalc = new CosCalc(sinCalc);
        SecCalc secCalc = new SecCalc(cosCalc);

        LnCalc lnCalc = new LnCalc();
        LogCalc logCalc = new LogCalc(lnCalc);

        Equation equation = new Equation(secCalc, logCalc, lnCalc);

        CsvWriter csvWriter = new CsvWriter();

        File outputDir = new File("output");
        if (!outputDir.exists() && !outputDir.mkdir()) {
            throw new IOException("Cannot create output directory");
        }
        if (!outputDir.isDirectory() || !outputDir.canWrite()) {
            throw new IOException("output must be writable directory");
        }

        tryCreateFileAndTest("output/sin.csv", csvWriter, sinCalc::sin);
        tryCreateFileAndTest("output/cos.csv", csvWriter, cosCalc::cos);
        tryCreateFileAndTest("output/sec.csv", csvWriter, secCalc::sec);

        tryCreateFileAndTest("output/ln.csv", csvWriter, lnCalc::ln);
        tryCreateFileAndTest("output/log3.csv", csvWriter, logCalc::log3);
        tryCreateFileAndTest("output/lg.csv", csvWriter, logCalc::lg);

        tryCreateFileAndTest("output/equation.csv", csvWriter, equation::calc);
    }

    private static void tryCreateFileAndTest(String filename, CsvWriter writer, Calculator module) throws IOException {
        File sinOut = new File(filename);
        if (!sinOut.exists() && !sinOut.createNewFile()) {
            throw new IOException(String.format("Cannot create %s file", filename));
        }
        writer.testAndSave(module, -5, 5, 0.1, 1e-6, sinOut);
    }

}
