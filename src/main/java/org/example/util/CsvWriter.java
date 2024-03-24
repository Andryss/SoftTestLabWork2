package org.example.util;

import org.example.Calculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public void testAndSave(Calculator module, double start, double stop, double step, double precision, File output) throws IOException {
        if (!output.exists() || !output.isFile() || !output.canWrite()) {
            throw new IllegalArgumentException("result must be existing writable file");
        }
        try (FileWriter writer = new FileWriter(output)) {
            writer.write(String.format("x,\"calc(x,%f)\"\n", precision));
            double cursor = start;
            do {
                double result = module.calc(cursor, precision);
                writer.write(String.format("%f,%f\n", cursor, result));
            } while ((cursor += step) < stop);
        }
    }

}
