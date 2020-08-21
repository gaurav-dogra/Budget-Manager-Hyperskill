package budget;


public class BudgetManagerException extends RuntimeException {

    public BudgetManagerException() {
        super("Error: Wrong input");
    }

    public BudgetManagerException(String message) {
        super(message);
    }

}
