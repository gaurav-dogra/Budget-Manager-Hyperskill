package budget;

import java.util.*;

public class App {

    private double balance = 0;
    private final List<Item> foodPurchases = new ArrayList<>();
    private final List<Item> clothesPurchases = new ArrayList<>();
    private final List<Item> entertainmentPurchases = new ArrayList<>();
    private final List<Item> otherPurchases = new ArrayList<>();

    private final String mainMenu = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "0) Exit";

    private final String purchaseTypes = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) Back";

    private final String purchaseTypesWithAll = "Choose the type of purchase\n" +
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
            System.out.println(mainMenu);
        }
    }

    private boolean userInput() {
        int input = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
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
            case 0:
                System.out.println("\nBye!");
                return false;
        }
        return true;
    }

    private void addIncome() {
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
        System.out.println();
    }

    private void addPurchase() {
        System.out.println(purchaseTypes);
        while (userInputType()) {
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

        switch(input) {
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
        System.out.println();
        balance -= price;
        return true;
    }

    private void showListOfPurchases() {
        if (foodPurchases.isEmpty() && clothesPurchases.isEmpty() &&
        entertainmentPurchases.isEmpty() && otherPurchases.isEmpty()) {
            System.out.println("Purchase list is empty!\n");
        } else {
            System.out.println(purchaseTypesWithAll);
            while (showPurchaseData()) {
                System.out.println(purchaseTypesWithAll);
            }
        }
    }

    private boolean showPurchaseData() {
        int input = scanner.nextInt();
        scanner.nextLine();
        if (input == 6) {
            return false;
        }
        double sum;
        switch (input) {
            case 1:
                System.out.println("Food:");
                sum = printData(foodPurchases);
                break;
            case 2:
                System.out.println("Clothes");
                sum = printData(clothesPurchases);
                break;
            case 3:
                System.out.println("Entertainment:");
                sum = printData(entertainmentPurchases);
                break;
            case 4:
                System.out.println("Other:");
                sum = printData(otherPurchases);
                break;
            case 5:
                sum = printData(foodPurchases);
                sum += printData(clothesPurchases);
                sum += printData(entertainmentPurchases);
                sum += printData(otherPurchases);
                break;
            default:
                return false;
        }
        System.out.println("Total sum: $" + sum);
        return true;
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
        System.out.println();
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println();
    }
}
