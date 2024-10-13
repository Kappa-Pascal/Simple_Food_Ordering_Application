package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, double price, int stockAmount,
            int orderAmount, boolean availbty, Item item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(stockAmount, item.getStockAmount());
        assertEquals(orderAmount, item.getOrderAmount());
        assertEquals(availbty, item.getAvailbty());
    }
}
