import java.util.Optional;

public abstract class Trader {
    protected String name;
    protected Inventory inventory;

    public Trader(String name, Inventory startingInventory) {
        this.name = name;
        this.inventory = startingInventory;
    }

    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    public Optional<ItemInterface> removeItem(String itemName) {
        return inventory.removeOne(itemName);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public abstract void buy(ItemInterface item);
    public abstract Optional<ItemInterface> sell(String itemName);
}
