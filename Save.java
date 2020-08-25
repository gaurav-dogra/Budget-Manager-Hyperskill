package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    private final File file;

    public Save(String file) {
        this.file = new File(file);
    }

    public void save(String purchases) throws IOException {
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file.getName());
        fileWriter.write(purchases);
        fileWriter.close();
    }
}
