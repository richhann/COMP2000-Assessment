import java.util.ArrayList;
import java.util.List;

public class Storage implements Observable {
    private String storageName;
    private Inventory items;
    private List<Observer> observers = new ArrayList<>();

    private String lastAction = ""; // To store the last action performed on the storage


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
    
  @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(lastAction);
        }
    }

    public void store(ItemInterface item) {    
        items.addOne(item);
        lastAction = "Added: " + item.getName();   //update last action string
        notifyObservers();
    }

    public ItemInterface retrieve(ItemInterface item) throws ItemNotAvailableException {
        ItemInterface removed = items.remove(item);
        lastAction = "Retrieved: " + item.getName();
        notifyObservers();
        return removed;
    }

    public String getLastAction() {
        return lastAction;
    }
}