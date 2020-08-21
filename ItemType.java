package budget;

public enum ItemType {
    FOOD(1), CLOTHES(2), ENTERTAINMENT(3), OTHER(4);

    private final int value;

    private ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
