import java.util.ArrayList;
import java.util.Optional;

public class Basket implements BasketInterface {
    ArrayList<ItemInterface> items;
    ArrayList<Integer> quantities;

    public Basket() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public Optional<Integer> itemIndex(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getInventoryTableRow().getColumnOne().equalsIgnoreCase(itemName)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public ArrayList<CartTableRow> getRowData() {
        ArrayList<CartTableRow> data = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            data.add(items.get(i).getCartRow(quantities.get(i) + ""));
        }

        return data;
    }

    @Override
    public void setItemQuantity(String itemName, int qty) {
        Optional<Integer> index = itemIndex(itemName);
        if (index.isPresent()) {
            quantities.set(index.get(), qty);
        }
    }

    public void add(ItemInterface item) {
        Optional<Integer> index = itemIndex(item.getInventoryTableRow().getColumnOne());
        if (index.isPresent()) {
            quantities.set(index.get(), quantities.get(index.get()) + 1);
        } else {
            items.add(item);
            quantities.add(1);
        }
    }

    @Override
    public void remove(String itemName) {
        Optional<Integer> index = itemIndex(itemName);

        if (index.isPresent()) {
            items.remove((int) index.get());
            quantities.remove((int) index.get());
        }
    }

    @Override
    public void processTransaction(Player from, Seller to) {
        processTransactionConsolidated(from, to);
    }

    @Override
    public void processTransaction(Seller from, Player to) {
        processTransactionConsolidated(from, to);
    }

    // Take advantage of the new parent class of Player and Seller
    public void processTransactionConsolidated(Actor from, Actor to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = 0; i < items.size() && !rollback; i++) {
            for (int q = 0; q < quantities.get(i); q++) {
                Optional<ItemInterface> saleItem = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                if (saleItem.isEmpty()) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
                transactionItems.add(saleItem.get());
            }
        }
        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }

}
