package budget;

import java.io.IOException;
import java.util.*;

public class App {

    private double balance = 0;
    private List<Item> foodPurchases = new ArrayList<>();
    private List<Item> clothesPurchases = new ArrayList<>();
    private List<Item> entertainmentPurchases = new ArrayList<>();
    private List<Item> otherPurchases = new ArrayList<>();

    private final String FILE_NAME = "purchases.txt";

    private final String mainMenu = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "5) Save\n" +
            "6) Load\n" +
            "0) Exit";

    private final String purchaseTypes = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) Back";

    private final String purchaseTypesWithAll = "Choose the type of purchases\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) All\n" +
            "6) Back";

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println(mainMenu);
        while (userInput()) {
            System.out.println();
            System.out.println(mainMenu);
        }
    }

    private boolean userInput() {
        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                showListOfPurchases();
                break;
            case 4:
                showBalance();
                break;
            case 5:
                save();
                break;
            case 6:
                loadData();
                break;
            case 0:
                System.out.println("\nBye!");
                return false;
        }
        return true;
    }

    private void loadData() {
        Loader loader;
        try {
            loader = new Loader(FILE_NAME);
            this.balance = loader.getBalance();
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + FILE_NAME);
            e.printStackTrace();
            return;
        }

        this.foodPurchases = loader.get(ItemType.FOOD);
        this.clothesPurchases = loader.get(ItemType.CLOTHES);
        this.entertainmentPurchases = loader.get(ItemType.ENTERTAINMENT);
        this.otherPurchases = loader.get(ItemType.OTHER);
    }

    private void save() {

        Save data = new Save(FILE_NAME);
        StringBuilder currentData = new StringBuilder();

        currentData.append(balance).append("\r\n");
        currentData.append(saveToFile(foodPurchases, "FOOD")).append("\r\n");
        currentData.append(saveToFile(clothesPurchases, "CLOTHES")).append("\r\n");
        currentData.append(saveToFile(entertainmentPurchases, "ENTERTAINMENT")).append("\r\n");
        currentData.append(saveToFile(otherPurchases, "OTHER")).append("\r\n");

        try {
            data.save(currentData.toString());
        } catch (IOException e) {
            System.out.println("Error saving in the file");
            e.printStackTrace();
            return;
        }
        System.out.println("\nPurchases were saved!");
    }

    private String saveToFile(List<Item> purchaseList, String type) {
        if (purchaseList.isEmpty()) {
            return "";
        }

        StringBuilder returnString = new StringBuilder(type);
        returnString.append(" \n\r");
        for (Item item : purchaseList) {
            returnString.append(item.getName()).append(":").append(item.getPrice()).append(", ");
        }

        return returnString.toString().substring(0, returnString.length() - 2);
    }

    private void addIncome() {
        System.out.println("\nEnter income:");
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
    }

    private void addPurchase() {
        System.out.println();
        System.out.println(purchaseTypes);
        while (userInputType()) {
            System.out.println();
            System.out.println(purchaseTypes);
        }
    }

    private boolean userInputType() {
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 5) {
            return false;
        }

        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();

        switch (input) {
            case 1:
                foodPurchases.add(new Item(item, price));
                break;
            case 2:
                clothesPurchases.add(new Item(item, price));
                break;
            case 3:
                entertainmentPurchases.add(new Item(item, price));
                break;
            case 4:
            default:
                otherPurchases.add(new Item(item, price));
        }
        System.out.println("Purchase was added!");
        balance -= price;
        return true;
    }

    private void showListOfPurchases() {
        if (foodPurchases.isEmpty() && clothesPurchases.isEmpty() &&
                entertainmentPurchases.isEmpty() && otherPurchases.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
        } else {
            System.out.println();
            System.out.println(purchaseTypesWithAll);
            int input = scanner.nextInt();
            while (showPurchaseData(input)) {
                System.out.println();
                System.out.println(purchaseTypesWithAll);
            }
        }
    }

    private boolean showPurchaseData(int input) {

        if (input == 6) {
            return false;
        }
        double sum;
        switch (input) {
            case 1:
                System.out.println("\nFood:");
                if (!checkIfEmpty(foodPurchases)) {
                    sum = printData(foodPurchases);
                    System.out.println("Total sum: $" + sum);
                }
                break;
            case 2:
                System.out.println("\nClothes");
                if (!checkIfEmpty(clothesPurchases)) {
                    sum = printData(clothesPurchases);
                    System.out.println("Total sum: $" + sum);
                }
                break;
            case 3:
                System.out.println("\nEntertainment:");
                if (!checkIfEmpty(entertainmentPurchases)) {
                    sum = printData(entertainmentPurchases);
                    System.out.println("Total sum: $" + sum);
                }
                break;
            case 4:
                System.out.println("\nOther:");
                if (!checkIfEmpty(otherPurchases)) {
                    sum = printData(otherPurchases);
                    System.out.println("Total sum: $" + sum);
                }
                break;
            case 5:
                System.out.println("\nAll:");
                sum = printData(foodPurchases);
                sum += printData(clothesPurchases);
                sum += printData(entertainmentPurchases);
                sum += printData(otherPurchases);
                System.out.println("Total sum: $" + sum);
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean checkIfEmpty(List<Item> purchasesList) {
        if (purchasesList.isEmpty()) {
            System.out.println("Purchase list is empty!");
            return true;
        }
        return false;
    }

    private double printData(List<Item> list) {
        double sum = 0;
        for (Item item : list) {
            System.out.println(item);
            sum += item.getPrice();
        }
        return sum;
    }

    private void showBalance() {
        System.out.printf("\nBalance: $%.2f\n", balance);
    }
}
