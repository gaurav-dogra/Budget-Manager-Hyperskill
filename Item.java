package budget;

import org.jetbrains.annotations.NotNull;

public class Item implements Comparable<Item> {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "=" + price;
    }

    @Override
    public int compareTo(@NotNull Item item){
        return Double.compare(item.getPrice(), this.getPrice());
    }
}
