package budget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Loader {
    private final File file;
    private final List<String> content;

    public Loader(String file_name) throws IOException {
        file = new File(file_name);
        content = Files.readAllLines(file.toPath());
    }

    public double getBalance() {
        return Double.parseDouble(content.get(0));
    }

    public List<Item> get(ItemType iType) {
        String listType = iType.toString();
        List<Item> returnList = new ArrayList<>();
        for (String line : content) {
            if (line.startsWith(listType)) {
                line = line.replace(listType, "");
                line = line.replaceAll("[\\[\\]$]", "");
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    String  values = stringTokenizer.nextToken().trim();
                    String item = values.split(" : ")[0];
                    double price = Double.parseDouble(values.split(" : ")[1]);
                    returnList.add(new Item(item, price));
                }
            }
        }
        return returnList;
    }
}
