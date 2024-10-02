package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test AllItems class
public class TestAllItems {
    private AllItems allitems;
    private final double error = 0.001;

    @BeforeEach
    void runBefore() {
        allitems = new AllItems();
    }

    @Test
    void testConstructor() {
        ArrayList<Item> empty = new ArrayList<>();
        assertEquals(empty, allitems.getAllItems());
    }

    @Test
    void testAddItem() {
        Item itm1 = new Item("item#1", 10.0, 20);
        allitems.addItem(itm1);
        assertEquals(itm1, allitems.getAllItems().get(0));
        assertEquals(1, allitems.getAllItems().size());
    }

    @Test
    void testAddItemMultiple() {
        Item itm1 = new Item("item#1", 10.0, 20);
        Item itm2 = new Item("item#2", 20.0, 30);
        allitems.addItem(itm1);
        allitems.addItem(itm2);
        assertEquals(itm1, allitems.getAllItems().get(0));
        assertEquals(itm2, allitems.getAllItems().get(1));
        assertEquals(2, allitems.getAllItems().size());
    }

    @Test
    void testRemoveItem() {
        Item itm1 = new Item("item#1", 10.0, 20);
        Item itm2 = new Item("item#2", 20.0, 30);
        allitems.addItem(itm1);
        allitems.addItem(itm2);
        assertEquals(itm1, allitems.getAllItems().get(0));
        assertEquals(itm2, allitems.getAllItems().get(1));
        assertEquals(2, allitems.getAllItems().size());
        allitems.removeItem(itm2);
        assertEquals(itm1, allitems.getAllItems().get(0));
        assertEquals(1, allitems.getAllItems().size());
        allitems.removeItem(itm1);
        assertEquals(0, allitems.getAllItems().size());
    }

    @Test
    void testMutateItem() {
        Item itm1 = new Item("item#1", 10.0, 20);
        Item itm2 = new Item("item#2", 20.0, 30);
        allitems.addItem(itm1);
        allitems.addItem(itm2);
        allitems.mutateItem(itm1, "item 001", 30.0);
        assertEquals("item 001", allitems.getAllItems().get(0).getName());
        assertEquals(30.0, allitems.getAllItems().get(0).getPrice(), error);
        assertEquals("item 001", itm1.getName());
        assertEquals(30.0, itm1.getPrice(), error);
    }

    @Test
    void testFindItem() {
        Item itm1 = new Item("item#1", 10.0, 20);
        Item itm2 = new Item("item#2", 20.0, 30);
        allitems.addItem(itm1);
        allitems.addItem(itm2);
        assertEquals(itm1, allitems.findItem("item#1"));
        assertEquals(itm2, allitems.findItem("item#2"));
        assertEquals(null, allitems.findItem("item#3"));
    }

    @Test
    void testPrintItems() {
        Item itm1 = new Item("item#1", 10.0, 20);
        Item itm2 = new Item("item#2", 20.0, 30);
        allitems.addItem(itm1);
        allitems.addItem(itm2);
        assertEquals("item#1 $10.0\nitem#2 $20.0\n", allitems.printItems());
    }
}
