package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test Item class
public class TestItem {
    private Item itm1;

    @BeforeEach
    void runBefore() {
        itm1 = new Item("item#1", 10.0, 20);
    }

    @Test
    void testConstructor() {
        assertEquals("item#1", itm1.getName());
        assertEquals(10.0, itm1.getPrice());
        assertEquals(20, itm1.getStockAmount());
        assertEquals(0, itm1.getOrderAmount());
        assertTrue(itm1.getAvailbty());
    }

    @Test
    void testAddstock() {
        itm1.addstock(20);
        assertEquals(20 + 20, itm1.getStockAmount());
    }

    @Test
    void testSetPrice() {
        itm1.setPrice(15.0);
        assertEquals(15.0, itm1.getPrice());
    }

    @Test
    void testSetName() {
        itm1.setName("i#1");
        assertEquals("i#1", itm1.getName());
    }

    @Test
    void testOrderItem() {
        itm1.orderItem(5);
        assertEquals(5, itm1.getOrderAmount());
        assertEquals(20 - 5, itm1.getStockAmount());
    }

    @Test
    void testDetermineAvailbty() {
        itm1.determineAvailbty();
        assertTrue(itm1.getAvailbty());
        itm1.orderItem(20);
        itm1.determineAvailbty();
        assertFalse(itm1.getAvailbty());
        itm1.addstock(10);
        itm1.determineAvailbty();
        assertTrue(itm1.getAvailbty());
    }

    @Test
    void testSetOrderAmountZero() {
        itm1.orderItem(5);
        assertEquals(5, itm1.getOrderAmount());
        assertEquals(20 - 5, itm1.getStockAmount());
        itm1.setOrderAmountZero();
        assertEquals(0, itm1.getOrderAmount());
    }

    @Test
    void testSetOrderAmount() {
        assertEquals(0, itm1.getOrderAmount());
        itm1.setOrderAmount(2);
        assertEquals(2, itm1.getOrderAmount());
    }
}
