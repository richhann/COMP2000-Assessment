public class Storage {
    private String storageName;
    private Inventory items;

    public Storage(String name, Inventory startingInventory) {
        storageName = name;
        items = startingInventory;
    }

    public Inventory getInventory() {
        return items;
    }

    public String getName() {
        return storageName;
    }
    
    public void store(ItemInterface item) {
        items.addOne(item);
    }

    public ItemInterface retrieve(ItemInterface item) throws ItemNotAvailableException {
        ItemInterface removed = items.remove(item);
        return removed;
    }
    
}
