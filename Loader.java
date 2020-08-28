package budget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Loader {
    String file;
    List<String> content;

    public Loader(String file_name) throws IOException {
        this.file = file_name;
        content = Files.readAllLines(Paths.get(file));
    }

    public double getBalance() {
        return Double.parseDouble(content.get(0));
    }

    public List<Item> get(ItemType iType) throws IOException {
        Scanner scanner = new Scanner(new File(file));
        List<Item> returnList = Collections.emptyList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(iType.toString())) {
                line = extractData(line, iType.toString());
                returnList = generateListFromData(line);
            }
        }
        return returnList;
    }

    private List<Item> generateListFromData(String line) {
        String[] purchases = line.split(", ");
        List<Item> list = new ArrayList<>();
        for (String item : purchases) {
            String itemName = item.split("=")[0];
            double price = Double.parseDouble(item.split("=")[1]);
            list.add(new Item(itemName, price));
        }
        return list;
    }

    private String extractData(String line, String type) {
        line = line.replaceAll(type, "");
        return line.replaceAll("[\\[\\]]", "").trim();
    }
}
