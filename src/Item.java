import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Item implements ItemInterface {
    private ItemDefinition definition;
    private List<ItemInterface> components; // * */

    public void addComponent(ItemInterface component) {
        components.add(component);
    }

    public List<ItemInterface> getComponents() {
        return components;
    }

    /**
     * Creates an Item instance with a set definition.
     * The composition list is (created but) left empty. For composite items, the
     * sub-components
     * should be retrieved/removed from some item source, and added with
     * Item::Add(ItemInterface).
     * 
     * @param def
     */
    public Item(ItemDefinition def) {
        this.definition = def;
        this.components = new ArrayList<>();

      // Check if the item is craftable
    if (!def.isBaseItemDef()) {
        // Retrieve component items from ItemDictionary or wherever you store your items
        String[] componentNames = def.componentsString().split(", ");
        for (String componentName : componentNames) {
            Optional<ItemDefinition> componentDef = ItemDictionary.get().defByName(componentName);
            if (componentDef.isPresent()) {
                ItemInterface componentItem = new Item(componentDef.get());
                addComponent(componentItem);
            }
        }
    }
} 

    @Override
    public double getWeight() {
        double weight = definition.getWeight().orElse(0.0);
        // If the item is made up of other items, we should find the sum of weights
        for (ItemInterface component : components) {
            weight += component.getWeight(); // * array LOOP */
        }

        return weight;
    }

    @Override
    public String getName() {
        return definition.getName();
    }

    @Override
    public String getDescription() {
        return definition.getDescription();
    }

    @Override
    public ItemDefinition getDefinition() {
        return definition;
    }

    @Override
    public String getCompositionDescription() {
        // For craftable items, this method should return a description
        // describing/listing the
        // other items which make up this item.
        // When a non-empty String is returned, the uncraft button will appear in the
        // UI.

        StringBuilder description = new StringBuilder();
        for (ItemInterface component : components) {
            if (description.length() > 0) {
                description.append(", ");
            }
            description.append(component.getName());
        }
        return description.toString();
    }

    @Override
    public boolean equals(ItemInterface other) {
        return isOf(other.getDefinition());
    }

    @Override
    public boolean isOf(ItemDefinition def) {
        return getName().equals(def.getName());
    }

    @Override
    public String toString() {
        String output = String.format("Item: %s\nDescription: %s\nWeight: %.2f",
                getName(), getDescription(), getWeight());
        output += "\nHashCode: " + Integer.toHexString(this.hashCode());
        return output;
    }

}