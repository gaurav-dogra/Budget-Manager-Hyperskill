package budget;

import java.util.*;


public class App {
    private double balance = 0;
    List<Item> purchases = new ArrayList<>();

    private final String menuOptions = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "0) Exit";
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println(menuOptions);
        while (userInput()) {
            System.out.println(menuOptions);
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
            case 0:
                System.out.println("\nBye!");
                return false;
        }
        return true;
    }

    private void addPurchase() {
        System.out.println();
        System.out.println("Enter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        purchases.add(new Item(item, price));
        System.out.println("Purchase was added!");
        System.out.println();
        balance -= price;
    }

    private void showListOfPurchases() {
        System.out.println();
        if(purchases.isEmpty()) {
            System.out.println("Purchase list is empty");
        } else {
            for (Item item : purchases) {
                System.out.println(item);
            }
            System.out.println("Total sum: $" + getSum());
        }
        System.out.println();
    }

    private Double getSum() {
        double sum = 0;
        for (Item item : purchases) {
            sum += item.getPrice();
        }
        return sum;
    }

    private void showBalance() {
        System.out.println();
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println();
    }

    private void addIncome() {
        System.out.println();
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
        System.out.println();
    }
}
