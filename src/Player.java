public class Player {
    private String name;
    private Inventory inventory;
    private double carryWeightCapacity;
    private Inventory storageView;

    public Player(String playerName, double carryCapacity, Inventory sInventory) {
        name = playerName;
        carryWeightCapacity = carryCapacity;
        inventory = sInventory;
    }

    public void setStorageView(Inventory storageInventory) {
        storageView = storageInventory;
    }

    public Inventory getStorageView() {
        return storageView;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getCarryCapacity() {
        return carryWeightCapacity;
    }

    public double getCurrentWeight() {
        double carrying = 0;
        for (ItemInterface item : getInventory().searchItems("")) {
            carrying += item.getWeight();
        }
        return carrying;
    }

    public void store(ItemInterface item, Storage storage) throws ItemNotAvailableException {
        // Do we have the item we are trying to store
        if (!inventory.searchItems("").contains(item)) {
            throw new ItemNotAvailableException(item.getDefinition());
        }
        storage.store(inventory.remove(item));
    }

    public void retrieve(ItemInterface item, Storage storage) throws ItemNotAvailableException, ExceedWeightCapacity {
        // Does the Storage have the item we are trying to retrieve
        if (!storageView.searchItems("").contains(item)) {
            throw new ItemNotAvailableException(item.getDefinition());
        }
        if (getCurrentWeight() + item.getWeight() > getCarryCapacity()) {
            throw new ExceedWeightCapacity(this, item);
        }
        inventory.addOne(storage.retrieve(item));
    }
    
}
