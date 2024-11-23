package model;

import java.util.ArrayList;

// Represents the List of ALL items added but not deleted by stuff,
// regardless of its availibility.
public class AllItems {
    private ArrayList<Item> allItems;

    // EFFECTS: initialize an arrayList of items
    public AllItems() {
        this.allItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an item to AllItems
    public void addItem(Item item) {
        allItems.add(item);
        EventLog.getInstance().logEvent(new Event("An Item named "
                + item.getName() + " is added"));
    }

    // REQUIRES: the given item exists
    // MODIFIES: this
    // EFFECTS: remove an item in AllItems
    public void removeItem(Item item) {
        allItems.remove(item);
        EventLog.getInstance().logEvent(new Event("An Item named"
                + item.getName() + " is removed"));
    }

    // REQUIRES: the given item exists
    // MODIFIES: this, Item
    // EFFECTS: mutate the name and price of an item
    public void mutateItem(Item item, String newName, double newPrice) {
        String originalName = item.getName();
        item.setName(newName);
        item.setPrice(newPrice);
        EventLog.getInstance().logEvent(new Event("An Item originally named"
                + originalName + " is changed to the new name, " + newName
                + ", and its price is changed to $" + newPrice));
    }

    // EFFECTS: produce the item corresponding to the itemName, return
    // null if the item corresponding to the itemName does not exist
    public Item findItem(String itemName) {
        for (Item itm : allItems) {
            if (itm.getName().equals(itemName)) {
                return itm;
            }
        }
        return null;
    }

    // EFFECTS: produce the list of all items in String, in a consistent format
    public String printItems() {
        String res = "";
        for (Item itm : allItems) {
            res += (itm.getName() + " $" + Double.toString(itm.getPrice()) + "\n");
        }
        return res;
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }
}
