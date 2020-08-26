package budget;

import java.io.*;
import java.util.List;

public class Save {

    private final File file;

    public Save(String file) {
        this.file = new File(file);
    }

    public void save(List<String> textToFile) throws IOException {
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String line: textToFile) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
