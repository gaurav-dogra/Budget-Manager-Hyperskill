package budget;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFromFile implements ILoadFromFile {

    public void loadData(Purchases purchaseLists, String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        purchaseLists.setBalance(Double.parseDouble(scanner.nextLine()));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("FOOD")) {
                line = extractData(line, "FOOD");
                purchaseLists.setFoodPurchases(generateListFromData(line));
            } else if (line.startsWith("CLOTHES")) {
                line = extractData(line, "CLOTHES");
                purchaseLists.setClothesPurchases(generateListFromData(line));
            } else if (line.startsWith("ENTERTAINMENT")) {
                line = extractData(line, "ENTERTAINMENT");
                purchaseLists.setEntertainmentPurchases(generateListFromData(line));
            } else if (line.startsWith("OTHER")) {
                line = extractData(line, "OTHER");
                purchaseLists.setOtherPurchases(generateListFromData(line));
            }
        }
    }

    private String extractData(String line, String type) {
        line = line.replaceAll(type, "");
        return line.replaceAll("[\\[\\]]", "").trim();
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
}
