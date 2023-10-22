import java.util.Optional;

public class Item implements ItemInterface {

    private String name;
    private String description;
    private double value;
    private Optional<Integer> expiry;

    public Item(String n, String desc, double val, Optional<Integer> exp) {
        name = n;
        description = desc;
        value = val;
        expiry = exp;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow(getName(), getDesc(), getValue() + "", getExpiryString());
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow(getName(), getValue() + "", column3);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public double getValue() {
        // if (expiry.isEmpty() || expiry.get() > 0) {
        //     return value;
        // }
        // return 0;

        return value;
    }

    public Optional<Integer> getExpiry() {
        return expiry;
    }

    public String getExpiryString() {
        if (expiry.isPresent()) {
            if (expiry.get() == 0) {
                return "Expired";
            } else {
                return "Expires in " + expiry.get();
            }
        } else {
            return "Does not expire";
        }
    }
    
}
