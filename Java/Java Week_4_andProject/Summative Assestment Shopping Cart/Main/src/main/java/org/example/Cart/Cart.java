package org.example.Cart;
import org.example.Models.Item;
import java.util.List;

public interface Cart {

    void addItem(Item itemToAdd);
    void removeItem(String productId, int quantityToRemove);
    void clearCart();
    boolean isCartEmpty();
    double getGrandTotal();
    List<Item>getCartItems();

}
