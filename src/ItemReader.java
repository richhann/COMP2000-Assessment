import java.util.Optional;

public class ItemReader {
	/**
	 * itemData format: {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}
	 * index:                0             1           2               3
	 *
	 * Each field is extracted for you to use.
	 *
	 * This method creates all of the starting items (for the player and store).
	 * @param itemData
	 * @return The read in Item
	 */
    public static ItemInterface readStartingItem(String[] itemData) {
        String name = itemData[0].trim();                      // DO NOT MODIFY
        String description = itemData[1].trim();               // DO NOT MODIFY
        double value = Double.valueOf(itemData[2].trim());     // DO NOT MODIFY
        String expiry = itemData[3].trim();                    // DO NOT MODIFY
        // NOTE: expiry will be an empty String if the item does not expire
        
        // You may modify the below
        
        if (expiry.isEmpty()) {
            return new Item(name, description, value, Optional.empty());
        } else {
            return new Item(name, description, value, Optional.of(Integer.valueOf(expiry)));
        }
    }
}
