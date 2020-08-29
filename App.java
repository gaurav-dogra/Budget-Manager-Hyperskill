package budget;

import java.io.IOException;
import java.util.*;

public class App {

    private final Purchases purchaseLists = new Purchases();

    private final String FILE_NAME = "purchases.txt";

    private final String mainMenu = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "5) Save\n" +
            "6) Load\n" +
            "0) Exit";

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
                showPurchases();
                break;
            case 4:
                showBalance();
                break;
            case 5:
                saveData();
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

    private void addIncome() {
        purchaseLists.addIncome();
    }

    private void addPurchase() {
        purchaseLists.addAPurchase();
    }

    private void showPurchases() {
        purchaseLists.show();
    }

    private void showBalance() {
        purchaseLists.showBalance();
    }

    private void saveData() {

        try {
            ISave iSave = new SaveToFile();
            iSave.save(purchaseLists, FILE_NAME);
            System.out.println("\nPurchases were saved!");
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + FILE_NAME);
            e.printStackTrace();
        }

    }

    private void loadData() {
        try {
            ILoadFromFile iLoad = new LoadFromFile();
            iLoad.loadData(purchaseLists, FILE_NAME);
            System.out.println("\nPurchases were loaded!");
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + FILE_NAME);
            e.printStackTrace();
        }
    }

}
