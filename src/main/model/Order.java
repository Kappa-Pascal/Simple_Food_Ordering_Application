package model;

import java.util.ArrayList;

// Represents an order with orderID, ArrayList of ordered items,
// and the status of order.

public class Order {

    //EFFECTS: initialize an order with a distinct orderID, an empty ArrayList
    // of ordered items, and the false (unfinished) status of order
    public Order() {
        // stub
    }

    //REQUIRES: the  item based on the given item name exists in AllItems and amount>0
    //MODIFIES: this, Item, AllItems
    //EFFECTS: add the item based on the given item name with given amount and decrease the 
    //stockAmount by amount
    public void addItem(Item item, int amount) {
        //stub
    }

    //REQUIRES: the the item based on the given item name exists in ArrayList of ordered items and amount>0
    //MODIFIES: this
    //EFFECTS: remove the item based on the given item name and increase the 
    //stockAmount by orderAmount
    public void removeItem() {
        //stub
    }

    //EFFECTS: calculate and produce the total price of ordered items
    public double getTotalPrice() {
        return 0.0;//stub
    }

    //EFFECTS: produce the list of all item and the total price in String, in a consistent format
    public String printReceipt() {
        return "";
    }

    //REQUIRE: the status of order is true 
    //MODIFIES: OrderList
    //EFFECTS: add the finished order to order list
    public void addToOrderList(Order order){
        //stub
    }

    //REQUIRE: the status of order is false
    //MODIFIES: this
    //EFFECTS: complete an order
    public void completeOrder() {
        //stub
    }

    public int getOrderID() {
        return 0;
    }

    public ArrayList<Item> getOrderedLists() {
        return null;
    }

    public boolean getOrderStatus() {
        return false;
    }

}
