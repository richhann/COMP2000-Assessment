import java.util.Optional;

public abstract class Actor {
    protected String name;
    protected Inventory inventory;

    public abstract void buy(ItemInterface item);

    public abstract Optional<ItemInterface> sell(String itemName);

    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    public Optional<ItemInterface> removeItem(String itemName) {
        return inventory.removeOne(itemName);
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
