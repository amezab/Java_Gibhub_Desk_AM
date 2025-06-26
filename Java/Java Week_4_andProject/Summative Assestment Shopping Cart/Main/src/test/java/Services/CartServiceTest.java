package Services;

import org.example.Models.Item;
import org.example.Services.CartService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartServiceTest {


    @Test
    public void addingExistingItem_shouldIncreaseQuantityAndCorrectTotal() {
        CartService cartService = new CartService();
        Item item = new Item("p101","Banana",1,0.50);
        Item item2 = new Item("p101","Banana",1,0.50);

        cartService.addItem(item);
        cartService.addItem(item2);
        assertEquals(1,cartService.getGrandTotal());
    }

    @Test
    void removingPartialQuantity_shouldDecreaseQuantityAndCorrectTotal() {
        CartService cartService = new CartService();
        Item tacos = new Item("a123","Street Tacos", 5, 1);

        cartService.addItem(tacos);

        cartService.removeItem("a123", 2);
        assertEquals(3,cartService.getGrandTotal());
    }

    @Test
    void removingEntireItem_shouldResultInZeroTotal() {
        CartService cartService = new CartService();
        Item tacos = new Item("a123","Street Tacos", 10, 1);

        cartService.addItem(tacos);
        cartService.removeItem("a123", 11);

        assertEquals(0,cartService.getGrandTotal());
    }

    @Test
    void getGrandTotal_onNewEmptyCart_shouldReturnZero() {
        CartService cartService = new CartService();

        double grandTotalresult = cartService.getGrandTotal();
        assertEquals(0.0,grandTotalresult);
    }

    @Test
    void clearCart_shouldResultInZeroTotal() {
        CartService cartService = new CartService();
        Item tacos = new Item("a123","Street Tacos", 5, 1);

        cartService.addItem(tacos);
        cartService.clearCart();

        assertEquals(0.0,cartService.getGrandTotal());


    }
}
