package model;

import java.util.ArrayList;

// Represents an order with orderID, ArrayList of ordered items,
// and the status of order.
public class Order {

    private static int nextID = 0; // generate the next order ID
    private int orderID;
    private ArrayList<Item> orderedItems;
    private boolean orderedStatus; // true if the order is complete, false if the order is imcomplete

    // EFFECTS: initialize an order with a distinct orderID, an empty ArrayList
    // of ordered items, and the false (unfinished) status of order
    public Order() {
        nextID++;
        this.orderID = nextID;
        this.orderedItems = new ArrayList<>();
        this.orderedStatus = false;
    }

    // REQUIRES: the item exists in AllItems and amount>0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: add the item to the order list with given amount and decrease the
    // stockAmount by amount
    public void addItem(Item item, int amount) {
        item.orderItem(amount);
        orderedItems.add(item);
    }

    // MODIFIES: this, Item, Allitems
    // EFFECTS: remove the item based on the given item name and increase the
    // stockAmount by orderAmount if the item exists in these ordered items,
    // otherwise do nothing
    public void removeItem(String itemName) {
        Item res = findItemInOrder(itemName);
        if (res == null) {
            return;
        } else {
            orderedItems.remove(res);
            res.addstock(res.getOrderAmount());
            res.setOrderAmountZero();
        }
    }

    // EFFECTS: calculate and produce the total price of ordered items
    public double getTotalPrice() {
        double total = 0.0;
        for (Item itm : orderedItems) {
            total += (itm.getPrice() * (double) itm.getOrderAmount());
        }
        total = ((double) Math.round(total * 100.0)) / 100.0; // round the total to at most 2 decimal places
        return total;
    }

    // EFFECTS: produce the list of all item and the total price in String, in a
    // consistent format
    public String printReceipt() {
        String res = "";
        res += "Order ID: " + Integer.toString(orderID) + "\n";
        for (Item itm : orderedItems) {
            res += itm.getName() + " $" + Double.toString(itm.getPrice())
                    + " Amount: " + Integer.toString(itm.getOrderAmount()) + "\n";
        }
        res += " Total: $" + Double.toString(getTotalPrice());
        return res;
    }

    // REQUIRE: the status of order is false
    // MODIFIES: this
    // EFFECTS: complete an order
    public void completeOrder() {
        orderedStatus = true;
    }

    // EFFECTS: produce the item corresponding to the itemName in these ordered
    // items, return
    // null if the item corresponding to the itemName does not exist in these
    // ordered items
    public Item findItemInOrder(String itemName) {
        for (Item itm : orderedItems) {
            if (itemName.equals(itm.getName())) {
                return itm;
            }
        }
        return null;
    }

    public int getOrderID() {
        return orderID;
    }

    public ArrayList<Item> getOrderedLists() {
        return orderedItems;
    }

    public boolean getOrderStatus() {
        return orderedStatus;
    }

}
