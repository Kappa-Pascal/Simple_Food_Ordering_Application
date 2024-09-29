package model;

import java.util.ArrayList;

// Represents the List of ALL items added but not deleted by stuff,
// regardless of its availibility.

public class AllItems {

    // EFFECTS: initialize an arrayList of items
    public AllItems() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: add an item to AllItems
    public void addItem(Item item) {
        //stub
    }

    // REQUIRES: the given item exists
    // MODIFIES: this
    // EFFECTS: remove an item in AllItems 
    public void removeItem(Item item) {
        //stub
    }

    // REQUIRES: the given item exists
    // MODIFIES: this, Item
    // EFFECTS: mutate the name and price of an item 
    public void mutateItem(Item item,String newName,double newPrice) {
        //stub
    }

    // EFFECTS: produce the item corresponding to the itemName, otherwise return null 
    public Item findItem(String itemName) {
        return null;
    }

    // EFFECTS: produce the list of all items in String, in a consistent format
    public String printItems() {
        return "";
    }

    public ArrayList<Item> getAllItems() {
        return null;
    }
}
