package model;

import java.util.ArrayList;

// Represents the ArrayList of finished orders

public class OrderList {

    ArrayList<Order> orderList;

    // EFFECTS: initialize an empty ArrayList of Order
    public OrderList() {
        orderList = new ArrayList<>();
    }

    // REQUIRE: the status of order is true
    // MODIFIES: OrderList
    // EFFECTS: add the finished order to order list
    public void addToOrderList(Order order) {
        orderList.add(order);
    }

    // EFFECTS: print the receipt of all order in String in OrderList
    public String displayOrderList() {
        int counter = 0;
        String res = "";
        for (Order odr : orderList) {
            counter++;
            res += "\n--- Order #" + Integer.toString(counter) + " ---\n" + odr.printReceipt();
        }
        res += "\n--- End ---\n";
        return res;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
