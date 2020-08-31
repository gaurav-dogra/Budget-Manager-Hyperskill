package budget;

import java.io.*;
import java.util.List;

public class SaveToFile implements ISave {

    public void save(Purchases purchases, String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(String.valueOf(purchases.getBalance()));
        writer.newLine();

        List<Item> foodPurchaseList = purchases.getFoodPurchases();
        if (!foodPurchaseList.isEmpty()) {
            writer.write("FOOD " + foodPurchaseList.toString());
            writer.newLine();
        }

        List<Item> clothPurchaseList = purchases.getClothesPurchases();
        if (!clothPurchaseList.isEmpty()) {
            writer.write("CLOTHES " + clothPurchaseList.toString());
            writer.newLine();
        }

        List<Item> entertainmentPurchaseList = purchases.getEntertainmentPurchases();
        if (!entertainmentPurchaseList.isEmpty()) {
            writer.write("ENTERTAINMENT " + entertainmentPurchaseList.toString());
            writer.newLine();
        }

        List<Item> otherPurchaseList = purchases.getOtherPurchases();
        if (!otherPurchaseList.isEmpty()) {
            writer.write("OTHER " + otherPurchaseList.toString());
            writer.newLine();
        }
        writer.close();
    }
}
