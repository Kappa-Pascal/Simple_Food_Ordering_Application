package ui;

import javax.swing.*;

import model.AllItems;
import model.Item;
import model.Order;
import model.OrderList;
import persistence.JsonReaderAllItems;
import persistence.JsonWriterAllItems;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represent the graphic UI of the project
// Part of the code is modelled from AlarmSystem
// Citation: “Build software better, together,” GitHub. https://github.students.cs.ubc.ca/CPSC210/AlarmSystem 
public class GraphicUI extends JFrame implements ActionListener {
    private AllItems allItems;
    private OrderList orderList;
    private Order order;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JButton addSingleItem;
    private ActionListener actionListener;
    private Action addSingleItemAction;
    private Action addMultipleItemAction;
    private Action removeItemAction;
    private Action mutateItemAction;
    private Action saveItemAction;
    private Action loadItemAction;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final String PATH = "./data/savedData.json";

    // EFFECTS: Display the graphic UI of the project
    public GraphicUI() {
        this.allItems = new AllItems();
        this.orderList = new OrderList();
        // this.addSingleItem = new JButton("Add single item");
        setBlankFrame();
        // addSingleItem.addActionListener(actionListener);
        addButtonPanel();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        controlPanel.pack();
        // JList<Item> displayList = new JList<>();
        // JScrollPane scrollPane = new JScrollPane(displayList);
        // getContentPane().add(scrollPane);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: Display a blank frame
    public void setBlankFrame() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Graphic UI", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        setContentPane(desktop);
        setTitle("Food Ordering Application");
        setSize(WIDTH, HEIGHT);
    }

    // Represent a inner class that is used for key handling
    private class DesktopFocusAction extends MouseAdapter {

        // EFFECTS: Handle the MouseEvent
        @Override
        public void mouseClicked(MouseEvent e) {
            GraphicUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: Detect a MouseEvent
    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicUI.this.requestFocusInWindow();
    }

    // MODIFIES: this
    // EFFECTS: Add buttons in the graphic UI
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddSingleItemAction()));
        buttonPanel.add(new JButton(new AddMultipleItemsAction()));
        buttonPanel.add(new JButton(new ViewItemAction()));
        buttonPanel.add(new JButton(removeItemAction));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton(removeItemAction));
        buttonPanel.add(new JButton(removeItemAction));
        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the name of the item from user's input
    public static String readItemName() {
        String name = JOptionPane.showInputDialog(null,
                "Item Name?",
                "Enter Item Name:",
                JOptionPane.QUESTION_MESSAGE);
        return name;
    }

    // REQUIRES: input >= 0.0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the price of the item from user's input
    public static Double readItemPrice() throws NumberFormatException {
        double res = 0.0;
        String price = JOptionPane.showInputDialog(null,
                "Item Price?",
                "Enter Item Price: (non-negative number)",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Double.valueOf(price);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }

    }

    // REQUIRES: input >= 0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the stock of the item from user's input
    public static int readItemStock() throws NumberFormatException {
        int res = 0;
        String stock = JOptionPane.showInputDialog(null,
                "Item Stock?",
                "Enter Item Stock (non-negative integer):",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Integer.valueOf(stock);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // REQUIRES: input >= 0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the number of the items the user want to add from user's input
    public static int readReps() throws NumberFormatException {
        int res = 0;
        String reps = JOptionPane.showInputDialog(null,
                "Number of Items?",
                "Enter the Number of Items (non-negative integer):",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Integer.valueOf(reps);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // Represents the functionality of Add Single Item botton
    private class AddSingleItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddSingleItemAction() {
            super("Add a single Item");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add a single item when the "Add a single Item" botton is clicked
        @Override
        public void actionPerformed(ActionEvent evt) {
            // The following code allows users to input a String.
            /*
             * String sensorLoc = JOptionPane.showInputDialog(null,
             * "Sensor location?",
             * "Enter sensor location",
             * JOptionPane.QUESTION_MESSAGE);
             */

            // The following code displays a String in a new window
            // JOptionPane.showMessageDialog(null, String);

            // AlarmCode alarmCode = new AlarmCode(kp.getCode());
            // kp.clearCode();
            // try {
            // ac.addCode(alarmCode);
            // } catch (NotValidCodeException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
            // JOptionPane.ERROR_MESSAGE);
            // }
            // System.out.println("success");
            String name = readItemName();
            double price = readItemPrice();
            int stock = readItemStock();
            allItems.addItem(new Item(name, price, stock));
        }
    }

    // Represents the functionality of Add Multiple Items botton
    private class AddMultipleItemsAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddMultipleItemsAction() {
            super("Add multiple items");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add multiple items when the "Add a single Item" botton is clicked
        @Override
        public void actionPerformed(ActionEvent evt) {
            int reps = readReps();
            AddSingleItemAction asi = new AddSingleItemAction();
            for (int i = 0; i < reps; i++) {
                asi.actionPerformed(evt);
            }
        }

    }

    // Represents the functionality of View Items botton
    private class ViewItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        ViewItemAction() {
            super("View all added items");
        }

        // MODIFIES: this
        // EFFECTS: Display the name and the price of items when the "View all added
        // items" botton is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "All added items are:\n" + allItems.printItems());
        }
    }

    // Represents the functionality of Save botton
    private class SaveAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        SaveAction() {
            super("Save");
        }

        // MODIFIES: this
        // EFFECTS: Save all added items when the "Save" button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonWriterAllItems writer = new JsonWriterAllItems(PATH);
            try {
                writer.open();
            } catch (FileNotFoundException ec) {
                return;
            }
            writer.write(allItems);
            writer.close();
            JOptionPane.showMessageDialog(null, "All added items are saved");
        }

    }

    // Represents the functionality of Load botton
    private class LoadAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        LoadAction() {
            super("Load");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Load all saved items when the "Load" button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonReaderAllItems reader = new JsonReaderAllItems(PATH);
            try {
                allItems = reader.read();
                JOptionPane.showMessageDialog(null, "All Saved items are loaded");                
            } catch (IOException ec) {
                return;
            }
        }

    }

    // Represents the functionality of Remove item botton
    private class RemoveItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        RemoveItemAction() {
            
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Remove items whose name is equal to the user's input
        // if the name of the item does not exist, do nothing 
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }
}
