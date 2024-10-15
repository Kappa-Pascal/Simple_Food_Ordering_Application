package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test OrderList class
public class TestOrderList {

    private OrderList ol1;
    private Order od1;
    private Order od2;
    private Item itm1;
    private Item itm2;

    @BeforeEach
    void runBefore() {
        od1 = new Order();
        od2 = new Order();
        itm1 = new Item("item1", 10.0, 20);
        itm2 = new Item("item2", 20.0, 30);
        ol1 = new OrderList();
    }

    @Test
    void testConstructor() {
        ArrayList<Order> empty = new ArrayList<>();
        assertEquals(empty, ol1.getOrderList());
    }

    @Test
    void testaddToOrderList() {
        od1.addItem(itm1, 2);
        od1.addItem(itm2, 3);
        od1.completeOrder();
        ol1.addToOrderList(od1);
        assertEquals(od1, ol1.getOrderList().get(0));
        assertEquals(1, ol1.getOrderList().size());
        od2.addItem(itm1, 4);
        od2.completeOrder();
        ol1.addToOrderList(od2);
        assertEquals(od2, ol1.getOrderList().get(1));
        assertEquals(2, ol1.getOrderList().size());
    }

    @Test
    void testDisplayOrderList() {
        od1.addItem(itm1, 2);
        od1.addItem(itm2, 3);
        od1.completeOrder();
        ol1.addToOrderList(od1);
        od2.addItem(itm1, 4);
        od2.completeOrder();
        ol1.addToOrderList(od2);
        assertEquals("\n--- Order #1 ---\n" + od1.printReceipt()
                + "\n--- Order #2 ---\n" + od2.printReceipt()
                + "\n--- End ---\n", ol1.displayOrderList());
    }

    @Test
    void testRemoveOrder() {
        od1.addItem(itm1, 2);
        od1.completeOrder();
        ol1.addToOrderList(od1);
        od2.addItem(itm1, 4);
        od2.completeOrder();
        ol1.addToOrderList(od2);
        assertEquals(2,ol1.getOrderList().size());
        ol1.removeOrder(od1.getOrderID());
        assertEquals(1,ol1.getOrderList().size());
        assertEquals(od2, ol1.getOrderList().get(0));
        ol1.removeOrder(-1);
        assertEquals(1,ol1.getOrderList().size());
        assertEquals(od2, ol1.getOrderList().get(0));
    }

    @Test
    void testIsEmpty() {
        assertEquals(true,ol1.isEmpty());
        od1.addItem(itm1, 2);
        od1.completeOrder();
        ol1.addToOrderList(od1);
        assertEquals(false, ol1.isEmpty());
        ol1.removeOrder(od1.getOrderID());
        assertEquals(true, ol1.isEmpty());
    }
}
