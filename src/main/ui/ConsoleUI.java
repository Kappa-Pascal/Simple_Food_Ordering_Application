package ui;

import model.AllItems;
import model.Item;
import model.Order;
import model.OrderList;
import persistence.JsonReaderAllItems;
import persistence.JsonWriterAllItems;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represent the UI of both customers and stuffs and display the UI on the console
public class ConsoleUI {
    private AllItems allItems;
    private OrderList orderList;
    private Scanner in;
    private Order order;
    private String input;
    private static final String textUI = "Welcome to the Food Ordering Application\n"
            + "s: Go to the stuff memu\n"
            + "c: Go to the customer menu\n"
            + "q: Quit the app if the list of the order is empty";
    private static final String errorMessage = "Invalid input, please try again";
    private static final String itemNotFoundMessage = "The give Item is not found";
    private static final String textStuffUI = "Welcome to the stuff menu\n"
            + "ai: Add a new item to the item list\n"
            + "as: Add the stock to the given item\n"
            + "rm: Remove given item\n"
            + "mt: mutate given item\n"
            + "vi: view item list\n"
            + "vo: view order list\n"
            + "sa: save the item list\n"
            + "ld: load the saved item list and overwrite the current item list\n"
            + "ro: remove order in the order list\n"
            + "ex: go back to the previous menu";
    private static final String textCustomerUI = "Welcome to the customer menu\n"
            + "vi: view item list\n"
            + "od: order a new item\n"
            + "rm: Remove given item in the order\n"
            + "gt: get the total price\n"
            + "pr: print the receipt\n"
            + "fa: finish order and add this order to the order list\n"
            + "ex: go back to the previous menu";
    private static final String duplicateNameError = "The given name is duplicated to " 
            + "the name of item you have added before";
    private static final String path = "./data/savedData.json";

    // MODIFIES: this
    // EFFECTS: display the menu that allows user to select type of user
    // (stuff or customer)
    public ConsoleUI() {
        in = new Scanner(System.in);
        allItems = new AllItems();
        orderList = new OrderList();
        boolean state = true;
        while (state) {
            System.out.println(textUI);
            input = in.nextLine();
            switch (input) {
                case "s":
                    stuffUI();
                    break;
                case "c":
                    customerUI();
                    break;
                case "q":
                    state = !isEmpty();
                    break;
                default:
                    System.out.println(errorMessage);
                    break;
            }
        }
        assert (isEmpty() == true);
    }

    // MODIFIES: this, Item, AllItems
    // EFFECTS: display the menu of UI for stuff and allow stuff to realize user
    // stories of stuffs
    @SuppressWarnings("methodlength")
    public void stuffUI() {
        while (true) {
            System.out.println(textStuffUI);
            input = in.nextLine();
            if (input.equals("")) {
                input = in.nextLine();
            }
            switch (input) {
                case "ai":
                    addItemStuffUI();
                    break;
                case "as":
                    addStockStuffUI();
                    break;
                case "rm":
                    removeItemStuffUI();
                    break;
                case "mt":
                    mutateItemStuffUI();
                    break;
                case "vi":
                    System.out.println(allItems.printItems());
                    break;
                case "vo":
                    System.out.println(orderList.displayOrderList());
                    break;
                case "sa":
                    saveItemUI();
                    break;
                case "ld":
                    allItems = loadItemUI();
                    break;
                case "ro":
                    removeOrderUI();
                    break;
                case "ex":
                    return;
                default:
                    System.out.println(errorMessage);
                    break;
            }
        }
    }

    // MODIFIES: this, Item, AllItems, Order, OrderList
    // EFFECTS: display the menu of UI for customers and allow customers to realize
    // user stories
    @SuppressWarnings("methodlength")
    public void customerUI() {
        order = new Order();
        while (true) {
            System.out.println(textCustomerUI);
            String input = in.nextLine();
            if (input.equals("")) {
                input = in.nextLine();
            }
            switch (input) {
                case "vi":
                    System.out.println(allItems.printItems());
                    break;
                case "od":
                    addItemCustomerUI();
                    break;
                case "rm":
                    removeItemCustomerUI();
                    break;
                case "gt":
                    System.out.println(order.getTotalPrice());
                    break;
                case "pr":
                    System.out.println(order.printReceipt());
                    break;
                case "fa":
                    finishOrderUI();
                    return;
                case "ex":
                    return;
                default:
                    System.out.println(errorMessage);
                    break;
            }
        }
    }

    // EFFECTS: produce true if the item with the given name exists in AllItems,
    // otherwise produce false
    public boolean checkExistanceAllItems(String name) {
        if (allItems.findItem(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    // EFFECTS: produce true if the item with the given name exists in a given
    // order, otherwise produce false
    public boolean checkExistanceOrder(String name, Order odr) {
        if (odr.findItemInOrder(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this, item, allItems
    // EFFECTS: display the ui of adding items in stuff menu
    public void addItemStuffUI() {
        System.out.println("Input the name of item, please do not add the item with the same name twice");
        String name = in.nextLine();
        if (checkExistanceAllItems(name)) {
            System.out.println(duplicateNameError);
            return;
        }
        System.out.println("Input the price of item");
        double price = in.nextDouble();
        System.out.println("Input the amount of stock of item");
        int stock = in.nextInt();
        Item item = new Item(name, price, stock);
        allItems.addItem(item);
    }

    // MODIFIES: this, item, allItemss
    // EFFECTS: display the ui of adding stock in stuff menu
    public void addStockStuffUI() {
        System.out.println("Input the name of item you want to add stock to");
        String name = in.nextLine();
        if (!checkExistanceAllItems(name)) {
            System.out.println(itemNotFoundMessage);
        } else {
            System.out.println("Input the amount of stock you want to add to");
            int amount = in.nextInt();
            allItems.findItem(name).addstock(amount);
        }
    }

    // MODIFIES: this, item, allItems
    // EFFECTS: display the ui of removing items in stuff menu
    public void removeItemStuffUI() {
        System.out.println("Input the name of item you want to remove");
        String name = in.nextLine();
        if (!checkExistanceAllItems(name)) {
            System.out.println(itemNotFoundMessage);
        } else {
            allItems.removeItem(allItems.findItem(name));
        }
    }

    // MODIFIES: this, Item, Allitems
    // EFFECTS: display the ui of mutating items in stuff menu
    public void mutateItemStuffUI() {
        System.out.println("Input the name of item you want to mutate");
        String name = in.nextLine();
        if (!checkExistanceAllItems(name)) {
            System.out.println(itemNotFoundMessage);
        } else {
            System.out.println("Input the new name of item, "
                    + "please do not add the item with the same name twice");
            String newName = in.nextLine();
            if (checkExistanceAllItems(newName) && !(newName.equals(name))) {
                System.out.println(duplicateNameError);
                return;
            }
            System.out.println("Input the new price of item");
            double newPrice = in.nextDouble();
            allItems.mutateItem(allItems.findItem(name), newName, newPrice);
        }
    }

    // MODIFIES: this, Item, Allitems, Order
    // EFFECTS: display the ui of adding an item from the item list to the order
    public void addItemCustomerUI() {
        System.out.println("Input the name of item you want to order, "
                + "please do not order the item with the same name twice");
        String name = in.nextLine();
        if (!checkExistanceAllItems(name)) {
            System.out.println(itemNotFoundMessage);
        } else if (checkExistanceOrder(name,order)) {
            System.out.println(duplicateNameError);
        } else {
            System.out.println("Input the quantity of this item");
            int amount = in.nextInt();
            if (amount > allItems.findItem(name).getStockAmount()) {
                System.out.println("The Stock of this item is not enough");
                return;
            }
            order.addItem(allItems.findItem(name), amount);
        }
    }

    // MODIFIES: this, Item, Allitems, Order
    // EFFECTS: display the ui of removing an item from the item list from the order
    public void removeItemCustomerUI() {
        System.out.println("Input the name of item you want to remove");
        String name = in.nextLine();
        order.removeItem(name);
    }

    // MODIFIES: this, Order, OrderList
    // EFFECTS: display the ui of finishing order
    public void finishOrderUI() {
        order.completeOrder();
        orderList.addToOrderList(order);
        System.out.println("Your order is finished and recorded");
    }

    //EFFECTS: save the data of AllItem
    public void saveItemUI() {
        JsonWriterAllItems writer = new JsonWriterAllItems(path);
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            return;
        }
        writer.write(allItems);
        writer.close();
    }

    //MODIFIES: this, AllItems
    //EFFECTS: load the saved data of AllItem and overwrite the AllItem with saved data
    public AllItems loadItemUI() {
        JsonReaderAllItems reader = new JsonReaderAllItems(path);
        try {
            return reader.read();
        } catch (IOException e) {
            return null;
        }
    }

    //MODIFIES: this, OrderList
    //EFFECTS: remove the order in the OrderList by the given order id
    // if the order id exist; otherwise do nothing
    public void removeOrderUI() {
        System.out.println("Input the id of the order you want to remove");
        int id = in.nextInt();
        orderList.removeOrder(id);
    }

    //MODIFIES: this
    //EFFECTS: produce true if the OrderList is empty; otherwise produce false
    public boolean isEmpty() {
        if (orderList.isEmpty()) {
            return true;
        } else {
            System.out.println("The order list is not empty, please check the unfinished orders");
            return false;
        }
    }
}
