package budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Analyze {

    private final String sortTypes = "How do you want to sort?\n" +
            "1) Sort all purchases\n" +
            "2) Sort by type\n" +
            "3) Sort certain type\n" +
            "4) Back";

    private final String purchaseTypes = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other";

    private final Scanner scanner = new Scanner(System.in);
    List<Item> foodPurchaseList;
    List<Item> clothesPurchaseList;
    List<Item> entertainmentPurchaseList;
    List<Item> otherPurchaseList;

    public void execute(Purchases purchaseLists) {
        foodPurchaseList = purchaseLists.getFoodPurchases();
        clothesPurchaseList = purchaseLists.getClothesPurchases();
        entertainmentPurchaseList = purchaseLists.getEntertainmentPurchases();
        otherPurchaseList = purchaseLists.getOtherPurchases();
        while (true) {
            System.out.println();
            System.out.println(sortTypes);
            int sortOption = scanner.nextInt();
            if (sortOption == 4) {
                break;
            }
            process(sortOption);
        }
    }

    private void process(int sortOption) {
        switch (sortOption) {
            case 1:
                sortAll();
                break;
            case 2:
                sortByType();
                break;
            case 3:
                sortCertainType();
                break;
            default:
                break;
        }
    }

    private void sortAll() {
        if (foodPurchaseList.isEmpty() && clothesPurchaseList.isEmpty() &&
                entertainmentPurchaseList.isEmpty() && otherPurchaseList.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
        } else {
            List<Item> combinedList = combineAllLists();
            Collections.sort(combinedList);
            printList(combinedList, " ", "All");
            //System.out.printf("Total: $%.2f\n", getSum(combinedList));
        }
    }

    private List<Item> combineAllLists() {
        List<Item> combinedList = new ArrayList<>();
        combinedList.addAll(foodPurchaseList);
        combinedList.addAll(clothesPurchaseList);
        combinedList.addAll(entertainmentPurchaseList);
        combinedList.addAll(otherPurchaseList);
        return combinedList;
    }

    private void printList(List<Item> list, String delimeter, String type) {
        if (list.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
            return;
        }
        System.out.println();
        System.out.println(type + ":");
        for (Item item : list) {
            System.out.printf("%s%s$%.2f%n", item.getName(), delimeter ,item.getPrice());
        }
    }

    private void sortByType() {
        double sumFood = getSum(foodPurchaseList);
        double sumEntertainment = getSum(entertainmentPurchaseList);
        double sumClothes = getSum(clothesPurchaseList);
        double sumOther = getSum(otherPurchaseList);
        List<Item> list = new ArrayList<>();
        list.add(new Item("Food", sumFood));
        list.add(new Item("Entertainment", sumEntertainment));
        list.add(new Item("Clothes", sumClothes));
        list.add(new Item("Other", sumOther));
        Collections.sort(list);
        printList(list, " - ", "Types");
        System.out.printf("Total sum: $%.2f\n", getSum(list));
    }

    private double getSum(List<Item> list) {
        double sum = 0;
        for (Item item : list) {
            sum += item.getPrice();
        }
        return sum;
    }

    private void sortCertainType() {
        System.out.println();
        System.out.println(purchaseTypes);
        int input = scanner.nextInt();
        show(input);
    }

    private void show(int input) {
        switch (input) {
            case 1:
                Collections.sort(foodPurchaseList);
                printList(foodPurchaseList, " ", "Food");
                printSum(foodPurchaseList);
                break;
            case 2:
                Collections.sort(clothesPurchaseList);
                printList(clothesPurchaseList, " ", "Clothes");
                printSum(clothesPurchaseList);
                break;
            case 3:
                Collections.sort(entertainmentPurchaseList);
                printList(entertainmentPurchaseList, " ", "Entertainment");
                printSum(entertainmentPurchaseList);
                break;
            case 4:
                Collections.sort(otherPurchaseList);
                printList(otherPurchaseList, " ", "Other");
                printSum(otherPurchaseList);
                break;
        }
    }

    private void printSum(List<Item> list) {
        double sum = getSum(list);
        if (sum > 0) {
            System.out.printf("Total sum: $%.2f%n", getSum(foodPurchaseList));
        }
    }
}
