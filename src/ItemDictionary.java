import java.util.ArrayList;
import java.util.Optional;

/**
 * Provided Singleton to show students a working example of it, and to prevent
 * its use as a graded pattern implementation.
 * 
 * Acts as a database of item definitions.
 */
public class ItemDictionary {
    private static final ItemDictionary INSTANCE = new ItemDictionary();

    private ArrayList<ItemDefinition> defs;

    private ItemDictionary() {
        defs = new ArrayList<>();
    }

    public static ItemDictionary get() {
        return INSTANCE;
    }

    public ArrayList<ItemDefinition> getDefs() {
        return defs;
    }

    /**
     * Search for an ItemDefinition specified by the `itemName` parameter.
     * Returns an Optional container with the matching ItemDefinition if the
     * provided `itemName` is valid.
     * Returns an Optional empty if no definition is found.
     * @param name
     * @return Optional result.
     */
    public Optional<ItemDefinition> defByName(String itemName) {
        for (ItemDefinition def : getDefs()) {
            if (def.getName().equalsIgnoreCase(itemName)) {
                return Optional.of(def);
            }
        }
        return Optional.empty();
    }

}
