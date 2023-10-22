public class Player implements Observer {
    private String name;
    private Inventory inventory;
    private double carryWeightCapacity;
    private Inventory storageView;

    public void craft(Item craftableItem) throws ItemNotAvailableException {
        // Debug: Print the number of components the craftableItem has
        System.out.println("Number of components for " + craftableItem.getName() + ": " + craftableItem.getComponents().size()); // * BUG HERE... craftableItem has no components */... maybe because Mace has not been created... 
    
        // Check if all components are available in the player's inventory
        for (ItemInterface component : craftableItem.getComponents()) {
            if (inventory.searchItems(component.getName()) == null) { //* search */
                System.out.println("Missing component: " + component.getName());
                return;
            }
        }
        
        // Remove components from the player's inventory
        for (ItemInterface component : craftableItem.getComponents()) {
            System.out.println("Attempting to remove component: " + component.getName());
            inventory.remove(component);
            System.out.println("Component removed: " + component.getName());
        }
    
        // Add the crafted item to the player's inventory
        inventory.addOne(craftableItem);
        System.out.println(name + ", Crafted: " + craftableItem.getName());
    }
    public void uncraft(Item craftableItem) throws ItemNotAvailableException {
        if (!inventory.searchItems("").contains(craftableItem)) {
            System.out.println("Item not in inventory: " + craftableItem.getName());
            return;
        }

        // Remove the craftable item from the player's inventory
        inventory.remove(craftableItem);

        // Add the components of the craftable item back to the player's inventory
        for (ItemInterface component : craftableItem.getComponents()) {
            inventory.addOne(component);
        }
        System.out.println(name + ", Uncrafted: " + craftableItem.getName());
    }

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

    @Override
    public void update(String message) { // ** */
        System.out.println(name + ", " + message);
    }

}
