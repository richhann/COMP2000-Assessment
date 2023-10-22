public class ItemNotAvailableException extends Exception {

    public ItemNotAvailableException(ItemDefinition itemDef) {
        super(String.format("Item '%s' not in player inventory", itemDef.getName()));
    }
}
