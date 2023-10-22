/**
 * DO NOT MODIFY
 * REQUIRED FOR UI
 */
public interface BasketInterface {
    void setItemQuantity(String itemName, int qty); // DO NOT MODIFY
    void remove(String itemName);   // DO NOT MODIFY
    void processTransaction(Player from, Seller to);  // DO NOT MODIFY
    void processTransaction(Seller from, Player to);  // DO NOT MODIFY
}
