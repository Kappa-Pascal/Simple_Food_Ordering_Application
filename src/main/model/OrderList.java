package model;

import java.util.ArrayList;

// Represents the ArrayList of finished orders
// TODO: add removeOrderList, isempty method
// TODO: edited user stories, remove unused class and interface
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

    // REQUIRES: the given orderID exists in OrderList
    // MODIFIES: this
    // EFFECTS: remove the order with the given orderID
    public void removeOrder(int orderID) {
        for (Order ol : orderList) {
            if (ol.getOrderID() == orderID) {
                orderList.remove(ol);
                return;
            }
        }
    }

    // EFFECTS: produce true if the orderList is empty, otherwise produce false
    public boolean isEmpty() {
        return 0 == orderList.size();
    }


    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
