package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchases {

    private final String purchaseTypes = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) Back";

    private final String purchaseTypesWithAllOption = "Choose the type of purchases\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) All\n" +
            "6) Back";

    private final Scanner scanner = new Scanner(System.in);

    private double balance = 0;
    private List<Item> foodPurchases = new ArrayList<>();
    private List<Item> clothesPurchases = new ArrayList<>();
    private List<Item> entertainmentPurchases = new ArrayList<>();
    private List<Item> otherPurchases = new ArrayList<>();

    public List<Item> getFoodPurchases() {
        return foodPurchases;
    }

    public List<Item> getClothesPurchases() {
        return clothesPurchases;
    }

    public List<Item> getEntertainmentPurchases() {
        return entertainmentPurchases;
    }

    public List<Item> getOtherPurchases() {
        return otherPurchases;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setFoodPurchases(List<Item> foodPurchases) {
        this.foodPurchases = foodPurchases;
    }

    public void setClothesPurchases(List<Item> clothesPurchases) {
        this.clothesPurchases = clothesPurchases;
    }

    public void setEntertainmentPurchases(List<Item> entertainmentPurchases) {
        this.entertainmentPurchases = entertainmentPurchases;
    }

    public void setOtherPurchases(List<Item> otherPurchases) {
        this.otherPurchases = otherPurchases;
    }

    public void addAPurchase() {
        System.out.println();
        System.out.println(purchaseTypes);
        while (askPurchaseType()) {
            System.out.println();
            System.out.println(purchaseTypes);

        }
    }

    private boolean askPurchaseType() {
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 5) {
            return false;
        } else {
            updatePurchaseList(input);
            return true;
        }
    }

    private void updatePurchaseList(int input) {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        Item newItem = new Item(item, price);
        scanner.nextLine();
        switch (input) {
            case 1:
                foodPurchases.add(newItem);
                break;
            case 2:
                clothesPurchases.add(newItem);
                break;
            case 3:
                entertainmentPurchases.add(newItem);
                break;
            case 4:
            default:
                otherPurchases.add(newItem);
        }
        System.out.println("Purchase was added!");
        updateBalance(price);
    }

    private void updateBalance(double price) {
        balance -= price;
    }

    public void show() {
        if (foodPurchases.isEmpty() && clothesPurchases.isEmpty() &&
                entertainmentPurchases.isEmpty() && otherPurchases.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
        } else {
            System.out.println();
            System.out.println(purchaseTypesWithAllOption);
            int listOption = scanner.nextInt();
            while (showPurchaseDataFor(listOption)) {
                System.out.println();
                System.out.println(purchaseTypesWithAllOption);
                listOption = scanner.nextInt();
            }
        }
    }

    private boolean showPurchaseDataFor(int listOption) {

        if (listOption == 6) {
            return false;
        }

        switch (listOption) {
            case 1:
                System.out.println("\nFood:");
                printData(foodPurchases);
                break;
            case 2:
                System.out.println("\nClothes");
                printData(clothesPurchases);
                break;
            case 3:
                System.out.println("\nEntertainment:");
                printData(entertainmentPurchases);
                break;
            case 4:
                System.out.println("\nOther:");
                printData(otherPurchases);
                break;
            case 5:
                System.out.println("\nAll:");
                List<Item> combinedList = new ArrayList<>();
                combinedList.addAll(foodPurchases);
                combinedList.addAll(clothesPurchases);
                combinedList.addAll(entertainmentPurchases);
                combinedList.addAll(otherPurchases);
                printData(combinedList);
                break;
            default:
                return false;
        }
        return true;
    }

    private void printData(List<Item> list) {
        double sum = 0;
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            for (Item item : list) {
                System.out.printf("%s $%.2f%n", item.getName(), item.getPrice());
                sum += item.getPrice();
            }
            System.out.printf("Total sum: $%.2f%n", sum);
        }
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n", balance);
    }

    public void addIncome() {
        System.out.println("\nEnter income:");
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
    }
}
