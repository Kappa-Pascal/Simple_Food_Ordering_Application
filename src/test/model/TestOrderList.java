package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals("--- Order #1 ---\n" + od1.printReceipt()
                + "--- Order #2 ---\n" + od2.printReceipt()
                + "--- End ---", ol1.displayOrderList());
    }
}
