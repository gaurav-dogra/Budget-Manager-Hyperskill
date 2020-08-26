package budget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {
    String file;
    double balance;
    List<String> content = new ArrayList<>();

    public Loader(String file_name) throws IOException {
        this.file = file_name;
        content = Files.readAllLines(Paths.get(file));
        System.out.println("content size = " + content.size());
    }

    public double getBalance() {
        return 0;
    }

    public List<Item> get(ItemType iType) throws IOException {
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        return null;
    }
}
