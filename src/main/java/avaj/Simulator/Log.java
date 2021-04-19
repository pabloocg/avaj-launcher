package avaj.Simulator;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Log {
    private static Log log = null;
    private final static String filename = "simulation.txt";
    private File file = new File(filename);

    private Log() {
        try {
            if (file.exists() == false)
                file.createNewFile();
            new FileWriter(file).close(); // clear the file for previous data
        } catch (IOException e) {
            System.out.println("simulation.txt file cannot be created.");
            System.exit(1);
        }
    }

    public static Log getLog() {
        if (log == null) {
            log = new Log();
        }
        return log;
    }

    public void write(String line) {
        try (FileWriter writer = new FileWriter(file, true);
                BufferedWriter bwriter = new BufferedWriter(writer);
                PrintWriter pWriter = new PrintWriter(bwriter)) {
            pWriter.println(line);
            pWriter.close();
        } catch (IOException e) {
            System.out.println("simulation.txt file cannot be writted.");
            System.exit(1);
        }
    }
}
