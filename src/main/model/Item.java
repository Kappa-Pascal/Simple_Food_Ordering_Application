package model;

import org.json.JSONObject;

// Represents an item with its name, price, amount of stock, amount
// of item in an order, and availibitity (availbty).
public class Item {
    private String name;
    private double price;
    private int stockAmount;
    private int orderAmount;
    private boolean availbty;

    // REQUIRE: the name is not empty, price >= 0, and stockAmount >= 0
    // EFFECTS: initialize an item with a given name, price, the amount of stock, 0
    // of item in an order
    // and availiblity based on the amount of stock
    public Item(String name, double price, int stockAmount) {
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
        this.orderAmount = 0;
        determineAvailbty();
    }

    // REQUIRE: amount>=0
    // MODIFIES: this
    // EFFECTS: add the amount of stock by a given number
    public void addstock(int amount) {
        stockAmount += amount;
    }

    // REQUIRE: newPrice>=0
    // MODIFIES: this
    // EFFECTS: change the price of the item
    public void setPrice(double newPrice) {
        price = newPrice;
    }

    // REQUIRE: newName is not empty
    // MODIFIES: this
    // EFFECTS: change the name of the item
    public void setName(String newName) {
        name = newName;
    }

    // REQUIRE: amount<=stockAmount
    // MODIFIES: this
    // EFFECTS: decrease the amount of stock by the given number,
    // and increase the amount of item by the given number
    public void orderItem(int num) {
        stockAmount -= num;
        orderAmount += num;
    }

    // MODIFIES: this
    // EFFECTS: determine the availbility of this item
    public void determineAvailbty() {
        if (stockAmount <= 0) {
            availbty = false;
        } else {
            availbty = true;
        }
    }

    //REQUIRES: amount>=0
    //MODIFIES: this
    //EFFECTS: set the order amount to a given amount
    public void setOrderAmount(int amount) {
        orderAmount = amount;
    }

    //MODIFIES: this
    //EFFECTS: set the order amount to zero
    public void setOrderAmountZero() {
        orderAmount = 0;
    }

    //EFFECTS: produce a JsonObject from an item instance
    public JSONObject toJson() {
        return null;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public boolean getAvailbty() {
        return availbty;
    }

}
