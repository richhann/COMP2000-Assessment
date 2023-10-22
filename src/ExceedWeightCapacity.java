public class ExceedWeightCapacity extends Exception {
    public ExceedWeightCapacity(String errorMessage) {
        super(errorMessage);
    }

    public ExceedWeightCapacity(Player p, ItemInterface newItem) {
        super(String.format(
            "Player %s cannot retrieve the item %s due to exceeding the carry weight limit",
            p.getName(), newItem.getName()
        ));
    }
}
