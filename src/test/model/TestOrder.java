package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test Order class
public class TestOrder {

    private Order od1;
    private Order od2;
    private Item itm1;
    private Item itm2;
    private Item itm0;

    @BeforeEach
    void runBefore() {
        od1 = new Order();
        od2 = new Order();
        itm1 = new Item("item1", 10.0, 20);
        itm2 = new Item("item2", 20.0, 30);
        itm0 = new Item("item0", 0.11, 40);
    }

    @Test
    void testConstructor() {
        ArrayList<Item> empty = new ArrayList<>();
        assertEquals(false, od2.getOrderID() == od1.getOrderID());
        // We only need to test that the orderIDs of different orders are different.
        assertEquals(empty, od1.getOrderedLists());
        assertEquals(false, od1.getOrderStatus());
    }

    @Test
    void testAddItem() {
        od1.addItem(itm1, 2);
        assertEquals("item1", od1.getOrderedLists().get(0).getName());
        assertEquals(2, od1.getOrderedLists().get(0).getOrderAmount());
        assertEquals(20 - 2, od1.getOrderedLists().get(0).getStockAmount());
        assertEquals(2, itm1.getOrderAmount());
        assertEquals(20 - 2, itm1.getStockAmount());
        assertEquals(1, od1.getOrderedLists().size());
        od1.addItem(itm2, 3);
        assertEquals("item2", od1.getOrderedLists().get(1).getName());
        assertEquals(3, od1.getOrderedLists().get(1).getOrderAmount());
        assertEquals(30 - 3, od1.getOrderedLists().get(1).getStockAmount());
        assertEquals(2, od1.getOrderedLists().size());
    }

    @Test
    void testRemoveItem() {
        od1.addItem(itm1, 2);
        od1.addItem(itm2, 3);
        assertEquals(2, od1.getOrderedLists().size());
        od1.removeItem("item1");
        assertEquals("item2", od1.getOrderedLists().get(0).getName());
        assertEquals(1, od1.getOrderedLists().size());
        assertEquals(0, itm1.getOrderAmount());
        assertEquals(20, itm1.getStockAmount());
        od1.removeItem("item3");
        assertEquals("item2", od1.getOrderedLists().get(0).getName());
        assertEquals(1, od1.getOrderedLists().size());
        assertEquals(0, itm1.getOrderAmount());
        assertEquals(20, itm1.getStockAmount());
    }

    @Test
    void testGetTotalPrice() {
        od1.addItem(itm1, 2);
        assertEquals(10.0 * 2, od1.getTotalPrice());
        od1.addItem(itm2, 3);
        assertEquals(10.0 * 2 + 20.0 * 3, od1.getTotalPrice());
        od1.addItem(itm0, 3);
        assertEquals(10.0 * 2 + 20.0 * 3 + 0.11 * 3, od1.getTotalPrice());
    }

    @Test
    void testPrintReceipt() {
        od1.addItem(itm1, 2);
        od1.addItem(itm2, 3);
        assertEquals("Order ID: " + Integer.toString(od1.getOrderID())
                + "\nitem1 $10.0 Amount: 2\nitem2 $20.0 Amount: 3\n Total: $80.0",
                od1.printReceipt());
    }

    @Test
    void testCompleteOrder() {
        assertEquals(false, od1.getOrderStatus());
        od1.completeOrder();
        assertEquals(true, od1.getOrderStatus());
    }

    @Test
    void testFindItemInOrder() {
        od1.addItem(itm1, 2);
        od1.addItem(itm2, 3);
        assertEquals(itm1, od1.findItemInOrder("item1"));
        assertEquals(itm2, od1.findItemInOrder("item2"));
        assertEquals(null, od1.findItemInOrder("item3"));
    }

}
